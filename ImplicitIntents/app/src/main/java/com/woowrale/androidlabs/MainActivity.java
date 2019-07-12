package com.woowrale.androidlabs;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    private EditText mWebSiteEditText;
    private EditText mLocationEditText;
    private EditText mShareEditText;

    private Button mWebSiteButton;
    private Button mLocationButton;
    private Button mShareButton;
    private Button mTakePictureButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebSiteEditText = findViewById(R.id.edittext_url);
        mLocationEditText = findViewById(R.id.edittext_loc);
        mShareEditText = findViewById(R.id.edittext_share);

        mWebSiteButton = findViewById(R.id.button_uri);
        mLocationButton = findViewById(R.id.button_loc);
        mShareButton = findViewById(R.id.button_share);

        mWebSiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebSite(v);
            }
        });

        mLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLocation(v);
            }
        });

        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareText(v);
            }
        });

        /*
        mTakePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }

            }
        });
        */
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            //imageView.setImageBitmap(imageBitmap);
        }
    }

    public void openWebSite(View view){

        String url = mWebSiteEditText.getText().toString();
        Uri website = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, website);

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }else{
            Log.d("ImplicitIntents", "Can't handle this");
        }
    }

    public void openLocation(View view){

        String loc = mLocationEditText.getText().toString();
        Uri addressLoc = Uri.parse("geo:0,0?q=" + loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressLoc);

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }else{
            Log.d("ImplicitIntents", "Can't handle this");
        }
    }

    public void shareText(View view){
        String text = mShareEditText.getText().toString();
        String mymeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mymeType)
                .setChooserTitle(R.string.share_text)
                .setText(text)
                .startChooser();

    }

}
