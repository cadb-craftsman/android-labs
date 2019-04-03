package com.woowrale.androidlabs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int mCount = 0;
    private TextView mShowCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = (TextView) findViewById(R.id.show_count);
    }

    public void showToast(View view){
        if(mShowCount != null){
            String message = getString(R.string.toast_message) + mCount;
            Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void countUp(View view){
        mCount++;
        if(mShowCount != null) {
            mShowCount.setText(String.valueOf(mCount));
        }
    }
}
