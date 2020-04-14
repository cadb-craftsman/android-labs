package com.woowrale.kmvvm.di.components

import com.woowrale.kmvvm.di.modules.MainModule
import com.woowrale.kmvvm.di.modules.ServicesModule
import com.woowrale.kmvvm.ui.base.BaseActivity
import com.woowrale.kmvvm.ui.search.home.SearchActivity
import com.woowrale.kmvvm.ui.search.local.LocalSearchActivity
import com.woowrale.kmvvm.ui.search.remote.RemoteSearchActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MainModule::class, ServicesModule::class])
interface MainComponent {

    fun inject(activity: BaseActivity)

    fun inject(activity: SearchActivity)

    fun inject(activity: LocalSearchActivity)

    fun inject(activity: RemoteSearchActivity)

}