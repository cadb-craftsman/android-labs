package com.woowrale.jmvvm.ui.search.local;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;
import com.woowrale.jmvvm.BuildConfig;
import com.woowrale.jmvvm.data.model.Contact;
import com.woowrale.jmvvm.data.repository.ApiService;
import com.woowrale.jmvvm.ui.adapters.ContactsAdapterFilterable;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class LocalSearchViewModel extends ViewModel {

    private static final String TAG = LocalSearchViewModel.class.getSimpleName();

    private ApiService apiService;

    private MutableLiveData<DisposableObserver<TextViewTextChangeEvent>> textSearch;

    @Inject
    public LocalSearchViewModel(ApiService apiService) {
        this.apiService = apiService;
        textSearch = new MutableLiveData<DisposableObserver<TextViewTextChangeEvent>>();
    }

    public LiveData<DisposableObserver<TextViewTextChangeEvent>> searchContacts(ContactsAdapterFilterable mAdapter) {
        textSearch.setValue(new DisposableObserver<TextViewTextChangeEvent>() {
            @Override
            public void onNext(TextViewTextChangeEvent textViewTextChangeEvent) {
                Log.d(TAG, "Search query: " + textViewTextChangeEvent.text());
                mAdapter.getFilter().filter(textViewTextChangeEvent.text());
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
        return textSearch;
    }

    public void fetchContacts(CompositeDisposable disposable, String source, List<Contact> contactsList, ContactsAdapterFilterable mAdapter) {
        disposable.add(apiService
                .getContacts(BuildConfig.GET_CONTACTS, source, null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Contact>>() {
                    @Override
                    public void onSuccess(List<Contact> contacts) {
                        contactsList.clear();
                        contactsList.addAll(contacts);
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }
}
