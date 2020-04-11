package com.woowrale.jmvp.ui.search.home;

import com.woowrale.jmvp.ui.base.BasePresenter;

public interface SearchView extends BasePresenter.View {

    void navigateToSearchLocal();

    void navigateToSearchRemote();
}
