package com.woowrale.kmvvm.ui.search.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent
import com.woowrale.kmvvm.data.model.Contact
import com.woowrale.kmvvm.ui.adapters.ContactsAdapter
import io.reactivex.observers.DisposableObserver
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class RemoteSearchViewModel @Inject constructor() : ViewModel() {

    private val TAG = RemoteSearchViewModel::class.java.simpleName
    private var search : MutableLiveData<DisposableObserver<List<Contact>>> = MutableLiveData()


    fun searchObserver(contactList: ArrayList<Contact>, mAdapter: ContactsAdapter):  LiveData<DisposableObserver<List<Contact>>> {
        search.value = object : DisposableObserver<List<Contact>>() {
            override fun onNext(contacts: List<Contact>) {
                contactList.clear()
                contactList.addAll(contacts)
                mAdapter.notifyDataSetChanged()
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "onError: " + e.message)
            }

            override fun onComplete() {

            }
        }
        return search
    }

    fun searchContactsTextWatcher(publishSubject: PublishSubject<String>): DisposableObserver<TextViewTextChangeEvent> {
        return object : DisposableObserver<TextViewTextChangeEvent>() {
            override fun onNext(textViewTextChangeEvent: TextViewTextChangeEvent) {
                Log.d(TAG, "Search query: " + textViewTextChangeEvent.text())
                publishSubject.onNext(textViewTextChangeEvent.text().toString())
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "onError: " + e.message)
            }

            override fun onComplete() {

            }
        }
    }
}