package com.woowrale.rxkotlinretrofit.ui.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.woowrale.rxkotlinretrofit.R
import com.woowrale.rxkotlinretrofit.data.model.Market
import com.woowrale.rxkotlinretrofit.ui.adapters.RecyclerViewAdapter
import com.woowrale.rxkotlinretrofit.ui.base.BaseActivity

class MainActivity : BaseActivity(), MainView {

    lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var mPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPresenter = MainPresenter()
        mPresenter.setView(this)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerViewAdapter = RecyclerViewAdapter()
        recyclerView.setAdapter(recyclerViewAdapter)

        mPresenter.onCallEndPoint()
    }

    override fun setRecyclerViewAdapterData(marketList: List<Market>) {
        recyclerViewAdapter.setData(marketList)
    }

    override fun showMessageResult() {
        Toast.makeText(this, "NO RESULTS FOUND", Toast.LENGTH_LONG).show()
    }

    override fun showMessageError() {
        Toast.makeText(this, "ERROR IN FETCHING API RESPONSE. Try again", Toast.LENGTH_LONG).show()
    }

}
