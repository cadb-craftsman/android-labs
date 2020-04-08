package com.woowrale.rxdiretrofit.ui.main;

import com.woowrale.rxdiretrofit.data.model.Crypto;
import com.woowrale.rxdiretrofit.data.repository.ApiService;
import com.woowrale.rxdiretrofit.ui.base.BasePresenter;

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
