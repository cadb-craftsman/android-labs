package com.woowrale.jmvvm.ui.search.home;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.woowrale.jmvvm.R;
import com.woowrale.jmvvm.ui.base.BaseActivity;
import com.woowrale.jmvvm.ui.search.local.LocalSearchActivity;
import com.woowrale.jmvvm.ui.search.remote.RemoteSearchActivity;

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
        whiteNotificationBar(toolbar);
    }

    protected void initDagger() {
        super.getDaggerMainComponent().inject(this);
    }

    @OnClick(R.id.btn_local_search)
    public void openLocalSearch() {
        startActivity(model.navigationTo(this, LocalSearchActivity.class).getValue());
    }

    @OnClick(R.id.btn_remote_search)
    public void openRemoteSearch() {
        startActivity(model.navigationTo(this, RemoteSearchActivity.class).getValue());
    }

    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }
}
