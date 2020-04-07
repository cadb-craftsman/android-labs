package com.woowrale.instantsearch.japp.ui.search.remote;

import android.util.Log;

import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;
import com.woowrale.mvp.data.model.Contact;
import com.woowrale.mvp.data.repository.ApiService;
import com.woowrale.mvp.ui.base.BasePresenter;

import java.util.List;

import io.reactivex.observers.DisposableObserver;
import retrofit2.Retrofit;

public class RemoteSearchPresenter extends BasePresenter<RemoteSearchView> {

    private static final String TAG = RemoteSearchPresenter.class.getSimpleName();

    private RemoteSearchView view;
    private ApiService apiService;

    public RemoteSearchPresenter(RemoteSearchActivity view, Retrofit retrofit){
        this.view = view;
        apiService = retrofit.create(ApiService.class);
    }

    public DisposableObserver<List<Contact>> getSearchObserver() {
        return new DisposableObserver<List<Contact>>() {
            @Override
            public void onNext(List<Contact> contacts) {
                view.getContactsList().clear();
                view.getContactsList().addAll(contacts);
                view.getmAdapter().notifyDataSetChanged();
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

    public DisposableObserver<TextViewTextChangeEvent> searchContactsTextWatcher() {
        return new DisposableObserver<TextViewTextChangeEvent>() {
            @Override
            public void onNext(TextViewTextChangeEvent textViewTextChangeEvent) {
                Log.d(TAG, "Search query: " + textViewTextChangeEvent.text());
                view.getPublishSubject().onNext(textViewTextChangeEvent.text().toString());
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

    public ApiService getApiService() {
        return apiService;
    }
}
