package com.woowrale.instantsearch.kapp.ui.search.remote

import android.util.Log
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent
import com.woowrale.instantsearch.kapp.data.model.Contact
import com.woowrale.instantsearch.kapp.data.repository.ApiService
import com.woowrale.instantsearch.kapp.ui.base.BasePresenter

import io.reactivex.observers.DisposableObserver
import retrofit2.Retrofit

class RemoteSearchPresenter(view: RemoteSearchActivity, retrofit: Retrofit) :
    BasePresenter<RemoteSearchView>() {

    private val view: RemoteSearchView
    val apiService: ApiService

    val searchObserver: DisposableObserver<List<Contact>>
        get() = object : DisposableObserver<List<Contact>>() {
            override fun onNext(contacts: List<Contact>) {
                view.setContactsList(contacts)
                view.getmAdapter().notifyDataSetChanged()
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "onError: " + e.message)
            }

            override fun onComplete() {

            }
        }

    init {
        this.view = view
        apiService = retrofit.create<ApiService>(ApiService::class.java!!)
    }

    fun searchContactsTextWatcher(): DisposableObserver<TextViewTextChangeEvent> {
        return object : DisposableObserver<TextViewTextChangeEvent>() {
            override fun onNext(textViewTextChangeEvent: TextViewTextChangeEvent) {
                Log.d(TAG, "Search query: " + textViewTextChangeEvent.text())
                view.publishSubject.onNext(textViewTextChangeEvent.text().toString())
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "onError: " + e.message)
            }

            override fun onComplete() {

            }
        }
    }

    companion object {

        private val TAG = RemoteSearchPresenter::class.java.simpleName
    }
}
