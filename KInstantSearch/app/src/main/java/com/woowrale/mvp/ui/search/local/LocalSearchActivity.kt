package com.woowrale.mvp.ui.search.local

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.*
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import butterknife.ButterKnife
import com.jakewharton.rxbinding2.widget.RxTextView
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent
import com.woowrale.mvp.R
import com.woowrale.mvp.data.model.Contact
import com.woowrale.mvp.ui.base.BaseActivity
import com.woowrale.mvp.ui.adapters.ContactsAdapterFilterable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_local_search.*
import kotlinx.android.synthetic.main.content_local_search.*
import java.util.ArrayList
import java.util.concurrent.TimeUnit

class LocalSearchActivity : BaseActivity(), ContactsAdapterFilterable.ContactsAdapterListener,
    LocalSearchView {

    private var presenter: LocalSearchPresenter? = null
    private var mAdapter: ContactsAdapterFilterable? = null
    private val contactsList = ArrayList<Contact>()
    override val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local_search)

        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        presenter = LocalSearchPresenter(this, retrofit)

        mAdapter = ContactsAdapterFilterable(this, contactsList, this)

        val mLayoutManager = LinearLayoutManager(getApplicationContext())
        recyclerView!!.layoutManager = mLayoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView!!.adapter = mAdapter

        whiteNotificationBar(recyclerView!!)

        disposable.add(
            RxTextView.textChangeEvents(inputSearch!!)
                .skipInitialValue()
                .debounce(300, TimeUnit.MILLISECONDS)
                /*.filter(new Predicate<TextViewTextChangeEvent>() {
                    @Override
                    public boolean test(TextViewTextChangeEvent textViewTextChangeEvent) throws Exception {
                        return TextUtils.isEmpty(textViewTextChangeEvent.text().toString()) || textViewTextChangeEvent.text().toString().length() > 2;
                    }
                })*/
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith<DisposableObserver<TextViewTextChangeEvent>>(presenter!!.searchContacts())
        )

        // source: `gmail` or `linkedin`
        // fetching all contacts on app launch
        // only gmail will be fetched
        presenter!!.fetchContacts("gmail")
    }

    private fun whiteNotificationBar(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var flags = view.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            view.systemUiVisibility = flags
            getWindow().setStatusBarColor(Color.WHITE)
        }
    }

    override fun onContactSelected(contact: Contact) {

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun getmAdapter(): ContactsAdapterFilterable {
        return mAdapter!!
    }
    override fun setContactsList(contactList: List<Contact>) {
        contactsList.clear()
        contactsList.addAll(contactList)
    }

    override fun getContactsList(): List<Contact> {
        return contactsList
    }
}