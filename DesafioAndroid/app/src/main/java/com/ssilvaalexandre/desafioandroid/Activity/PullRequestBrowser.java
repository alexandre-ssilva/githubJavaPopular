package com.ssilvaalexandre.desafioandroid.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.ssilvaalexandre.desafioandroid.R;

public class PullRequestBrowser extends AppCompatActivity {

    private String url, prTitle;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_request_browser);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            url = extras.getString("url");
            prTitle = extras.getString("pull");
        }

        setupToolbar();
        onActivityCreated();
    }

    private void setupToolbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(prTitle);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();

        return true;
    }

    private void onActivityCreated() {
        WebView browser = findViewById(R.id.pr_browser);
        progressBar = findViewById(R.id.webview_progressbar);
        progressBar.setMax(100);

        browser.setWebChromeClient(new CustomWebViewClient());

        browser.loadUrl(url);
    }

    private class CustomWebViewClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {

            progressBar.setProgress(newProgress);

            if (newProgress < 100)
                progressBar.setVisibility(View.VISIBLE);
            else
                progressBar.setVisibility(View.GONE);

            super.onProgressChanged(view, newProgress);
        }
    }
}
