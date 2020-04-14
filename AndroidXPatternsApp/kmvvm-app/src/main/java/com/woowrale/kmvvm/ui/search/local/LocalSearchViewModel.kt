package com.woowrale.kmvvm.ui.search.local

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent
import com.woowrale.kmvvm.BuildConfig
import com.woowrale.kmvvm.data.model.Contact
import com.woowrale.kmvvm.data.repository.ApiService
import com.woowrale.kmvvm.ui.adapters.ContactsAdapterFilterable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class LocalSearchViewModel @Inject constructor() : ViewModel() {
    private var searchText = MutableLiveData<DisposableObserver<TextViewTextChangeEvent>>()
    private val TAG = LocalSearchViewModel::class.java.simpleName

    fun searchContacts(mAdapter: ContactsAdapterFilterable): LiveData<DisposableObserver<TextViewTextChangeEvent>> {
        searchText.value = object : DisposableObserver<TextViewTextChangeEvent>() {
            override fun onNext(textViewTextChangeEvent: TextViewTextChangeEvent) {
                Log.d(TAG, "Search query: " + textViewTextChangeEvent.text())
                mAdapter.filter.filter(textViewTextChangeEvent.text())
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "onError: " + e.message)
            }

            override fun onComplete() {

            }
        }

        return searchText
    }


    fun fetchContacts(disposable: CompositeDisposable, source: String, contactList: ArrayList<Contact>, mAdapter: ContactsAdapterFilterable) {
        disposable.add(ApiService.services()
                .getContacts(BuildConfig.GET_CONTACTS, source, "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Contact>>() {
                    override fun onSuccess(contacts: List<Contact>) {
                        contactList.clear()
                        contactList.addAll(contacts)
                        mAdapter.notifyDataSetChanged()
                    }

                    override fun onError(e: Throwable) {

                    }

                })
        )
    }
}