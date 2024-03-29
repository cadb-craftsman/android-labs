package com.woowrale.jmvvm.ui.search.remote;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.woowrale.jmvvm.BuildConfig;
import com.woowrale.jmvvm.R;
import com.woowrale.jmvvm.data.model.Contact;
import com.woowrale.jmvvm.ui.adapters.ContactsAdapter;
import com.woowrale.jmvvm.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class RemoteSearchActivity extends BaseActivity implements ContactsAdapter.ContactsAdapterListener {

    @BindView(R.id.input_search)
    EditText inputSearch;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    RemoteSearchViewModel model;

    private Unbinder unbinder;
    private ContactsAdapter mAdapter;

    private List<Contact> contactsList = new ArrayList<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private PublishSubject<String> publishSubject = PublishSubject.create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDagger();

        setContentView(R.layout.activity_remote_search);
        unbinder = ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAdapter = new ContactsAdapter(this, contactsList, this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        whiteNotificationBar(recyclerView);

        DisposableObserver<List<Contact>> observer = model.searchContacts(contactsList, mAdapter).getValue();

        disposable.add(
                publishSubject
                        .debounce(300, TimeUnit.MILLISECONDS)
                        .distinctUntilChanged()
                        .switchMapSingle(new Function<String, Single<List<Contact>>>() {
                            @Override
                            public Single<List<Contact>> apply(String s) throws Exception {
                                return model.getApiService().getContacts(BuildConfig.GET_CONTACTS,null, s)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread());
                            }
                        })
                        .subscribeWith(observer));

        disposable.add(
                RxTextView.textChangeEvents(inputSearch)
                        .skipInitialValue()
                        .debounce(300, TimeUnit.MILLISECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(model.searchContactsTextWatcher(publishSubject)));

        disposable.add(observer);

        publishSubject.onNext("");
    }

    protected void initDagger() {
        super.getDaggerMainComponent().inject(this);
    }

    @Override
    protected void onDestroy() {
        disposable.clear();
        unbinder.unbind();
        super.onDestroy();
    }

    @Override
    public void onContactSelected(Contact contact) {

    }

    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}