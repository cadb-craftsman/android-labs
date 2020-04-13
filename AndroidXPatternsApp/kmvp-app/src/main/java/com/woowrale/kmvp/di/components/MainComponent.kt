package com.woowrale.kmvp.di.components

import com.woowrale.kmvp.di.modules.MainModule
import com.woowrale.kmvp.di.modules.ServicesModule
import com.woowrale.kmvp.ui.base.BaseActivity
import com.woowrale.kmvp.ui.search.home.SearchActivity
import com.woowrale.kmvp.ui.search.local.LocalSearchActivity
import com.woowrale.kmvp.ui.search.remote.RemoteSearchActivity
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