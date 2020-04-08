package com.woowrale.rxretrofit.kapp.ui.main

import android.annotation.SuppressLint
import android.util.Log
import com.woowrale.rxretrofit.kapp.data.model.Crypto
import com.woowrale.rxretrofit.kapp.data.repository.ApiServices
import com.woowrale.rxretrofit.kapp.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainPresenter : BasePresenter<MainView>() {

    val TAG_NAME: String = this.javaClass.canonicalName
    var compositeDisposable: CompositeDisposable? = null

    @SuppressLint("CheckResult")
    fun onCallEndPoint() {
        compositeDisposable = CompositeDisposable()
        compositeDisposable?.add(ApiServices.service().getCoinData("btc")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({t -> handleResults(t)}))
    }

    private fun handleResults(crypto: Crypto) {

        if (crypto != null) {
             getView()?.setRecyclerViewAdapterData(crypto.ticker?.markets!!)
        } else {
            getView()?.showMessageResult()
        }
    }

    private fun handleError(t: Throwable) {
        Log.e(TAG_NAME, t.message)
        getView()?.showMessageError()
    }
}