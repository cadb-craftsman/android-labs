package com.woowrale.instantsearch.kapp.ui.search.home

import com.woowrale.instantsearch.kapp.ui.base.BasePresenter

class SearchPresenter(private val searchActivity: SearchActivity) : BasePresenter<SearchView>() {

    fun navigateToSearch(searchType: String) {
        if (searchType == "L") {
            searchActivity.navigateToSearchLocal()
        } else {
            searchActivity.navigateToSearchRemote()
        }
    }
}