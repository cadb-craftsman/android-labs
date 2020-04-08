package com.woowrale.instantsearch.kapp.ui.search.home

import com.woowrale.instantsearch.kapp.ui.base.BasePresenter

interface SearchView : BasePresenter.View {

    fun navigateToSearchLocal()

    fun navigateToSearchRemote()
}