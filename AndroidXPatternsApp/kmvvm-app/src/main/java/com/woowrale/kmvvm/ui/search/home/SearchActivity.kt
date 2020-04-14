package com.woowrale.kmvvm.ui.search.home

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import com.woowrale.kmvvm.R
import com.woowrale.kmvvm.ui.base.BaseActivity
import com.woowrale.kmvvm.ui.search.local.LocalSearchActivity
import com.woowrale.kmvvm.ui.search.remote.RemoteSearchActivity
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.content_search.*
import javax.inject.Inject

class SearchActivity : BaseActivity() {

    @Inject
    lateinit var model: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDagger()

        setContentView(R.layout.activity_search)

        setSupportActionBar(toolbar)
        whiteNotificationBar(toolbar)

        btnLocalSearch.setOnClickListener { view ->
            startActivity(model.navigationTo(this, LocalSearchActivity::class.java).value)
        }

        btnRemoteSearch.setOnClickListener { view ->
            startActivity(model.navigationTo(this, RemoteSearchActivity::class.java).value)
        }
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
}