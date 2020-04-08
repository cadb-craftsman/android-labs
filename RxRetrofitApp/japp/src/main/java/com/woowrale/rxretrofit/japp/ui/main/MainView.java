package com.woowrale.rxretrofit.japp.ui.main;

import com.woowrale.rxretrofit.japp.data.model.Crypto;
import com.woowrale.rxretrofit.japp.ui.base.BasePresenter;

import java.util.List;

public interface MainView extends BasePresenter.View {

    void showListView(List<Crypto.Market> marketList);

}
