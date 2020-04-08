package com.woowrale.rxdiretrofit.ui.main;

import com.woowrale.rxdiretrofit.data.model.Crypto;
import com.woowrale.rxdiretrofit.ui.base.BasePresenter;


import java.util.List;

public interface MainView extends BasePresenter.View {

    void showListView(List<Crypto.Market> marketList);

}
