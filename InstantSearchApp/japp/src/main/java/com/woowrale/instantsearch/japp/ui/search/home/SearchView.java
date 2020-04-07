package com.woowrale.instantsearch.japp.ui.search.home;

import com.woowrale.mvp.ui.base.BasePresenter;

public interface SearchView extends BasePresenter.View {

    void navigateToSearchLocal();

    void navigateToSearchRemote();
}
