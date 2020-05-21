package com.woowrale.jcleanarchitecture.ui.search.local;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;
import com.woowrale.domain.model.Contact;
import com.woowrale.jcleanarchitecture.di.factory.UseCaseFactory;
import com.woowrale.jcleanarchitecture.ui.adapters.ContactsAdapterFilterable;
import com.woowrale.usecase.observers.Observer;
import com.woowrale.usecase.usescases.GetContactAllUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public class LocalSearchViewModel extends ViewModel {

    private static final String TAG = LocalSearchViewModel.class.getSimpleName();

    private UseCaseFactory useCaseFactory;
    private MutableLiveData<Intent> navigation;
    private MutableLiveData<DisposableObserver<TextViewTextChangeEvent>> textSearch;

    @Inject
    public LocalSearchViewModel(UseCaseFactory useCaseFactory) {
        this.useCaseFactory = useCaseFactory;
        navigation = new MutableLiveData<Intent>();
        textSearch = new MutableLiveData<DisposableObserver<TextViewTextChangeEvent>>();
    }

    public LiveData<Intent> navigationTo(Context context, Class navigationClass){
        Intent intent = new Intent(context, navigationClass);
        navigation.setValue(intent);
        return navigation;
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

    public void getLocalContacts(CompositeDisposable disposable, List<Contact> contactsList, ContactsAdapterFilterable mAdapter) {
        disposable.add(useCaseFactory.getLocalContacts().execute(new ContactObserver(contactsList, mAdapter), new GetContactAllUseCase.Params(contactsList)));
    }

    private final class ContactObserver extends Observer<List<Contact>> {

        private List<Contact> contactList;
        private ContactsAdapterFilterable mAdapter;

        public ContactObserver(List<Contact> contactList, ContactsAdapterFilterable mAdapter) {
            this.contactList = contactList;
            this.mAdapter = mAdapter;
        }

        @Override
        public void onSuccess(List<Contact> contacts) {
            contactList.clear();
            contactList.addAll(contacts);
            mAdapter.notifyDataSetChanged();
        }

        @Override
        public void onError(Throwable exception) {

        }
    }
}
