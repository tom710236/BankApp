package com.example.tom.bankapp;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.ckfree.common.SimpleSearchBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//import android.support.v7.widget.SearchView;



public class MainActivity extends AppCompatActivity {
    String token = "null";
    SQLiteDatabase db;
    GridView mGridView;
    ArrayList<String> itemName;
    ArrayList<Integer> imageRes;
    SimpleAdapter adapter;
    ArrayList<LinkedHashMap<String, String>> myList;
    LinkedHashMap<String, String> map;

    int image = R.drawable.pig64
            ,image2 = R.drawable.pig64
            ,image3 = R.drawable.pig64
            ,image4 = R.drawable.pig64
            ,image5 = R.drawable.pig64
            ,image6 = R.drawable.pig64
            ,image7 = R.drawable.pig64
            ,image8 = R.drawable.pig64
            ,image9 = R.drawable.pig64;
    String name = "审前调查"
            ,name2 = "需求评估"
            ,name3 = "在册人员"
            ,name4 = "请销假"
            ,name5 = "集中教育"
            ,name6 = "个别教育"
            ,name7 = "心理测评"
            ,name8 = "生活量表"
            ,name9 = "矫正方案";
    Uri uri1 = Uri.parse("https://www.google.com.tw/")
            ,uri2 = Uri.parse("http://barcode.tec-it.com/zh/Code128?data=94344068123")
            ,uri3 = Uri.parse("http://news.ltn.com.tw/news/life/breakingnews/2206806")
            ,uri4 = Uri.parse("http://givemepass-blog.logdown.com/posts/288946-book-list")
            ,uri5 = Uri.parse("https://www.slideshare.net/rickwu12/ss-54297655")
            ,uri6 = Uri.parse("http://demo.shinda.com.tw/PegionModernWebApi/login.aspx")
            ,uri7 = Uri.parse("https://luolala.gitbooks.io/mystudynote/content/MobileWebDev/note.html")
            ,uri8 = Uri.parse("http://news.ltn.com.tw/news/life/breakingnews/2206806")
            ,uri9 = Uri.parse("http://news.ltn.com.tw/news/life/breakingnews/2206806");

    //九宮格全部功能
    private String[] itemName2 = {name,name2,name3,name4,name5,name6,name7,name8,name9};
    // 九宮格全部功能的圖片
    private int[] imageRes2 = {image,image2,image3,image4,image5,image6,image7,image8,image9};
    //九宮格預設功能
    private String[] itemName3 = {name,name2,name3,name4,name5};
    //九宮格預設功能的圖片
    private int[] imageRes3 = {image,image2,image3,image4,image5};
    //九宮格登入後功能
    private String[] itemName4;
    //九宮格登入後的圖片
    private int[] imageRes4;
    //前往網址
    private Uri[] uris = {uri1,uri2,uri3,uri4,uri5,uri6,uri7,uri8,uri9,} ;
    //紀錄勾勾
    private HashSet<Integer> mCheckSet = new HashSet<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_main);



        toolBar();

        //預設九宮格的設定
        setmArrayList();
        //search 的設定
        setmSearch();
        //啟動service 讓7.0的手機可以常駐後台
        Intent it = new Intent(MainActivity.this, Delay.class);
        startService(it);
        //取出token
        getToken();
        Log.e("TOKEN", Application.FCMToken);
        //跳出dialog
        //S為推播的內容 (FirebaseMessageingService 那邊調整onMessageReceived)
        MyNotice.getInstance().setOnMessageReceivedListener(new MyNotice.OnMessageReceivedListener() {
            @Override
            public void onMessageReceived(final String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mDialog(s);
                    }
                });
            }
        });
        //九宮格的設定
        setmGridView();
        //新增我的最愛
        setmListView();

        if (Application.Login == true) {
            ImageButton button = (ImageButton)findViewById(R.id.imageButton);
            button.setVisibility(View.VISIBLE);
            Button button1 = (Button)findViewById(R.id.button4);
            button1.setText("登出");
        }
        setSeachView();

    }
    //定义九宮格点击事件监听器
    public class GridViewItemOnClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getApplicationContext(), itemName.get(position), Toast.LENGTH_SHORT).show();

            switch (itemName.get(position)){
                case "审前调查" :
                    setmUri(0);
                    break;
                case "需求评估" :
                    setmUri(1);
                    break;
                case "在册人员" :
                    setmUri(2);
                    break;
                case "请销假" :
                    setmUri(3);
                    break;
                case "集中教育" :
                    setmUri(4);
                    break;
                case "个别教育" :
                    setmUri(5);
                    break;
                case "心理测评" :
                    setmUri(6);
                    break;
                case "生活量表" :
                    setmUri(7);
                    break;
                case "矫正方案" :
                    setmUri(8);
                    break;

            }
        }
    }
    private void setmUri (int i){
        Intent it = new Intent(this, WebViewActivity.class);
        Application.WebUri = String.valueOf(uris[i]);
        startActivity(it);
        MainActivity.this.finish();

    }
    private void mDialog(String message) {
        AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
        alertDlg.setTitle("您有一則新訊息");
        alertDlg.setCancelable(false);
        alertDlg.setMessage(message);
        alertDlg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDlg.show();

    }

    //假設收到雲端通知時APP是在裝置的背景，此時不會在onMessageReceived中收到通知，Android系統會自動將收到的通知顯示在裝置上方，下拉可顯示更多資訊
    //目前好像沒用
    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();
        /*
        String msg = bundle.getString("msg");
        if (msg!=null){
            Log.e("FCM", "msg:"+msg);
        }
        */

    }

    private String getToken() {
        MyDBhelper MyDB = new MyDBhelper(this, "tblTable", null, 1);
        db = MyDB.getWritableDatabase();
        //Cursor c=db2.rawQuery("SELECT * FROM "+"tblTable2", null);   //查詢全部欄位
        Cursor c = db.query("tblTable",                          // 資料表名字
                null,                                              // 要取出的欄位資料
                null,                                              // 查詢條件式(WHERE)
                null,                                              // 查詢條件值字串陣列(若查詢條件式有問號 對應其問號的值)
                null,                                              // Group By字串語法
                null,                                              // Having字串法
                null);                                             // Order By字串語法(排序)
        //往下一個 收尋
        while (c.moveToNext()) {
            token = c.getString(c.getColumnIndex("cToken"));
        }
        Application.FCMToken = token;
        return token;
    }

    private void unLock() {
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        //获取电源管理器对象
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK |
                PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, "bright");
        //获取PowerManager.WakeLock对象,后面的参数|表示同时传入两个值,最后的是LogCat里用的Tag
        wl.acquire();
        //点亮屏幕
        wl.release();
        //释放
        // 處理 bData 內含的訊息
        // 在本例中, 我的 server 端程式 gcm_send.php 傳來了 message, campaigndate, title, description 四項資料
        KeyguardManager km = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
        //得到鍵盤鎖管理器對象
        KeyguardManager.KeyguardLock kl = km.newKeyguardLock("unLock");
        //參數是LogCat裡用的Tag
        kl.disableKeyguard();
        //解鎖

        //收到推播會震動
        Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vb.vibrate(1500);

    }

    public void onAdd(View v) {
        LinearLayout linear = (LinearLayout) findViewById(R.id.linear);
        LinearLayout linear2 = (LinearLayout) findViewById(R.id.linear2);
        ScrollView linear3 = (ScrollView) findViewById(R.id.linear3);
        ScrollView linear4 = (ScrollView) findViewById(R.id.linear4);
        linear.setVisibility(View.GONE);
        linear2.setVisibility(View.GONE);
        linear3.setVisibility(View.GONE);
        linear4.setVisibility(View.VISIBLE);
    }

    public void onBack(View v) {
        LinearLayout linear = (LinearLayout) findViewById(R.id.linear);
        LinearLayout linear2 = (LinearLayout) findViewById(R.id.linear2);
        ScrollView linear3 = (ScrollView) findViewById(R.id.linear3);
        ScrollView linear4 = (ScrollView) findViewById(R.id.linear4);
        linear.setVisibility(View.VISIBLE);
        linear2.setVisibility(View.VISIBLE);
        linear3.setVisibility(View.VISIBLE);
        linear4.setVisibility(View.GONE);
        setmGridView();
        adapter.notifyDataSetChanged();

    }

    public void onLogin(View v){
        if(Application.Login == false){
            Intent it = new Intent(this , LoginActivity.class);
            startActivity(it);
            MainActivity.this.finish();

        }else {
            Application.Login = false;
            Intent it = new Intent(this , MainActivity.class);
            startActivity(it);
            MainActivity.this.finish();
            overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
        }

    }

    public class SpecialAdapter extends SimpleAdapter {

        public SpecialAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
        }

        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {
            //获取相应的view中的checkbox对象
            convertView = null;
            if (convertView == null) {
                convertView = View.inflate(MainActivity.this, R.layout.item2, null);
                final CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
                //留住勾勾
                checkBox.setChecked(mCheckSet.contains(position));
                checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (((CheckBox) v).isChecked()) {
                            itemName.add(itemName2[position]);
                            imageRes.add(imageRes2[position]);
                            mCheckSet.add(position);
                        } else {

                            for(int i = 0 ; i<itemName.size() ; i++){
                                if(itemName.get(i).equals(itemName2[position])){
                                    Log.e("打勾", itemName.get(i));
                                    itemName.remove(i);
                                    i--;
                                    mCheckSet.remove(position);
                                }
                            }
                            for (int i= 0 ; i<imageRes.size() ; i++){
                                if(imageRes.get(i).equals(itemName2[position])){
                                    imageRes.remove(i);
                                    i--;
                                }
                            }
                        }
                    }
                });
            }
            return super.getView(position, convertView, parent);
        }

    }

    private void setmGridView() {
        //九宮格的設定
        mGridView = (GridView) findViewById(R.id.MyGridView);
        List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        int length = itemName.size();
        for (int i = 0; i < length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImageView", imageRes.get(i));
            map.put("ItemTextView", itemName.get(i));
            data.add(map);
        }
        //为itme.xml添加适配器
        SimpleAdapter simpleAdapter = new SimpleAdapter(MainActivity.this,
                data, R.layout.item, new String[]{"ItemImageView", "ItemTextView"}, new int[]{R.id.ItemImageView, R.id.ItemTextView});
        mGridView.setAdapter(simpleAdapter);
        //为mGridView添加点击事件监听器
        mGridView.setOnItemClickListener(new GridViewItemOnClick());
    }
    private void setmSearch(){
        final SimpleSearchBar mysearchbar = (SimpleSearchBar) findViewById(R.id.mysearchbar);

        /*
        View displayview = findViewById(R.id.imageView7);//跟随searchbar显示隐藏的view
        mysearchbar.init(displayview, new SimpleSearchBar.SearchBarWathcer() {
            @Override
            protected void _onTextChanged(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
        */
    }


    private void setmListView(){
        myList = new ArrayList();
        for (int i = 0; i < itemName2.length; i++) {
            map = new LinkedHashMap<String, String>();
            map.put("1", itemName2[i]);
            myList.add(map);
        }
        ListView listView = (ListView) findViewById(R.id.listView);
        adapter = new SpecialAdapter(
                MainActivity.this,
                myList,
                R.layout.item2,
                new String[]{"1", "checkbox"},
                new int[]{R.id.textView2});
        listView.setAdapter(adapter);

    }
    private void setmArrayList() {
        //預設九宮格的功能
        itemName = new ArrayList<>();
        imageRes = new ArrayList<>();
        //未登入
        if (Application.Login == false) {
            for (int i = 0; i < itemName3.length; i++) {
                itemName.add(itemName3[i]);
                imageRes.add(imageRes3[i]);
                mCheckSet.add(i);
            }
        //已登入
        } else {
            itemName4 = Application.itemName4;
            imageRes4 = Application.imageRes4;
            Log.e("itemName4", String.valueOf(itemName4[0]));
            for (int i = 0; i < itemName4.length; i++) {
                itemName.add(itemName4[i]);
                imageRes.add(imageRes4[i]);
                mCheckSet.add(i);
            }

        }

    }
        /*
        itemName.add("审前调查");
        itemName.add("需求评估");
        mCheckSet.add(0);
        mCheckSet.add(1);
        //預設九宮格圖片
        imageRes = new ArrayList<>();
        imageRes.add(R.drawable.pig64);
        imageRes.add(R.drawable.pig64);
        */
    private void toolBar() {
        //Toolbar 設定
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        TextView textView = (TextView)findViewById(R.id.textTitle);
        textView.setText(Application.toolbarTitle);
    }

    private void setSeachView(){

        final SearchView mSearchView = (SearchView)findViewById(R.id.search);
        mSearchView.setIconifiedByDefault(true);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.onActionViewExpanded();
        //mSearchView.setBackgroundColor(0x22ff00ff);
        mSearchView.setIconifiedByDefault(true);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Toast.makeText(MainActivity.this,query,Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


    }




}
