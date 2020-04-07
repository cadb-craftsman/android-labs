package com.woowrale.instantsearch.japp.ui.search.remote;

import com.woowrale.mvp.data.model.Contact;
import com.woowrale.mvp.ui.adapters.ContactsAdapter;
import com.woowrale.mvp.ui.base.BasePresenter;

import java.util.List;

import io.reactivex.subjects.PublishSubject;

public interface RemoteSearchView extends BasePresenter.View {

    ContactsAdapter getmAdapter();

    List<Contact> getContactsList();

    PublishSubject<String> getPublishSubject();

}
