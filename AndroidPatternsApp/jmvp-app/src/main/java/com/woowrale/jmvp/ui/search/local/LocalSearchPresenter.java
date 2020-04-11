package com.woowrale.jmvp.ui.search.local;

import android.util.Log;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;
import com.woowrale.jmvp.BuildConfig;
import com.woowrale.jmvp.data.model.Contact;
import com.woowrale.jmvp.data.repository.ApiService;
import com.woowrale.jmvp.ui.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class LocalSearchPresenter extends BasePresenter<LocalSearchView> {

    private static final String TAG = LocalSearchPresenter.class.getSimpleName();

    private ApiService apiService;

    @Inject
    public LocalSearchPresenter(ApiService apiService) {
        this.apiService = apiService;
    }

    public DisposableObserver<TextViewTextChangeEvent> searchContacts() {
        return new DisposableObserver<TextViewTextChangeEvent>() {
            @Override
            public void onNext(TextViewTextChangeEvent textViewTextChangeEvent) {
                Log.d(TAG, "Search query: " + textViewTextChangeEvent.text());
                getView().getmAdapter().getFilter().filter(textViewTextChangeEvent.text());
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
    }

    public void fetchContacts(String source) {
        getView().getDisposable().add(apiService
                .getContacts(BuildConfig.GET_CONTACTS, source, null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Contact>>() {
                    @Override
                    public void onSuccess(List<Contact> contacts) {
                        getView().getContactsList().clear();
                        getView().getContactsList().addAll(contacts);
                        getView().getmAdapter().notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

}
