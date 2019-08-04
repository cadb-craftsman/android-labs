package com.woowrale.architecture.mvppatterns.ui.search.remote;

import com.woowrale.architecture.mvppatterns.data.model.Contact;
import com.woowrale.architecture.mvppatterns.ui.adapters.ContactsAdapter;
import com.woowrale.architecture.mvppatterns.ui.base.IView;

import java.util.List;

import io.reactivex.subjects.PublishSubject;

public interface RemoteSearchView extends IView {

    ContactsAdapter getmAdapter();

    List<Contact> getContactsList();

    PublishSubject<String> getPublishSubject();

}
