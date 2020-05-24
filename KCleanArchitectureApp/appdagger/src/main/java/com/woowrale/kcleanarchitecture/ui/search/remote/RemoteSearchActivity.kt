package com.woowrale.kcleanarchitecture.ui.search.remote

import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.widget.RxTextView
import com.woowrale.domain.model.Contact
import com.woowrale.kcleanarchitecture.R
import com.woowrale.kcleanarchitecture.ui.adapters.ContactsAdapterFilterable
import com.woowrale.kcleanarchitecture.ui.base.BaseActivity
import com.woowrale.kcleanarchitecture.ui.details.DetailsActivity
import com.woowrale.kcleanarchitecture.ui.model.ContactUI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_remote_search.*
import kotlinx.android.synthetic.main.content_remote_search.*
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RemoteSearchActivity : BaseActivity(), ContactsAdapterFilterable.ContactsAdapterListener {

    @Inject
    lateinit var model: RemoteSearchViewModel

    private val disposable = CompositeDisposable()
    private val contacts = ArrayList<Contact>()

    private lateinit var mAdapter: ContactsAdapterFilterable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDagger()

        setContentView(R.layout.activity_local_search)

        setSupportActionBar(toolbar)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)

        mAdapter = ContactsAdapterFilterable(this, contacts, this)

        val mLayoutManager = LinearLayoutManager(getApplicationContext())
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = mAdapter

        disposable.add(
            RxTextView.textChangeEvents(inputSearch)
                .skipInitialValue()
                .debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(model.searchContacts(mAdapter).value!!)
        )

        model.getContacts(disposable, contacts, mAdapter)

    }

    override fun initDagger() {
        getDaggerMainComponent().inject(this)
    }

    override fun onContactSelected(contact: Contact) {
        startActivity(model.navigationTo(this, DetailsActivity::class.java, ContactUI(contact.name, contact.image, contact.phone, contact.email)).value)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}