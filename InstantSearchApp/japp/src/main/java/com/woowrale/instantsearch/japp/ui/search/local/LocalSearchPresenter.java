package com.woowrale.instantsearch.japp.ui.search.local;

import android.util.Log;

import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;
import com.woowrale.mvp.BuildConfig;
import com.woowrale.mvp.data.model.Contact;
import com.woowrale.mvp.data.repository.ApiService;
import com.woowrale.mvp.ui.base.BasePresenter;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class LocalSearchPresenter extends BasePresenter<LocalSearchView> {

    private static final String TAG = LocalSearchPresenter.class.getSimpleName();

    private LocalSearchView view;
    private ApiService apiService;

    public LocalSearchPresenter(LocalSearchActivity view, Retrofit retrofit) {
        this.view = view;
        this.apiService = retrofit.create(ApiService.class);
    }

    public DisposableObserver<TextViewTextChangeEvent> searchContacts() {
        return new DisposableObserver<TextViewTextChangeEvent>() {
            @Override
            public void onNext(TextViewTextChangeEvent textViewTextChangeEvent) {
                Log.d(TAG, "Search query: " + textViewTextChangeEvent.text());
                view.getmAdapter().getFilter().filter(textViewTextChangeEvent.text());
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
        view.getDisposable().add(apiService
                .getContacts(BuildConfig.GET_CONTACTS, source, null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Contact>>() {
                    @Override
                    public void onSuccess(List<Contact> contacts) {
                        view.getContactsList().clear();
                        view.getContactsList().addAll(contacts);
                        view.getmAdapter().notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

}
