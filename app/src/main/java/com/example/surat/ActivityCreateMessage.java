package com.example.surat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.ProgressDialog;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ActivityCreateMessage extends AppCompatActivity {
    private static final String URL_CREATE = "http://jtisurat.wsjti.com/mhs/formA";
    WebView createmessage;
    WebSettings webSettings;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);

        getSupportActionBar().setTitle("Create Message");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        createmessage = findViewById(R.id.browser_createMessage);
        webSettings = createmessage.getSettings();
        webSettings.setJavaScriptEnabled(true);

        createmessage.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        });
        createmessage.loadUrl(URL_CREATE);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && createmessage.canGoBack()) {
            createmessage.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
