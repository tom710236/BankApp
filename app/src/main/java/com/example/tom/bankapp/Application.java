package com.example.tom.bankapp;

/**
 * Created by TOM on 2017/9/18.
 */

public class Application extends android.app.Application {

    //十六進為表顏色 前面加 0x30

    public static String FCMToken = "";
    public static String WebUri = "";
    public static boolean Login = false ;
    public static String[] itemName4 ;
    public static int[] imageRes4 ;

    /**
     * MainActivity
     */

    //toolbar 設定
    public static int MainToolbarTextSize = 18;
    public static int MainToolbarTextColor = 0x99000000;
    public static int MainToolbarBackGroundColor = 0x99FF1493;
    public static String MainToolbarText = "投信APP";

    //登入按鈕
    public static int MainLoginTextSize = 18;
    public static int MainLoginTextColor = 0x99000000 ;
    public static int MainLoginBackgroundColor = 0x99FF1493 ;

    //背景圖

    public static int MainBackgroundPicture = R.drawable.pig64;

    /**
     * LoginActivity
     */

    //取消按鈕
    public static int LoginCancelTextSize = 18;
    public static int LoginCancelTextColor =0x99000000 ;
    public static int LoginCancelBackgroundColor = 0x99FF1493 ;
    //登入按鈕
    public static int LoginLoginTextSize = 18;
    public static int LoginLoginTextColor = 0x99000000 ;
    public static int LoginLoginBackgroundColor = 0x99FF1493 ;
    //toolbar 設定
    public static int LoginToolbarTextSize = 18;
    public static int LoginToolbarTextColor = 0x99000000;
    public static int LoginToolbarBackGroundColor = 0x99FF1493;
    public static String LoginToolbarText = "投信APP";



    /**
     * WebViewActivity投信APP
     */
    //toolbar
    public static int webToolbarTextSize = 18;
    public static int webToolbarTextColor = 0x9900000投信APP0;
    public static int webToolbarBackGroundColor = 0x99FF1493;
    public static String webToolbarText = "";
}
