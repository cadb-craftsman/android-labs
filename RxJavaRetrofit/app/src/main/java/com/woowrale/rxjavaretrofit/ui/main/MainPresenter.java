package com.woowrale.rxjavaretrofit.ui.main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.woowrale.rxjavaretrofit.BuildConfig;
import com.woowrale.rxjavaretrofit.data.model.Crypto;
import com.woowrale.rxjavaretrofit.data.repository.ApiRestServices;
import com.woowrale.rxjavaretrofit.ui.base.BasePresenter;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class MainPresenter extends BasePresenter<MainView> {

    public void onSearchCryptoCurrency(){

        Observable<List<Crypto.Market>> btcObservable = ApiRestServices.services().getCoinData("btc")
                .map(result -> Observable.fromIterable(result.ticker.markets))
                .flatMap(x -> x).filter(y -> {
                    y.coinName = "btc";
                    return true;
                }).toList().toObservable();

        Observable<List<Crypto.Market>> ethObservable =  ApiRestServices.services().getCoinData("eth")
                .map(result -> Observable.fromIterable(result.ticker.markets))
                .flatMap(x -> x).filter(y -> {
                    y.coinName = "eth";
                    return true;
                }).toList().toObservable();

        Observable.merge(btcObservable, ethObservable)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResults, this::handleError);
    }


    private void handleResults(List<Crypto.Market> marketList) {
        if (marketList != null && marketList.size() != 0) {
            getView().showListView(marketList);
        } else {
            getView().showMessageResult();
        }
    }

    private void handleError(Throwable t) {
        getView().showMessageError();
    }

}
