package com.woowrale.kroomapp.ui.search.local

import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.widget.RxTextView
import com.woowrale.kroomapp.R
import com.woowrale.kroomapp.domain.model.Contact
import com.woowrale.kroomapp.ui.adapters.ContactsAdapterFilterable
import com.woowrale.kroomapp.ui.base.BaseActivity
import com.woowrale.kroomapp.utils.DataWrapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_local_search.*
import kotlinx.android.synthetic.main.content_local_search.*
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class LocalSearchActivity: BaseActivity(), ContactsAdapterFilterable.ContactsAdapterListener {

    @Inject
    lateinit var model: LocalSearchViewModel

    private val disposable = CompositeDisposable()
    private var contacts = ArrayList<Contact>()

    private lateinit var mAdapter: ContactsAdapterFilterable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDagger()

        setContentView(R.layout.activity_local_search)
        setSupportActionBar(toolbar)

        contacts = DataWrapper.getContactsFromJson(this)
        mAdapter = ContactsAdapterFilterable(this, contacts, this)
        model.getLocalContacts(disposable, contacts, mAdapter)

        val mLayoutManager = LinearLayoutManager(this)
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
    }

    override fun initDagger() {
        getDaggerMainComponent().inject(this)
    }

    override fun onContactSelected(contact: Contact) {

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}