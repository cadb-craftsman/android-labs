package com.woowrale.architecture.mvp.ui.search.home;

import com.woowrale.architecture.mvp.ui.base.BasePresenter;

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
