package com.example.tom.bankapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

public class WebViewActivity extends AppCompatActivity {
    WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        toolBar();

        final ProgressBar pb = (ProgressBar)findViewById(R.id.progressBar3);
        webview = (WebView) findViewById(R.id.webview);

        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);//啟用JaveScript
        webSettings.setBuiltInZoomControls(true);//啟用縮放功能
        webview.setWebViewClient(new WebViewClient());//啟用超連結動作
        //設定進度條
        webview.setWebChromeClient(new WebChromeClient(){
            public void onProgressChanged(WebView view , int progress){
                pb.setProgress(progress);
                pb.setVisibility(progress<100? View.VISIBLE:
                View.GONE);
            }
        });
        //載入的網址
        webview.loadUrl(Application.WebUri);

    }

    private void toolBar() {
        //Toolbar 設定
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //回到上一頁的圖示
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp);
        //回到上一頁按鍵設定
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WebViewActivity.this, Navigation.class);
                startActivity(intent);
                if(Application.Login == false && Application.Login2 ==false){
                    Application.Web = false;
                }else {
                    Application.Web = true;
                }

                WebViewActivity.this.finish();
            }
        });
        TextView textView = (TextView)findViewById(R.id.textTitle);
        textView.setText(Application.webToolbarText);
        toolbar.setBackgroundColor(Application.webToolbarBackGroundColor);
        textView.setTextColor(Application.webToolbarTextColor);
        textView.setTextSize(Application.webToolbarTextSize);
    }

    //設定返回鍵
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if (keyCode == KeyEvent.KEYCODE_BACK) { // 攔截返回鍵
            if(webview.canGoBack()){
                webview.goBack();
            }else {
                Intent intent = new Intent(WebViewActivity.this, Navigation.class);
                startActivity(intent);
                Application.Web = true;
                WebViewActivity.this.finish();

            }

        }
        //return super.onKeyDown(keyCode, event);
        return false;
    }



}
