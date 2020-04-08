package com.woowrale.mvp.ui.search.remote

import android.util.Log
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent
import com.woowrale.mvp.data.model.Contact
import com.woowrale.mvp.ui.base.BasePresenter
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteSearchPresenter@Inject constructor() : BasePresenter<RemoteSearchView>() {

    private val TAG = RemoteSearchPresenter::class.java.simpleName

    val searchObserver: DisposableObserver<List<Contact>>
        get() = object : DisposableObserver<List<Contact>>() {
            override fun onNext(contacts: List<Contact>) {
                getView().setContactsList(contacts)
                getView().getmAdapter().notifyDataSetChanged()
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "onError: " + e.message)
            }

            override fun onComplete() {

            }
        }

    fun searchContactsTextWatcher(): DisposableObserver<TextViewTextChangeEvent> {
        return object : DisposableObserver<TextViewTextChangeEvent>() {
            override fun onNext(textViewTextChangeEvent: TextViewTextChangeEvent) {
                Log.d(TAG, "Search query: " + textViewTextChangeEvent.text())
                getView().getPublishSubject().onNext(textViewTextChangeEvent.text().toString())
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "onError: " + e.message)
            }

            override fun onComplete() {

            }
        }
    }
}