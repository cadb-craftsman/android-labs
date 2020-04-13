package com.woowrale.jmvvm.ui.search.local;

import com.woowrale.jmvvm.data.model.Contact;
import com.woowrale.jmvvm.ui.adapters.ContactsAdapterFilterable;
import com.woowrale.jmvvm.ui.base.BasePresenter;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public interface LocalSearchView extends BasePresenter.View {

    CompositeDisposable getDisposable();

    ContactsAdapterFilterable getmAdapter();

    List<Contact> getContactsList();

}
