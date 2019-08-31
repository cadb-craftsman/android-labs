package com.woowrale.rxjavaretrofit.ui.main;

import com.woowrale.rxjavaretrofit.data.model.Crypto;
import com.woowrale.rxjavaretrofit.ui.base.BasePresenter;

import java.util.List;

public interface MainView extends BasePresenter.View {

    void showListView(List<Crypto.Market> marketList);

}
