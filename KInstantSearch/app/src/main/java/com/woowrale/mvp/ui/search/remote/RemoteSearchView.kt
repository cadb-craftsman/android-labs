package com.woowrale.mvp.ui.search.remote

import com.woowrale.mvp.data.model.Contact
import com.woowrale.mvp.ui.base.BasePresenter
import com.woowrale.mvp.ui.adapters.ContactsAdapter
import io.reactivex.subjects.PublishSubject

interface RemoteSearchView : BasePresenter.View {

    val publishSubject: PublishSubject<String>

    fun  setContactsList(contacts: List<Contact>)

    fun getmAdapter(): ContactsAdapter

}
