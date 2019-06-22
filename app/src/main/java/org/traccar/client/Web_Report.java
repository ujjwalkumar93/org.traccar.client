package org.traccar.client;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

public class Web_Report extends AppCompatActivity {
    WebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web__report);
        wv=findViewById(R.id.myweb);




        WebSettings ws=wv.getSettings();
       // ws.setJavaScriptEnabled(true);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
       // wv.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //wv.getSettings().setAppCacheEnabled(true);
        wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wv.getSettings().setLoadsImagesAutomatically(true);
        ws.setDomStorageEnabled(true);
        ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        ws.setUseWideViewPort(true);
        ws.setSavePassword(true);
        ws.setSaveFormData(true);
        ws.setEnableSmoothTransition(true);
        wv.loadUrl("http://om.vedarths.com/desk#Form/Maintenance%20Visit/MV00001");
        wv.setWebViewClient(new WebViewClient());

    }
    @Override
    public void onBackPressed() {
        if (wv.canGoBack()) {
            wv.goBack();
        } else {
            super.onBackPressed();
        }
    }


}
