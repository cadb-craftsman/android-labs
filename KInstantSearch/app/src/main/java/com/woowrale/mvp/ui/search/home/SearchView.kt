package com.woowrale.mvp.ui.search.home

import com.woowrale.mvp.ui.base.BasePresenter

interface SearchView : BasePresenter.View {

    fun navigateToSearchLocal()

    fun navigateToSearchRemote()
}