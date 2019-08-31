package com.woowrale.rxkotlinretrofit.ui.main

import com.woowrale.rxkotlinretrofit.data.model.Market
import com.woowrale.rxkotlinretrofit.ui.base.BasePresenter

interface MainView: BasePresenter.View {
    fun setRecyclerViewAdapterData(marketList: List<Market>)
}