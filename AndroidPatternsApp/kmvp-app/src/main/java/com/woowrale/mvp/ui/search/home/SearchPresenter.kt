package com.woowrale.mvp.ui.search.home

import com.woowrale.mvp.ui.base.BasePresenter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchPresenter @Inject constructor() : BasePresenter<SearchView>() {

    fun navigateToSearch(searchType: String) {
        if (searchType == "L") {
            getView().navigateToSearchLocal()
        } else {
            getView().navigateToSearchRemote()
        }
    }
}
