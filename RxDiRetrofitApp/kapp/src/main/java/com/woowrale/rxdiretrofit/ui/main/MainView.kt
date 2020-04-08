package com.woowrale.rxdiretrofit.ui.main

import com.woowrale.rxdiretrofit.data.model.Market
import com.woowrale.rxdiretrofit.ui.base.BasePresenter


interface MainView: BasePresenter.View {
    fun setRecyclerViewAdapterData(marketList: List<Market>)
}