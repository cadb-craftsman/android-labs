package com.woowrale.instantsearch.japp.ui.search.remote;

import com.woowrale.instantsearch.japp.data.model.Contact;
import com.woowrale.instantsearch.japp.ui.adapters.ContactsAdapter;
import com.woowrale.instantsearch.japp.ui.base.BasePresenter;

import java.util.List;

import io.reactivex.subjects.PublishSubject;

public interface RemoteSearchView extends BasePresenter.View {

    ContactsAdapter getmAdapter();

    List<Contact> getContactsList();

    PublishSubject<String> getPublishSubject();

}
