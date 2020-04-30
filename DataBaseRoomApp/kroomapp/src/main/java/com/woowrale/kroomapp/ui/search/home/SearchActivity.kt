package com.woowrale.kroomapp.ui.search.home

import android.os.Bundle
import com.woowrale.kroomapp.R
import com.woowrale.kroomapp.ui.base.BaseActivity
import com.woowrale.kroomapp.ui.search.local.LocalSearchActivity
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

        btnLocalSearch.setOnClickListener {
            startActivity(model.navigationTo(this, LocalSearchActivity::class.java).value)
        }

    }

    override fun initDagger() {
        getDaggerMainComponent().inject(this)
    }

}