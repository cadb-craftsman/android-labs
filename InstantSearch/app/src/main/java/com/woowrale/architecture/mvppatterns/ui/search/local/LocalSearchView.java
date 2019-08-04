package com.woowrale.architecture.mvppatterns.ui.search.local;

import com.woowrale.architecture.mvppatterns.data.model.Contact;
import com.woowrale.architecture.mvppatterns.ui.adapters.ContactsAdapterFilterable;
import com.woowrale.architecture.mvppatterns.ui.base.IView;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public interface LocalSearchView extends IView {

    CompositeDisposable getDisposable();

    ContactsAdapterFilterable getmAdapter();

    List<Contact> getContactsList();

}
