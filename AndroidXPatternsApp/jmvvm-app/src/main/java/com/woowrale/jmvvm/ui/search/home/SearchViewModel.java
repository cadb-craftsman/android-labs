package com.woowrale.jmvvm.ui.search.home;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class SearchViewModel extends ViewModel {

    private MutableLiveData<Intent> navigation;

    @Inject
    public SearchViewModel(){
        navigation = new MutableLiveData<Intent>();
    }

    public LiveData<Intent> navigationTo(Context context, Class navigationClass){
        Intent intent = intent = new Intent(context, navigationClass);
        navigation.setValue(intent);
        return navigation;
    }
}
