package com.woowrale.jcleanarchitecture.ui.details;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.woowrale.jcleanarchitecture.R;
import com.woowrale.jcleanarchitecture.ui.base.BaseActivity;
import com.woowrale.jcleanarchitecture.ui.model.ContactUI;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DetailsActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.thumbnail)
    ImageView thumbnail;

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.phone)
    TextView phone;

    @BindView(R.id.email)
    TextView email;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initDagger();
        unbinder = ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        ContactUI contactUI = i.getParcelableExtra("contact");
        Log.e("Contact", contactUI.toString());

        Glide.with(this)
                .load(contactUI.getImage())
                .apply(RequestOptions.circleCropTransform())
                .into(thumbnail);

        name.setText(contactUI.getName());
        phone.setText(contactUI.getPhone());
        email.setText(contactUI.getEmail());

    }

    @Override
    protected void initDagger() {
        super.getDaggerMainComponent().inject(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
