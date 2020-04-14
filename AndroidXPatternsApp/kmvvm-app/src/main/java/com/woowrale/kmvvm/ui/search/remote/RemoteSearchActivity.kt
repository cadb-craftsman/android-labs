package com.woowrale.kmvvm.ui.search.remote

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.widget.RxTextView
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent
import com.woowrale.kmvvm.BuildConfig
import com.woowrale.kmvvm.R
import com.woowrale.kmvvm.data.model.Contact
import com.woowrale.kmvvm.data.repository.ApiService
import com.woowrale.kmvvm.ui.adapters.ContactsAdapter
import com.woowrale.kmvvm.ui.base.BaseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_remote_search.*
import kotlinx.android.synthetic.main.content_remote_search.*
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RemoteSearchActivity : BaseActivity(), ContactsAdapter.ContactsAdapterListener {

    @Inject
    lateinit var model: RemoteSearchViewModel

    private val contactsList = ArrayList<Contact>()
    private val disposable = CompositeDisposable()
    private val publishSubject = PublishSubject.create<String>()

    private lateinit var mAdapter: ContactsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDagger()

        setContentView(R.layout.activity_remote_search)

        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        mAdapter = ContactsAdapter(this, contactsList, this)

        val mLayoutManager = LinearLayoutManager(getApplicationContext())
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = mAdapter

        whiteNotificationBar(recyclerView)

        var observer= model.searchObserver(contactsList, mAdapter).value

        disposable.add(
            publishSubject
                .debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .switchMapSingle<List<Contact>> { s ->
                    ApiService.services().getContacts(BuildConfig.GET_CONTACTS, "", s)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                }
                .subscribeWith<DisposableObserver<List<Contact>>>(observer))

        disposable.add(
            RxTextView.textChangeEvents(inputSearch!!)
                .skipInitialValue()
                .debounce(300, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith<DisposableObserver<TextViewTextChangeEvent>>(model.searchContactsTextWatcher(publishSubject))
        )

        disposable.add(observer!!)
        publishSubject.onNext("")
    }

    override fun initDagger() {
        getDaggerMainComponent().inject(this)
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
}