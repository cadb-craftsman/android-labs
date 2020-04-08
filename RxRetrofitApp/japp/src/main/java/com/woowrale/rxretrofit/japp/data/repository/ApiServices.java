package com.woowrale.rxretrofit.japp.data.repository;

import com.woowrale.rxretrofit.japp.data.model.Crypto;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiServices {

    @GET("{coin}-usd")
    Observable<Crypto> getCoinData(@Path("coin") String coin);
}
