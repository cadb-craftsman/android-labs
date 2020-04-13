package com.woowrale.jmvvm.ui.search.remote;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;
import com.woowrale.jmvvm.data.model.Contact;
import com.woowrale.jmvvm.data.repository.ApiService;
import com.woowrale.jmvvm.ui.adapters.ContactsAdapter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import io.reactivex.subjects.PublishSubject;

public class RemoteSearchViewModel extends ViewModel {
    private static final String TAG = RemoteSearchViewModel.class.getSimpleName();

    private ApiService apiService;

    private MutableLiveData<DisposableObserver<List<Contact>>> searchObserver;

    @Inject
    public RemoteSearchViewModel(ApiService apiService){
        this.apiService = apiService;
        searchObserver = new MutableLiveData<DisposableObserver<List<Contact>>>();
    }

    public LiveData<DisposableObserver<List<Contact>>> searchContacts(List<Contact> contactList,  ContactsAdapter mAdapter) {
        searchObserver.setValue(new DisposableObserver<List<Contact>>() {
            @Override
            public void onNext(List<Contact> contacts) {
                contactList.clear();
                contactList.addAll(contacts);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
        return searchObserver;
    }

    public DisposableObserver<TextViewTextChangeEvent> searchContactsTextWatcher(PublishSubject<String> publishSubject) {
        return new DisposableObserver<TextViewTextChangeEvent>() {
            @Override
            public void onNext(TextViewTextChangeEvent textViewTextChangeEvent) {
                Log.d(TAG, "Search query: " + textViewTextChangeEvent.text());
                publishSubject.onNext(textViewTextChangeEvent.text().toString());
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
