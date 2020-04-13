package com.woowrale.jmvp.ui.search.local;

import com.woowrale.jmvp.data.model.Contact;
import com.woowrale.jmvp.ui.adapters.ContactsAdapterFilterable;

import java.util.List;

import com.woowrale.jmvp.ui.base.BasePresenter;
import io.reactivex.disposables.CompositeDisposable;

public interface LocalSearchView extends BasePresenter.View {

    CompositeDisposable getDisposable();

    ContactsAdapterFilterable getmAdapter();

    List<Contact> getContactsList();

}
