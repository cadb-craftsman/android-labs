package com.woowrale.jcleanarchitecture.di.components;

import com.woowrale.jcleanarchitecture.di.modules.MainModule;
import com.woowrale.jcleanarchitecture.ui.base.BaseActivity;
import com.woowrale.jcleanarchitecture.ui.search.home.SearchActivity;
import com.woowrale.jcleanarchitecture.ui.search.local.LocalSearchActivity;
import com.woowrale.jcleanarchitecture.ui.search.remote.RemoteSearchActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MainModule.class})
public interface MainComponent {

    void inject(BaseActivity activity);

    void inject(SearchActivity activity);

    void inject(LocalSearchActivity activity);

    void inject(RemoteSearchActivity activity);

}
