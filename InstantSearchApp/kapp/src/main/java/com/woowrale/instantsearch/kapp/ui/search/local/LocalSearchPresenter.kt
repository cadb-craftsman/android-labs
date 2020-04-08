package com.woowrale.instantsearch.kapp.ui.search.local

import android.util.Log
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent
import com.woowrale.instantsearch.kapp.BuildConfig
import com.woowrale.instantsearch.kapp.data.model.Contact
import com.woowrale.instantsearch.kapp.data.repository.ApiService
import com.woowrale.instantsearch.kapp.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

class LocalSearchPresenter(view: LocalSearchActivity, retrofit: Retrofit) :
    BasePresenter<LocalSearchView>() {

    private val view: LocalSearchView
    private val apiService: ApiService

    init {
        this.view = view
        this.apiService = retrofit.create<ApiService>(ApiService::class.java!!)
    }

    fun searchContacts(): DisposableObserver<TextViewTextChangeEvent> {
        return object : DisposableObserver<TextViewTextChangeEvent>() {
            override fun onNext(textViewTextChangeEvent: TextViewTextChangeEvent) {
                Log.d(TAG, "Search query: " + textViewTextChangeEvent.text())
                view.getmAdapter().filter.filter(textViewTextChangeEvent.text())
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "onError: " + e.message)
            }

            override fun onComplete() {

            }
        }
    }


    fun fetchContacts(source: String) {
        view.disposable.add(apiService
            .getContacts(BuildConfig.GET_CONTACTS, source, "")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<Contact>>() {
                override fun onSuccess(contacts: List<Contact>) {
                    view.setContactsList(contacts)
                    view.getmAdapter().notifyDataSetChanged()
                }

                override fun onError(e: Throwable) {

                }
            })
        )
    }

    companion object {

        private val TAG = LocalSearchPresenter::class.java.simpleName
    }

}