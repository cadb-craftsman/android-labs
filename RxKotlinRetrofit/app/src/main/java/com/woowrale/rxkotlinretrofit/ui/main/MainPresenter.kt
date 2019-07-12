package com.woowrale.rxkotlinretrofit.ui.main

import android.annotation.SuppressLint
import android.util.Log
import com.woowrale.rxkotlinretrofit.data.model.Market
import com.woowrale.rxkotlinretrofit.data.repository.ApiServices
import com.woowrale.rxkotlinretrofit.data.repository.RestClient
import com.woowrale.rxkotlinretrofit.ui.base.BasePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter : BasePresenter<MainView>() {

    val TAG_NAME: String = this.javaClass.canonicalName

    @SuppressLint("CheckResult")
    fun onCallEndPoint() {
        val cryptocurrencyService = RestClient.getRetrofit().create(ApiServices::class.java)

        //Single call
        /*Observable<Crypto> cryptoObservable = cryptocurrencyService.getCoinData("btc");
        cryptoObservable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).map(result -> result.ticker).subscribe(this::handleResults, this::handleError);*/
        val btcObservable = cryptocurrencyService.getCoinData("btc")
            .map { result -> Observable.fromIterable(result.ticker?.markets) }
            .flatMap({ x -> x }).filter({ y ->
                y.coinName = "btc"
                true
            }).toList().toObservable()
        /*
        val ethObservable = cryptocurrencyService.getCoinData("eth")
            .map { result -> Observable.fromIterable(result.ticker?.markets) }
            .flatMap({ x -> x }).filter({ y ->
                y.coinName = "eth"
                true
            }).toList().toObservable()
        */
        Observable.merge<List<Market>>(btcObservable, btcObservable)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ this.handleResults(it) }, { this.handleError(it) })
    }

    private fun handleResults(marketList: List<Market>?) {
        if (marketList != null && marketList.size != 0) {
            getView()?.setRecyclerViewAdapterData(marketList)
        } else {
            getView()?.showMessageResult()
        }
    }

    private fun handleError(t: Throwable) {
        Log.e(TAG_NAME, t.message)
        getView()?.showMessageError()
    }
}