package com.woowrale.jcleanarchitecture.ui.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.woowrale.jcleanarchitecture.MainApplication;
import com.woowrale.jcleanarchitecture.di.components.MainComponent;

public abstract class BaseActivity extends AppCompatActivity {

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
