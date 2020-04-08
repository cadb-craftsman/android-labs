package com.woowrale.rxretrofit.japp.ui.main;

import com.woowrale.rxretrofit.japp.data.model.Crypto;
import com.woowrale.rxretrofit.japp.data.repository.ApiService;
import com.woowrale.rxretrofit.japp.ui.base.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<MainView> {

    private CompositeDisposable compositeDisposable;

    public void onSearchCryptoCurrency(){

        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(ApiService.services().getCoinData("btc")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResults));

        /*
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
         */
    }


    private void handleResults(Crypto crypto) {
        if (crypto != null) {
            if(crypto.ticker.markets != null){
                getView().showListView(crypto.ticker.markets);
            }
        } else {
            getView().showMessageResult();
        }
    }

    private void handleError(Throwable t) {
        getView().showMessageError();
    }

}
