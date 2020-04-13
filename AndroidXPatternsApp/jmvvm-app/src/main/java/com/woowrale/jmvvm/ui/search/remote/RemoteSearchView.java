package com.woowrale.jmvvm.ui.search.remote;

import com.woowrale.jmvvm.data.model.Contact;
import com.woowrale.jmvvm.ui.adapters.ContactsAdapter;
import com.woowrale.jmvvm.ui.base.BasePresenter;

import java.util.List;

import io.reactivex.subjects.PublishSubject;

public interface RemoteSearchView extends BasePresenter.View {

    ContactsAdapter getmAdapter();

    List<Contact> getContactsList();

    PublishSubject<String> getPublishSubject();

}
