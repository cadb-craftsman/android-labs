package com.woowrale.previewpdf;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    WebView previewWebview;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        previewWebview = (WebView) findViewById(R.id.previeWebview);
        previewWebview.getSettings().setJavaScriptEnabled(true);

        String url = "https://github.github.com/training-kit/downloads/github-git-cheat-sheet.pdf";
        previewWebview.loadUrl("https://docs.google.com/gview?embedded=true&url="+url);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        previewWebview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }
        });

    }
}
