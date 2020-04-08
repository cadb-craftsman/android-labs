package com.woowrale.rxretrofit.kapp.ui.main

import com.woowrale.rxretrofit.kapp.data.model.Market
import com.woowrale.rxretrofit.kapp.ui.base.BasePresenter

interface MainView: BasePresenter.View {
    fun setRecyclerViewAdapterData(marketList: List<Market>)
}