package com.woowrale.kmvvm.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.woowrale.kmvvm.MainApplication
import com.woowrale.kmvvm.di.components.MainComponent

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getDaggerMainComponent().inject(this)
    }

    protected fun getDaggerMainComponent(): MainComponent {
        val mainApplication = MainApplication()
        mainApplication.onCreate()
        return mainApplication.mainComponent
    }

    protected abstract fun initDagger()
}
