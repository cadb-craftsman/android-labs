package com.woowrale.jmvp.ui.search.remote;

import com.woowrale.jmvp.data.model.Contact;
import com.woowrale.jmvp.ui.adapters.ContactsAdapter;
import com.woowrale.jmvp.ui.base.BasePresenter;
import io.reactivex.subjects.PublishSubject;

import java.util.List;

public interface RemoteSearchView extends BasePresenter.View {

    ContactsAdapter getmAdapter();

    List<Contact> getContactsList();

    PublishSubject<String> getPublishSubject();

}
