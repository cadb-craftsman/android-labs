package com.woowrale.mvp.ui.search.local

import android.util.Log
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent
import com.woowrale.mvp.BuildConfig
import com.woowrale.mvp.data.model.Contact
import com.woowrale.mvp.data.repository.ApiService
import com.woowrale.mvp.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalSearchPresenter@Inject constructor() : BasePresenter<LocalSearchView>() {

    private val TAG = LocalSearchPresenter::class.java.simpleName

    fun searchContacts(): DisposableObserver<TextViewTextChangeEvent> {
        return object : DisposableObserver<TextViewTextChangeEvent>() {
            override fun onNext(textViewTextChangeEvent: TextViewTextChangeEvent) {
                Log.d(TAG, "Search query: " + textViewTextChangeEvent.text())
                getView().getmAdapter().filter.filter(textViewTextChangeEvent.text())
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "onError: " + e.message)
            }

            override fun onComplete() {

            }
        }
    }


    fun fetchContacts(source: String) {
        getView().getDisposable().add(ApiService.services()
            .getContacts(BuildConfig.GET_CONTACTS, source, "")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<Contact>>() {
                override fun onSuccess(contacts: List<Contact>) {
                    getView().setContactsList(contacts)
                    getView().getmAdapter().notifyDataSetChanged()
                }

                override fun onError(e: Throwable) {

                }
            })
        )
    }
}