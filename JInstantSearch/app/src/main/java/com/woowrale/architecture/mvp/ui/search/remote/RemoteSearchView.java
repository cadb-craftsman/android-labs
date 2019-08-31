package com.woowrale.architecture.mvp.ui.search.remote;

import com.woowrale.architecture.mvp.data.model.Contact;
import com.woowrale.architecture.mvp.ui.adapters.ContactsAdapter;
import com.woowrale.architecture.mvp.ui.base.IView;

import java.util.List;

import io.reactivex.subjects.PublishSubject;

public interface RemoteSearchView extends IView {

    ContactsAdapter getmAdapter();

    List<Contact> getContactsList();

    PublishSubject<String> getPublishSubject();

}
