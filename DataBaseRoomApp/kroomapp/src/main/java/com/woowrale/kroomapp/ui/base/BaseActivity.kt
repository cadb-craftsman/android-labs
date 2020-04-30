package com.woowrale.kroomapp.ui.base

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.woowrale.kroomapp.MainApplication
import com.woowrale.kroomapp.di.components.MainComponent

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getDaggerMainComponent().inject(this)
    }

    protected fun getDaggerMainComponent(): MainComponent {
        val mainApplication: MainApplication = application as MainApplication
        return mainApplication.mainComponent
    }

    protected abstract fun initDagger()
}