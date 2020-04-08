package com.woowrale.instantsearch.japp.ui.search.local;

import com.woowrale.instantsearch.japp.data.model.Contact;
import com.woowrale.instantsearch.japp.ui.adapters.ContactsAdapterFilterable;
import com.woowrale.instantsearch.japp.ui.base.BasePresenter;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public interface LocalSearchView extends BasePresenter.View {

    CompositeDisposable getDisposable();

    ContactsAdapterFilterable getmAdapter();

    List<Contact> getContactsList();

}
