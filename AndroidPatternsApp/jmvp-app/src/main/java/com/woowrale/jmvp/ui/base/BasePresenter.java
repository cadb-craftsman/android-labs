package com.woowrale.jmvp.ui.base;

import android.support.annotation.NonNull;

public abstract class BasePresenter<V extends BasePresenter.View> {

    private V mView;

    public V getView() {
        return mView;
    }

    public void setView(@NonNull V view) { mView = view; }

    public interface View {

    }
}
