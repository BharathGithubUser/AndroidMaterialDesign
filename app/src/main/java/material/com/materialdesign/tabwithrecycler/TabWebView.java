package material.com.materialdesign.tabwithrecycler;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import material.com.materialdesignexample.R;

/**
 * Created by user on 7/2/18.
 */

public class TabWebView extends Fragment {
    View tabWebView;
    WebView webView;
    ProgressDialog pDialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        tabWebView = inflater.inflate(R.layout.fragment_tabwebview,container,false);
        webView = tabWebView.findViewById(R.id.tab_web_view);
        webView.clearView();
        pDialog = new ProgressDialog(getActivity());
        webView.loadUrl("https://www.google.com");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(false);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webView.setWebViewClient(new LoadUrl());
        return webView;
    }

    private class LoadUrl extends WebViewClient {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                pDialog.show();
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                final Handler backHandler = new Handler();
                backHandler.postDelayed(new Runnable() {
                    public void run() {
                        pDialog.dismiss();
                    }
                }, 5000);
            }
        }
}
