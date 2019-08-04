package com.woowrale.architecture.mvppatterns.ui.search;

import com.woowrale.architecture.mvppatterns.ui.base.BasePresenter;

public class SearchPresenter extends BasePresenter<SearchView> {

    private SearchActivity searchActivity;

    public SearchPresenter(SearchActivity searchActivity) {
        this.searchActivity = searchActivity;
    }

    public void navigateToSearch(String searchType){
        if (searchType.equals("L")){
            searchActivity.navigateToSearchLocal();
        }else{
            searchActivity.navigateToSearchRemote();
        }
    }
}
