package com.woowrale.kroomapp.ui.search.local

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent
import com.woowrale.kroomapp.di.factory.UseCaseFactory
import com.woowrale.kroomapp.domain.model.Contact
import com.woowrale.kroomapp.ui.adapters.ContactsAdapterFilterable
import com.woowrale.kroomapp.usecases.ContactAllUseCase
import com.woowrale.kroomapp.usecases.observers.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import java.util.ArrayList
import javax.inject.Inject

class LocalSearchViewModel @Inject constructor(useCaseFactory: UseCaseFactory) : ViewModel() {
    private val useCaseFactory: UseCaseFactory = useCaseFactory
    private val textSearch: MutableLiveData<DisposableObserver<TextViewTextChangeEvent>> = MutableLiveData()

    companion object {
        private val TAG = LocalSearchViewModel::class.java.simpleName
    }

    fun searchContacts(mAdapter: ContactsAdapterFilterable): LiveData<DisposableObserver<TextViewTextChangeEvent>> {
        textSearch.setValue(object : DisposableObserver<TextViewTextChangeEvent>() {
            override fun onNext(textViewTextChangeEvent: TextViewTextChangeEvent) {
                Log.d(
                    TAG,
                    "Search query: " + textViewTextChangeEvent.text()
                )
                mAdapter.getFilter().filter(textViewTextChangeEvent.text())
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "onError: " + e.message)
            }

            override fun onComplete() {}
        })
        return textSearch
    }

    fun getLocalContacts(
        disposable: CompositeDisposable,
        contacts: List<Contact>,
        mAdapter: ContactsAdapterFilterable
    ) {
        var contactObserver = ContactObserver(contacts as ArrayList<Contact>, mAdapter)
        var params = ContactAllUseCase.Params(contacts)
        disposable.add(useCaseFactory.getContactsUsesCases().execute(contactObserver, params))
    }

    class ContactObserver constructor(
        contactsList: ArrayList<Contact>,
        adapter: ContactsAdapterFilterable
    ) : Observer<List<Contact>>() {

        private val mAdapter: ContactsAdapterFilterable = adapter
        private val contactList: ArrayList<Contact> = contactsList

        override fun onSuccess(t: List<Contact>) {
            contactList.clear()
            contactList.addAll(t)
            mAdapter.notifyDataSetChanged()
        }

        override fun onError(e: Throwable) { }
    }
}