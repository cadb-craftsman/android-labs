package com.woowrale.architecture.mvp.ui.base;

public class BasePresenter<V extends BasePresenter.View> {

    private View V;

    public View getView(){
        return V;
    }

    public interface View{

    }
}
