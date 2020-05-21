package com.woowrale.jcleanarchitecture.ui.details;

import android.os.Bundle;

import com.woowrale.jcleanarchitecture.R;
import com.woowrale.jcleanarchitecture.ui.base.BaseActivity;

public class DetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }

    @Override
    protected void initDagger() {
        super.getDaggerMainComponent().inject(this);
    }
}
