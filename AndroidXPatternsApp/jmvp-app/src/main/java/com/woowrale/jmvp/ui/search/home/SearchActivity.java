package com.woowrale.jmvp.ui.search.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.woowrale.jmvp.R;
import com.woowrale.jmvp.ui.base.BaseActivity;
import com.woowrale.jmvp.ui.search.local.LocalSearchActivity;
import com.woowrale.jmvp.ui.search.remote.RemoteSearchActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity implements SearchView {

    @Inject
    SearchPresenter mPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDagger();

        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        whiteNotificationBar(toolbar);

        mPresenter.setView(this);

    }

    protected void initDagger() {
        super.getDaggerMainComponent().inject(this);
    }

    @OnClick(R.id.btn_local_search)
    public void openLocalSearch() {
        mPresenter.navigateToSearch("L");
    }

    @OnClick(R.id.btn_remote_search)
    public void openRemoteSearch() {
        mPresenter.navigateToSearch("R");
    }

    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    @Override
    public void navigateToSearchLocal() {
        startActivity(new Intent(SearchActivity.this, LocalSearchActivity.class));
    }

    @Override
    public void navigateToSearchRemote() {
        startActivity(new Intent(SearchActivity.this, RemoteSearchActivity.class));
    }
}
