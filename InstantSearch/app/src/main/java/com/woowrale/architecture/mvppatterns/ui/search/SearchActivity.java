package com.woowrale.architecture.mvppatterns.ui.search;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.woowrale.architecture.mvppatterns.R;
import com.woowrale.architecture.mvppatterns.ui.base.BaseActivity;
import com.woowrale.architecture.mvppatterns.ui.search.local.LocalSearchActivity;
import com.woowrale.architecture.mvppatterns.ui.search.remote.RemoteSearchActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity implements SearchView {

    private SearchPresenter presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        whiteNotificationBar(toolbar);

        presenter = new SearchPresenter(this);
    }

    @OnClick(R.id.btn_local_search)
    public void openLocalSearch() {
        presenter.navigateToSearch("L");
    }

    @OnClick(R.id.btn_remote_search)
    public void openRemoteSearch() {
        presenter.navigateToSearch("R");
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
