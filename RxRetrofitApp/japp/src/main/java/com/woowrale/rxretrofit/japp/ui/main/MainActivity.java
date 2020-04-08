package com.woowrale.rxretrofit.japp.ui.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.woowrale.rxretrofit.japp.R;
import com.woowrale.rxretrofit.japp.data.model.Crypto;
import com.woowrale.rxretrofit.japp.ui.adapter.RecyclerViewAdapter;
import com.woowrale.rxretrofit.japp.ui.base.BaseActivity;

import java.util.List;

public class MainActivity extends BaseActivity implements MainView {

    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    MainPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(recyclerViewAdapter);

        mPresenter = new MainPresenter();
        mPresenter.setView(this);
        mPresenter.onSearchCryptoCurrency();

    }

    @Override
    public void showMessageError() {
        Toast.makeText(this, "ERROR IN FETCHING API RESPONSE. Try again", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessageResult() {
        Toast.makeText(this, "NO RESULTS FOUND", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showListView(List<Crypto.Market> marketList) {
        recyclerViewAdapter.setData(marketList);
    }
}
