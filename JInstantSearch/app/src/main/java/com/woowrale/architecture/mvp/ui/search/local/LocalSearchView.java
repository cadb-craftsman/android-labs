package com.woowrale.architecture.mvp.ui.search.local;

import com.woowrale.architecture.mvp.data.model.Contact;
import com.woowrale.architecture.mvp.ui.adapters.ContactsAdapterFilterable;
import com.woowrale.architecture.mvp.ui.base.BasePresenter;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public interface LocalSearchView extends BasePresenter.View {

    CompositeDisposable getDisposable();

    ContactsAdapterFilterable getmAdapter();

    List<Contact> getContactsList();

}
