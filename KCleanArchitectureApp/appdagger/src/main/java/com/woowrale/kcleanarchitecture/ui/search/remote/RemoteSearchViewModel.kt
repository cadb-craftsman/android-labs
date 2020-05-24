package com.woowrale.kcleanarchitecture.ui.search.remote

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent
import com.woowrale.domain.model.Contact
import com.woowrale.kcleanarchitecture.di.factory.UseCaseFactory
import com.woowrale.kcleanarchitecture.ui.adapters.ContactsAdapterFilterable
import com.woowrale.kcleanarchitecture.ui.model.ContactUI
import com.woowrale.usecase.observers.Observer
import com.woowrale.usecase.usecases.GetContactsUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import java.util.*
import javax.inject.Inject

class RemoteSearchViewModel @Inject constructor(private val useCaseFactory: UseCaseFactory) : ViewModel() {

    private val TAG = RemoteSearchViewModel::class.java.simpleName
    private var navigation: MutableLiveData<Intent> = MutableLiveData()
    private val textSearch: MutableLiveData<DisposableObserver<TextViewTextChangeEvent>> = MutableLiveData()

    fun navigationTo(context: Context, navigationClass: Class<*>, contactUI: ContactUI): LiveData<Intent> {
        val intent: Intent = Intent(context, navigationClass)
        intent.putExtra("contact", contactUI)
        navigation.value = intent
        return navigation
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

    fun getContacts(
        disposable: CompositeDisposable,
        contacts: List<Contact>,
        mAdapter: ContactsAdapterFilterable
    ) {
        var contactObserver = ContactObserver(contacts as ArrayList<Contact>, mAdapter)
        var params = GetContactsUseCase.Params()
        disposable.add(useCaseFactory.getRemoteContacts().execute(contactObserver, params))
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

        override fun onError(e: Throwable) {}
    }
}