package com.woowrale.architecture.mvppatterns.ui.search;

import com.woowrale.architecture.mvppatterns.ui.base.IView;

public interface SearchView extends IView {

    void navigateToSearchLocal();

    void navigateToSearchRemote();
}
