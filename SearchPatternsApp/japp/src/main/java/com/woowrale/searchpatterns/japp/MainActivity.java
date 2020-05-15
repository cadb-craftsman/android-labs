package com.woowrale.searchpatterns.japp;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText search = findViewById(R.id.searchNifText);

        findViewById(R.id.searchButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = search.getText().toString();
                if(validateNIF(searchText)){
                    Log.e("SEARCH", "Is a NIF");
                }

                if(validateNIE(searchText)){
                    Log.e("SEARCH", "Is a NIE");
                }

                if(validateFullName(searchText)){
                    Log.e("SEARCH", "Is a NAME");
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean validateNIF(String data){
        boolean isOk = false;
        Pattern p = Pattern.compile("[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKET]");
        if(p.matcher(data).find()){
            isOk = true;
        }
        return isOk;
    }

    private boolean validateNIE(String data){
        boolean isOk = false;
        Pattern p = Pattern.compile("[XYZ][0-9]{7}[TRWAGMYFPDXBNJZSQVHLCKET]");
        if(p.matcher(data).find()){
            isOk = true;
        }
        return isOk;
    }

    private boolean validateFullName(String data){
        boolean isOk = false;
        Pattern p = Pattern.compile("^[a-zA-Z\\s]*$");
        if(p.matcher(data).find()){
            isOk = true;
        }
        return isOk;
    }
}
