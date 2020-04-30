package com.woowrale.kroomapp.di.components

import com.woowrale.kroomapp.di.modules.MainModule
import com.woowrale.kroomapp.ui.base.BaseActivity
import com.woowrale.kroomapp.ui.search.home.SearchActivity
import com.woowrale.kroomapp.ui.search.local.LocalSearchActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MainModule::class])
interface MainComponent {

    fun inject(activity: BaseActivity)

    fun inject(activity: SearchActivity)

    fun inject(activity: LocalSearchActivity)

}