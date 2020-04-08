package com.woowrale.rxdiretrofit.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(), BasePresenter.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}

abstract class BasePresenter<T : BasePresenter.View> {

    private var mView: T? = null

    protected fun getView(): T? {
        return mView
    }

    fun setView(view: T?) {
        mView = view
    }

    interface View {

        fun showMessageError()

        fun showMessageResult()
    }
}