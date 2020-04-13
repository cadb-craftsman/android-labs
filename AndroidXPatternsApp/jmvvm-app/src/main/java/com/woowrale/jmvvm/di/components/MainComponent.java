package com.woowrale.jmvvm.di.components;

import com.woowrale.jmvvm.di.modules.MainModule;
import com.woowrale.jmvvm.di.modules.ServicesModule;
import com.woowrale.jmvvm.ui.base.BaseActivity;
import com.woowrale.jmvvm.ui.search.home.SearchActivity;
import com.woowrale.jmvvm.ui.search.local.LocalSearchActivity;
import com.woowrale.jmvvm.ui.search.remote.RemoteSearchActivity;

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
