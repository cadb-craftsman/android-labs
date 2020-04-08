package com.woowrale.mvp.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.woowrale.mvp.MainApplication;
import com.woowrale.mvp.di.components.MainComponent;

public abstract class BaseActivity extends AppCompatActivity implements BasePresenter.View {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDaggerMainComponent().inject(this);
    }

    protected MainComponent getDaggerMainComponent() {
        return ((MainApplication) getApplication()).getMainComponent();
    }

    protected abstract void initDagger();

}
