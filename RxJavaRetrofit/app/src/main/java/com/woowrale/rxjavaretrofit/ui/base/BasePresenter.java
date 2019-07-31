package com.woowrale.rxjavaretrofit.ui.base;

import android.support.annotation.NonNull;

public abstract class BasePresenter<T extends BasePresenter.View> {

    private T mView;
    private static final String TAG = BasePresenter.class.getName();

    protected T getView() {
        return mView;
    }

    public void setView(@NonNull T view) {
        mView = view;
    }

    public interface View {

        void showMessageError();

        void showMessageResult();
    }
}