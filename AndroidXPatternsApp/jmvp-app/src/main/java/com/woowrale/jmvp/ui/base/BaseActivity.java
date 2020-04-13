package com.woowrale.jmvp.ui.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.woowrale.jmvp.MainApplication;
import com.woowrale.jmvp.di.components.MainComponent;

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
