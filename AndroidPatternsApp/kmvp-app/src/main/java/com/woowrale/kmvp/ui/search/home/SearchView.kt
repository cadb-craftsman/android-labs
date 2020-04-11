package com.woowrale.kmvp.ui.search.home

import com.woowrale.kmvp.ui.base.BasePresenter

interface SearchView : BasePresenter.View {

    fun navigateToSearchLocal()

    fun navigateToSearchRemote()
}