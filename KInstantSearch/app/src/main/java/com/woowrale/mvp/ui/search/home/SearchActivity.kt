package com.woowrale.mvp.ui.search.home

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import com.woowrale.mvp.R
import com.woowrale.mvp.ui.base.BaseActivity
import com.woowrale.mvp.ui.search.local.LocalSearchActivity
import com.woowrale.mvp.ui.search.remote.RemoteSearchActivity
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.content_search.*

class SearchActivity : BaseActivity(),
    SearchView {

    private var presenter: SearchPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setSupportActionBar(toolbar)
        whiteNotificationBar(toolbar)

        presenter = SearchPresenter(this)
        btnLocalSearch.setOnClickListener {
            presenter!!.navigateToSearch("L")
        }

        btnRemoteSearch.setOnClickListener {
            presenter!!.navigateToSearch("R")
        }
    }

    private fun whiteNotificationBar(view: View?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var flags = view!!.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            view.systemUiVisibility = flags
            getWindow().setStatusBarColor(Color.WHITE)
        }
    }

    override fun navigateToSearchLocal() {
        startActivity(Intent(this@SearchActivity, LocalSearchActivity::class.java))
    }

    override fun navigateToSearchRemote() {
        startActivity(Intent(this@SearchActivity, RemoteSearchActivity::class.java))
    }
}