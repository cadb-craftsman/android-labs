package com.woowrale.jroomapp.ui.search.home;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.woowrale.jroomapp.R;
import com.woowrale.jroomapp.ui.base.BaseActivity;
import com.woowrale.jroomapp.ui.search.local.LocalSearchActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity {

    @Inject
    SearchViewModel model;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDagger();

        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

    }

    protected void initDagger() {
        super.getDaggerMainComponent().inject(this);
    }

    @OnClick(R.id.btn_local_search)
    public void openLocalSearch() {
        startActivity(model.navigationTo(this, LocalSearchActivity.class).getValue());
    }

}
