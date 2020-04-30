package com.woowrale.jroomapp.ui.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.woowrale.jroomapp.MainApplication;
import com.woowrale.jroomapp.di.components.MainComponent;

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
