package com.woowrale.mvp.ui.search.home

import com.woowrale.mvp.ui.base.BasePresenter

class SearchPresenter(private val searchActivity: SearchActivity) : BasePresenter<SearchView>() {

    fun navigateToSearch(searchType: String) {
        if (searchType == "L") {
            searchActivity.navigateToSearchLocal()
        } else {
            searchActivity.navigateToSearchRemote()
        }
    }
}