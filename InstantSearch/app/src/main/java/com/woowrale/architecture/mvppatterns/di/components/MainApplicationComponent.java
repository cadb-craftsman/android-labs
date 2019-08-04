package com.woowrale.architecture.mvppatterns.di.components;

import com.woowrale.architecture.mvppatterns.di.modules.ApiServiceModule;
import com.woowrale.architecture.mvppatterns.di.modules.MainApplicationModule;
import com.woowrale.architecture.mvppatterns.ui.search.SearchActivity;
import com.woowrale.architecture.mvppatterns.ui.search.local.LocalSearchActivity;
import com.woowrale.architecture.mvppatterns.ui.search.remote.RemoteSearchActivity;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {MainApplicationModule.class, ApiServiceModule.class})
public interface MainApplicationComponent {

    void inject(SearchActivity activity);

    void inject(LocalSearchActivity activity);

    void inject(RemoteSearchActivity activity);

}
