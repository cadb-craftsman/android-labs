package com.woowrale.mvp.ui.search.remote

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.jakewharton.rxbinding2.widget.RxTextView
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent
import com.woowrale.mvp.BuildConfig
import com.woowrale.mvp.R
import com.woowrale.mvp.data.model.Contact
import com.woowrale.mvp.ui.base.BaseActivity
import com.woowrale.mvp.ui.adapters.ContactsAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_remote_search.*
import kotlinx.android.synthetic.main.content_remote_search.*
import java.util.ArrayList
import java.util.concurrent.TimeUnit

class RemoteSearchActivity : BaseActivity(), ContactsAdapter.ContactsAdapterListener,
    RemoteSearchView {

    private var mAdapter: ContactsAdapter? = null
    private var presenter: RemoteSearchPresenter? = null
    private val contactsList = ArrayList<Contact>()
    private val disposable = CompositeDisposable()
    override val publishSubject = PublishSubject.create<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remote_search)


        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        presenter = RemoteSearchPresenter(this, retrofit)

        mAdapter = ContactsAdapter(this, contactsList, this)

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

        whiteNotificationBar(recyclerView)

        val observer = presenter!!.searchObserver

        disposable.add(
            publishSubject
                .debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .switchMapSingle<List<Contact>> { s ->
                    presenter!!.apiService.getContacts(BuildConfig.GET_CONTACTS, "", s)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                }
                .subscribeWith<DisposableObserver<List<Contact>>>(observer))


        // skipInitialValue() - skip for the first time when EditText empty
        disposable.add(
            RxTextView.textChangeEvents(inputSearch!!)
                .skipInitialValue()
                .debounce(300, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith<DisposableObserver<TextViewTextChangeEvent>>(presenter!!.searchContactsTextWatcher())
        )

        disposable.add(observer)

        // passing empty string fetches all the contacts
        publishSubject.onNext("")
    }

    override fun onContactSelected(contact: Contact) {

    }

    private fun whiteNotificationBar(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var flags = view.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            view.systemUiVisibility = flags
            getWindow().setStatusBarColor(Color.WHITE)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun setContactsList(contacts: List<Contact>) {
        contactsList.clear()
        contactsList.addAll(contacts)
    }

    override fun getmAdapter(): ContactsAdapter {
        return mAdapter!!
    }
}