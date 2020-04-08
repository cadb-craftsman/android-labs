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
import javax.inject.Inject

class SearchActivity : BaseActivity(), SearchView {

    @Inject
    lateinit var mPresenter: SearchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDagger()

        setContentView(R.layout.activity_search)
        mPresenter.setView(this)

        setSupportActionBar(toolbar)
        whiteNotificationBar(toolbar)

        btnLocalSearch.setOnClickListener({view ->
            mPresenter.navigateToSearch("L")
        })

        btnRemoteSearch.setOnClickListener({view ->
            mPresenter.navigateToSearch("R")
        })
    }

    override fun initDagger() {
        getDaggerMainComponent().inject(this)
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
        startActivity(Intent(this, LocalSearchActivity::class.java))
    }

    override fun navigateToSearchRemote() {
        startActivity(Intent(this, RemoteSearchActivity::class.java))
    }
}