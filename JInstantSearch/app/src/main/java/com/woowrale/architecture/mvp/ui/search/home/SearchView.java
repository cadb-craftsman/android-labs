package com.woowrale.architecture.mvp.ui.search.home;

import com.woowrale.architecture.mvp.ui.base.IView;

public interface SearchView extends IView {

    void navigateToSearchLocal();

    void navigateToSearchRemote();
}
