package com.woowrale.jmvp.di.components;

import com.woowrale.jmvp.ui.base.BaseActivity;
import com.woowrale.jmvp.ui.search.home.SearchActivity;
import com.woowrale.jmvp.ui.search.local.LocalSearchActivity;
import com.woowrale.jmvp.ui.search.remote.RemoteSearchActivity;
import com.woowrale.jmvp.di.modules.MainModule;
import com.woowrale.jmvp.di.modules.ServicesModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MainModule.class, ServicesModule.class})
public interface MainComponent {

    void inject(BaseActivity activity);

    void inject(SearchActivity activity);

    void inject(LocalSearchActivity activity);

    void inject(RemoteSearchActivity activity);

}
