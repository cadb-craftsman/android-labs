package com.woowrale.mvp.di.components;

import com.woowrale.mvp.di.modules.MainModule;
import com.woowrale.mvp.di.modules.ServicesModule;
import com.woowrale.mvp.ui.base.BaseActivity;
import com.woowrale.mvp.ui.search.home.SearchActivity;
import com.woowrale.mvp.ui.search.local.LocalSearchActivity;
import com.woowrale.mvp.ui.search.remote.RemoteSearchActivity;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {MainModule.class, ServicesModule.class})
public interface MainComponent {

    void inject(BaseActivity activity);

    void inject(SearchActivity activity);

    void inject(LocalSearchActivity activity);

    void inject(RemoteSearchActivity activity);

}
