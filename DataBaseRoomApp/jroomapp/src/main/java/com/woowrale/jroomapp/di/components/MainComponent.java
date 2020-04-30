package com.woowrale.jroomapp.di.components;

import com.woowrale.jroomapp.di.modules.MainModule;
import com.woowrale.jroomapp.ui.base.BaseActivity;
import com.woowrale.jroomapp.ui.search.home.SearchActivity;
import com.woowrale.jroomapp.ui.search.local.LocalSearchActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MainModule.class})
public interface MainComponent {

    void inject(BaseActivity activity);

    void inject(SearchActivity activity);

    void inject(LocalSearchActivity activity);

}
