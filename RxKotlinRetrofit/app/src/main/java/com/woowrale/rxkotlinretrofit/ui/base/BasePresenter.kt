package com.woowrale.rxkotlinretrofit.ui.base

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