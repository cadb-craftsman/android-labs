package com.woowrale.rxjavaretrofit.data.repository;

import com.woowrale.rxjavaretrofit.data.model.Crypto;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiServices {

    @GET("{coin}-usd")
    Observable<Crypto> getCoinData(@Path("coin") String coin);
}
