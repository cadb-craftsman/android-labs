package com.woowrale.kmvp.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.woowrale.kmvp.MainApplication
import com.woowrale.kmvp.di.components.MainComponent

abstract class BaseActivity : AppCompatActivity(), BasePresenter.View {

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

abstract class BasePresenter<T : BasePresenter.View> {

    private lateinit var mView: T

    protected fun getView(): T {
        return mView
    }

    fun setView(view: T) {
        mView = view
    }

    interface View {

    }
}