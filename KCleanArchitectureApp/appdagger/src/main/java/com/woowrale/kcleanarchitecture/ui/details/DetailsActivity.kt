package com.woowrale.kcleanarchitecture.ui.details

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.woowrale.kcleanarchitecture.R
import com.woowrale.kcleanarchitecture.ui.base.BaseActivity
import com.woowrale.kcleanarchitecture.ui.model.ContactUI
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.content_detail.*
import javax.inject.Inject

class DetailsActivity : BaseActivity() {

    @Inject
    lateinit var model: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDagger()
        setContentView(R.layout.activity_details)
        setSupportActionBar(toolbar)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
        val i: Intent = getIntent()
        val contactUI = i.getParcelableExtra("contact") as ContactUI

        Glide.with(this)
            .load(contactUI.image)
            .apply(RequestOptions.circleCropTransform())
            .into(thumbnail)
        name.text = contactUI.name
        phone.text = contactUI.phone
        email.text = contactUI.email
    }

    override fun initDagger() {
        super.getDaggerMainComponent().inject(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}