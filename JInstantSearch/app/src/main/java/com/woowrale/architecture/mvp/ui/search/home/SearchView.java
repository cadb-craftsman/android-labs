package com.woowrale.architecture.mvp.ui.search.home;

import com.woowrale.architecture.mvp.ui.base.BasePresenter;

public interface SearchView extends BasePresenter.View {

    void navigateToSearchLocal();

    void navigateToSearchRemote();
}
