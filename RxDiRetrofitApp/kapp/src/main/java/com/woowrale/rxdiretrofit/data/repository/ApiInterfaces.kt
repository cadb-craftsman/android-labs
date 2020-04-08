package com.woowrale.rxdiretrofit.data.repository

import com.woowrale.rxdiretrofit.data.model.Crypto
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServicesInterface {

    @GET("{coin}-usd")
    fun getCoinData(@Path("coin") coin: String): Observable<Crypto>

}