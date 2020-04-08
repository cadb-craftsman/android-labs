package com.woowrale.mvp.ui.search.remote;

import com.woowrale.mvp.data.model.Contact;
import com.woowrale.mvp.ui.adapters.ContactsAdapter;
import com.woowrale.mvp.ui.base.BasePresenter;
import io.reactivex.subjects.PublishSubject;

import java.util.List;

public interface RemoteSearchView extends BasePresenter.View {

    ContactsAdapter getmAdapter();

    List<Contact> getContactsList();

    PublishSubject<String> getPublishSubject();

}
