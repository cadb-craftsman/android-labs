package com.woowrale.jroomapp.ui.search.local;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;
import com.woowrale.jroomapp.di.factory.UseCaseFactory;
import com.woowrale.jroomapp.domain.model.Contact;
import com.woowrale.jroomapp.ui.adapters.ContactsAdapterFilterable;
import com.woowrale.jroomapp.usecases.ContactAllUseCase;
import com.woowrale.jroomapp.usecases.observers.Observer;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public class LocalSearchViewModel extends ViewModel {

    private static final String TAG = LocalSearchViewModel.class.getSimpleName();

    private UseCaseFactory useCaseFactory;
    private MutableLiveData<DisposableObserver<TextViewTextChangeEvent>> textSearch;

    @Inject
    public LocalSearchViewModel(UseCaseFactory useCaseFactory) {
        this.useCaseFactory = useCaseFactory;
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

    public void getLocalContacts(CompositeDisposable disposable, List<Contact> contactsList, ContactsAdapterFilterable mAdapter) {
        disposable.add(useCaseFactory.getContacts().execute(new ContactObserver(contactsList, mAdapter), new ContactAllUseCase.Params(contactsList)));
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
