package com.woowrale.jcleanarchitecture.ui.search.remote;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;
import com.woowrale.domain.model.Contact;
import com.woowrale.jcleanarchitecture.BuildConfig;
import com.woowrale.jcleanarchitecture.di.factory.UseCaseFactory;
import com.woowrale.jcleanarchitecture.ui.adapters.ContactsAdapterFilterable;
import com.woowrale.usecase.observers.Observer;
import com.woowrale.usecase.usescases.GetContactsUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public class RemoteSearchViewModel extends ViewModel {
    private static final String TAG = RemoteSearchViewModel.class.getSimpleName();

    private UseCaseFactory useCaseFactory;
    private MutableLiveData<Intent> navigation;
    private MutableLiveData<DisposableObserver<TextViewTextChangeEvent>> textSearch;

    @Inject
    public RemoteSearchViewModel(UseCaseFactory useCaseFactory) {
        this.useCaseFactory = useCaseFactory;
        navigation = new MutableLiveData<Intent>();
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

    public LiveData<Intent> navigationTo(Context context, Class navigationClass){
        Intent intent = new Intent(context, navigationClass);
        navigation.setValue(intent);
        return navigation;
    }

    public void getRemoteContacts(CompositeDisposable disposable, String source, List<Contact> contactsList, ContactsAdapterFilterable mAdapter) {
        disposable.add(useCaseFactory.getRemoteContacts().execute(new RemoteSearchViewModel.ContactObserver(contactsList, mAdapter), new GetContactsUseCase.Params(BuildConfig.GET_CONTACTS, source, null)));
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
