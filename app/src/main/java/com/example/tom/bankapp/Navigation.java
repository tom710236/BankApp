package com.example.tom.bankapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.example.tom.bankapp.R.drawable.f1;
import static com.example.tom.bankapp.R.drawable.f2;
import static com.example.tom.bankapp.R.drawable.f3;
import static com.example.tom.bankapp.R.drawable.f4;
import static com.example.tom.bankapp.R.drawable.f5;
import static com.example.tom.bankapp.R.drawable.f6;
import static com.example.tom.bankapp.R.drawable.f7;
import static com.example.tom.bankapp.R.drawable.f8;
import static com.example.tom.bankapp.R.drawable.pig64;
import static com.example.tom.bankapp.R.layout.item;

public class Navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {

    String token = "null";
    SQLiteDatabase db;
    GridView mGridView;
    MyAdapter myAdapter;
    private ArrayList<String> myDataset;
    public class ItemInfo {
        public String mItemName;
        public int mItemImage;
        public Uri mItemUrl;

        ItemInfo(String ItemName, int ItemImage, Uri ItemUrl){
            this.mItemName = ItemName;
            this.mItemImage = ItemImage;
            this.mItemUrl = ItemUrl;
        }
    }


    SimpleAdapter adapter;

    ArrayList<LinkedHashMap<String, String>> myList;
    ArrayList<ItemInfo> itemInfoArray,itemInfoArrayList2,itemInfoArrayList4;
    LinkedHashMap<String, String> map;
    Boolean add = false;

    int image0 = f1
            ,image1 = f2
            ,image2 = f3
            ,image3 = f4
            ,image4 = f5
            ,image5 = f6
            ,image6 = f7
            ,image7 = f8
            ,image8 = pig64
            ,image9 = pig64
            ,image10 = pig64
            ,image11 = pig64
            ,image12 = pig64
            ,image13 = pig64
            ,image14 = pig64;

    String name0 = "最新消息"
            ,name1 = "基金淨值"
            ,name2 = "優惠好康"
            ,name3 = "基金交易"
            ,name4 = "到價通知"
            ,name5 = "關注基金"
            ,name6 = "理財講堂"
            ,name7 = "關於我們"
            ,name8 = "線上客服"
            ,name9 = "線上開戶"
            ,name10 = "我要開戶"
            ,name11 = "配息一欄表"
            ,name12 = "基金手續費\n"+"一欄表"
            ,name13 = "理財講座報\n"+"名查詢"
            ,name14 = "投資試算";


    Uri uri0 = Uri.parse("http://appportal.shinda.com.tw/ImportantNotice.aspx")
            ,uri1 = Uri.parse("http://appportal.shinda.com.tw/FundList.aspx")
            ,uri2 = Uri.parse("http://appportal.shinda.com.tw/PromotionsList.aspx")
            ,uri3 = Uri.parse("http://demo.shinda.com.tw/prototype/APP%20Portal/#p=基金交易")
            ,uri4 = Uri.parse("http://demo.shinda.com.tw/prototype/APP%20Portal/#p=到價通知")
            ,uri5 = Uri.parse("http://appportal.shinda.com.tw/FundList.aspx")
            ,uri6 = Uri.parse("http://appportal.shinda.com.tw/FinancialLectureList.aspx")
            ,uri7 = Uri.parse("http://appportal.shinda.com.tw/CompanyIntroduction.aspx")
            ,uri8 = Uri.parse("https://juejin.im/entry/5780b8481532bc005f2b81f3")
            ,uri9 = Uri.parse("http://demo.shinda.com.tw/prototype/APP%20Portal/#p=線上開戶")
            ,uri10 = Uri.parse("http://appportal.shinda.com.tw/FormDownload.aspx")
            ,uri11 = Uri.parse("http://appportal.shinda.com.tw/FundRation.aspx")
            ,uri12 = Uri.parse("http://appportal.shinda.com.tw/FundFeeRate.aspx")
            ,uri13 = Uri.parse("http://appportal.shinda.com.tw/LectureRegistration.aspx")
            ,uri14 = Uri.parse("http://invest.fubonlife.com.tw/MoneyCalc/calc05.htm");

    ArrayList<String> itemName;
    ArrayList<Integer> imageRes;
    //九宮格全部功能
    private String[] itemName2 = {name0,name1,name2,name3,name4,name5,name6,name7,name8,name9,name10,name11,name12,name13,name14};
    // 九宮格全部功能的圖片
    private int[] imageRes2 = {image0,image1,image2,image3,image4,image5,image6,image7,image8,image9,image10,image11,image12,image13,image14};
    //九宮格預設功能
    private String[] itemName3 = {name0,name1,name2,name3,name4};
    //九宮格預設功能的圖片
    private int[] imageRes3 = {image0,image1,image2,image3,image4};
    //九宮格登入後功能
    private String[] itemName4;
    //九宮格登入後的圖片
    private int[] imageRes4;
    //前往網址
    private Uri[] uris = {uri0,uri1,uri2,uri3,uri4,uri5,uri6,uri7,uri8,uri9,uri10,uri11,uri12,uri13,uri14} ;
    //紀錄勾勾
    private HashSet<Integer> mCheckSet = new HashSet<Integer>();

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);


        setItem();
        setmListView();
        toolBar();
        setmArrayList();
        //setmGridView();
        setmListView2();
        setSeachView();
        setLinearLayout();
        //啟動service 讓7.0的手機可以常駐後台
        Intent it = new Intent(Navigation.this, Delay.class);
        startService(it);
        //setMyGridView();

        setRecyclerView();
        setMyRecycleView();
        if (Application.Login == true) {
            ImageButton button = (ImageButton)findViewById(R.id.imageButton);
            button.setVisibility(View.VISIBLE);
            Button button1 = (Button)findViewById(R.id.button2);
            button1.setText("登出");
        }


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



        /*
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        */
        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }
    //設定右邊三點
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.navigation, menu);

        return true;
    }
    //點擊右邊三點後的動作
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Toast.makeText(this,"camera",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    //側邊抽屜的清單
    private void setmListView() {
        myList = new ArrayList();
        for (int i = 0; i < itemInfoArray.size(); i++) {
            map = new LinkedHashMap<String, String>();
            map.put("1", itemInfoArray.get(i).mItemName);
            myList.add(map);
        }
        ListView listView = (ListView) findViewById(R.id.navigationListview);
        ListAdapter adapter = new ArrayAdapter<String>(Navigation.this,android.R.layout.simple_list_item_1,itemName2);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

    }

    //側邊抽屜功能點擊
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       Log.e("點擊", String.valueOf(myList.get(position)));
        switch (position){
            case 0 :
                setmUri(0);
                break;
            case 1 :
                setmUri(1);
                break;
            case 2 :
                setmUri(2);
                break;
            case 3 :
                setmUri(3);
                break;
            case 4 :
                setmUri(4);
                break;
            case 5 :
                setmUri(5);
                break;
            case 6 :
                setmUri(6);
                break;
            case 7 :
                setmUri(7);
                break;
            case 8 :
                setmUri(8);
                break;
            case 9 :
                setmUri(9);
                break;
            case 10 :
                setmUri(10);
                break;
            case 11 :
                setmUri(11);
                break;
            case 12 :
                setmUri(12);
                break;
            case 13 :
                setmUri(13);
                break;
            case 14 :
                setmUri(14);
                break;
        }
    }
    //點擊後跳到所點擊的網頁
    private void setmUri (int i){
        Intent it = new Intent(this, WebViewActivity.class);
        Application.WebUri = String.valueOf(uris[i]);
        startActivity(it);
        Navigation.this.finish();

    }


    private void toolBar() {

        //Toolbar 設定
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        TextView textView = (TextView)findViewById(R.id.textTitle);

        toolbar.setBackgroundColor(Application.MainToolbarBackGroundColor);
        textView.setText(Application.MainToolbarText);
        textView.setTextColor(Application.MainToolbarTextColor);
        textView.setTextSize(Application.MainToolbarTextSize);

    }
    private void setmArrayList() {
        //預設九宮格的功能

        itemInfoArrayList4 = new ArrayList<>();
        //未登入
        if (Application.Login == false) {
            //把未登入的預設值放入
            for(int i = 0 ; i< itemName3.length; i++){
                itemInfoArrayList4.add(new ItemInfo(itemName3[i],imageRes3[i],uris[i]));

                //mCheckSet.add(i);
            }

            //已登入
        } else {
            //新增後 功能不會跑掉
            if(Application.add == true){
                itemInfoArrayList4 = Application.itemInfoArrayList4;
                //記住登入後功能的勾勾
                for(int i = 0 ; i <itemInfoArrayList4.size() ; i++){
                    switch (itemInfoArrayList4.get(i).mItemName){
                        case "最新消息" :
                            checkItem(name0);
                            break;
                        case "基金淨值" :
                            checkItem(name1);
                            break;
                        case "優惠好康" :
                            checkItem(name2);
                            break;
                        case "基金交易" :
                            checkItem(name3);
                            break;
                        case "到價通知" :
                            checkItem(name4);
                            break;
                        case "關注基金" :
                            checkItem(name5);
                            break;
                        case "理財講堂" :
                            checkItem(name6);
                            break;
                        case "關於我們" :
                            checkItem(name7);
                            break;
                        case "線上客服" :
                            checkItem(name8);
                            break;
                        case "線上開戶" :
                            checkItem(name9);
                            break;
                        case "我要開戶" :
                            checkItem(name10);
                            break;
                        case "配息一欄表" :
                            checkItem(name11);
                            break;
                        case "基金手續費一欄表" :
                            checkItem(name12);
                            break;
                        case "理財講座報名查詢" :
                            checkItem(name13);
                            break;
                        case "投資試算" :
                            checkItem(name14);
                            break;
                    }

                }


            }else {
                //按下新增功能確定前
                itemName4 = Application.itemName4;
                imageRes4 = Application.imageRes4;
                itemInfoArrayList4 = new ArrayList<>();
                for (int i = 0; i < itemName4.length; i++) {
                    itemInfoArrayList4.add(new ItemInfo(itemName4[i],imageRes4[i],uris[i]));
                    mCheckSet.add(i);
                }
            }

        }
    }

    private void checkItem(String name){
        for(int i2 = 0 ; i2<itemInfoArray.size() ; i2++){
            if(itemInfoArray.get(i2).mItemName.equals(name)){
                mCheckSet.add(i2);
            }
        }

    }

    private void setmGridView() {
        //九宮格的設定
        mGridView = (GridView) findViewById(R.id.MyGridView);
        List<LinkedHashMap<String, Object>> data = new ArrayList<LinkedHashMap<String, Object>>();
        int length = itemInfoArrayList4.size();
        for (int i = 0; i < length; i++) {
            LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("ItemImageView", itemInfoArrayList4.get(i).mItemImage);
            map.put("ItemTextView", itemInfoArrayList4.get(i).mItemName);
            data.add(map);
        }
        Log.e("Data", String.valueOf(data));
        //为itme.xml添加适配器
        SimpleAdapter simpleAdapter = new SimpleAdapter(Navigation.this,
                data, item, new String[]{"ItemImageView", "ItemTextView"}, new int[]{R.id.ItemImageView, R.id.ItemTextView});
        mGridView.setAdapter(simpleAdapter);
        //为mGridView添加点击事件监听器
        mGridView.setOnItemClickListener(new GridViewItemOnClick());
    }

    //定义九宮格点击事件监听器
    public class GridViewItemOnClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getApplicationContext(), itemInfoArrayList4.get(position).mItemName, Toast.LENGTH_SHORT).show();

            switch (itemInfoArrayList4.get(position).mItemName){
                case "最新消息" :
                    setmUri(0);
                    break;
                case "基金淨值" :
                    setmUri(1);
                    break;
                case "優惠好康" :
                    setmUri(2);
                    break;
                case "基金交易" :
                    setmUri(3);
                    break;
                case "到價通知" :
                    setmUri(4);
                    break;
                case "關注基金" :
                    setmUri(5);
                    break;
                case "理財講堂" :
                    setmUri(6);
                    break;
                case "關於我們" :
                    setmUri(7);
                    break;
                case "線上客服" :
                    setmUri(8);
                    break;
                case "線上開戶" :
                    setmUri(9);
                    break;
                case "我要開戶" :
                    setmUri(10);
                    break;
                case "配息一欄表" :
                    setmUri(11);
                    break;
                case "基金手續費一欄表" :
                    setmUri(12);
                    break;
                case "理財講座報名查詢" :
                    setmUri(13);
                    break;
                case "投資試算" :
                    setmUri(14);
                    break;
            }
        }
    }


    public void onLogin(View v){
        if(Application.Login == false){
            setmListView2();
            Intent it = new Intent(this , LoginActivity.class);
            startActivity(it);
            Navigation.this.finish();

        }else {
            setmListView2();
            Application.Login = false;
            Intent it = new Intent(this , Navigation.class);
            Application.Web = false;
            Application.Login2 = false;
            startActivity(it);
            Navigation.this.finish();
            overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
        }

    }


    public void onAdd(View v) {
        LinearLayout linear = (LinearLayout) findViewById(R.id.linear);
        LinearLayout linear2 = (LinearLayout) findViewById(R.id.linear2);
        ScrollView linear3 = (ScrollView) findViewById(R.id.linear3);
        ScrollView linear4 = (ScrollView) findViewById(R.id.linear4);
        ScrollView linear5 = (ScrollView) findViewById(R.id.linear5);
        linear.setVisibility(View.GONE);
        linear2.setVisibility(View.GONE);
        linear3.setVisibility(View.GONE);
        linear5.setVisibility(View.GONE);
        linear4.setVisibility(View.VISIBLE);
    }

    public void onBack(View v) {
        LinearLayout linear = (LinearLayout) findViewById(R.id.linear);
        LinearLayout linear2 = (LinearLayout) findViewById(R.id.linear2);
        ScrollView linear3 = (ScrollView) findViewById(R.id.linear3);
        ScrollView linear4 = (ScrollView) findViewById(R.id.linear4);
        ScrollView linear5 = (ScrollView) findViewById(R.id.linear5);
        linear.setVisibility(View.VISIBLE);
        linear2.setVisibility(View.VISIBLE);
        linear3.setVisibility(View.VISIBLE);
        linear5.setVisibility(View.VISIBLE);
        linear4.setVisibility(View.GONE);

        add = true;
        Application.add = add;
        //adapter.notifyDataSetChanged();
        myAdapter.notifyDataSetChanged();
    }
    // 新增我的最愛的list
    private void setmListView2() {
        myList = new ArrayList();
        for (int i = 0; i < itemName2.length; i++) {
            map = new LinkedHashMap<String, String>();
            map.put("1", itemName2[i]);
            myList.add(map);
        }
        ListView listView = (ListView) findViewById(R.id.listView);
        adapter = new SpecialAdapter(
                Navigation.this,
                myList,
                R.layout.item2,
                new String[]{"1", "checkbox"},
                new int[]{R.id.textView2});
        listView.setAdapter(adapter);

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
                convertView = View.inflate(Navigation.this, R.layout.item2, null);
                final CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
                //留住勾勾
                checkBox.setChecked(mCheckSet.contains(position));
                checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (((CheckBox) v).isChecked()) {

                            itemInfoArrayList4.add(new ItemInfo(itemName2[position],imageRes2[position],uris[0]));
                            mCheckSet.add(position);

                        } else {

                            for (int i = 0 ; i<itemInfoArrayList4.size() ; i++ ){
                                if(itemInfoArrayList4.get(i).mItemName.equals(itemName2[position])){
                                    itemInfoArrayList4.remove(i);
                                    i--;
                                    mCheckSet.remove(position);
                                }
                            }
                        }
                        Application.itemInfoArrayList4 = itemInfoArrayList4;
                        Log.e("itemArray", String.valueOf(Application.itemInfoArrayList4));


                    }
                });

            }



            return super.getView(position, convertView, parent);
        }

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

                Toast.makeText(Navigation.this,query,Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }
    //設定Dialog
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
    //設定背景顏色
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void setLinearLayout (){
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linear);
        linearLayout.setBackground(getResources().getDrawable(Application.MainBackgroundPicture));
    }
    //設定背景顏色
    private void setMyGridView(){
        GridView gridView = (GridView)findViewById(R.id.MyGridView);
        gridView.setBackgroundColor(0x99e4ecee);
    }
    //設定背景顏色
    private void setMyRecycleView(){
        ScrollView linear5 = (ScrollView) findViewById(R.id.linear5);
        linear5.setBackgroundColor(0x99e4ecee);
    }
    private void setItem(){
        //全部
        itemInfoArray = new ArrayList<>();
        for (int i = 0 ; i<15 ;i++){
            itemInfoArray.add(new ItemInfo(itemName2[i],imageRes2[i],uris[i]));
        }

        //登入前
        itemInfoArrayList2 = new ArrayList<>();
        for(int i = 0 ;i<4 ; i++ ){
            itemInfoArrayList2.add(new ItemInfo(itemName2[i],imageRes2[i],uris[i]));
        }


    }


    private void setRecyclerView(){

        if(itemInfoArrayList4.size()!=0){
            myAdapter = new MyAdapter(itemInfoArrayList4);
        }

        RecyclerView mList = (RecyclerView) findViewById(R.id.list_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mList.setLayoutManager(layoutManager);
        mList.setAdapter(myAdapter);

        /*
        ItemTouchHelper.Callback mCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP|ItemTouchHelper.DOWN|ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT,ItemTouchHelper.START|ItemTouchHelper.END) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int fromPosition = viewHolder.getAdapterPosition();
                int toPosition = target.getAdapterPosition();
                myAdapter.notifyItemMoved(fromPosition, toPosition);

                return true;
            }

            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(getDragDirs(recyclerView, viewHolder), getSwipeDirs(recyclerView, viewHolder));
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                myDataset.remove(position);
                myAdapter.notifyItemRemoved(position);


            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    //给被拖曳的 item 设置一个深颜色背景
                    viewHolder.itemView.setBackgroundColor(0x30e4ecee);
                    viewHolder.itemView.setScaleX(0.9f);
                    viewHolder.itemView.setScaleY(0.9f);
                }
                super.onSelectedChanged(viewHolder, actionState);
            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                viewHolder.itemView.setBackgroundColor(0x99e4ecee);
                viewHolder.itemView.setScaleX(1.0f);
                viewHolder.itemView.setScaleY(1.0f);
                super.clearView(recyclerView, viewHolder);
            }

        };
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(mCallback);
        mItemTouchHelper.attachToRecyclerView(mList);
        */
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            //通过返回值来设置是否处理某次拖曳或者滑动事件
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                int swipeFlags = 0;

                    return makeMovementFlags(dragFlags, swipeFlags);

            }
            //当长按并进入拖曳状态时，拖曳的过程中不断的回调此方法，返回值为是否处理事件
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

                myAdapter.onItemMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());

                return true;
            }
            //滑动删除的回调
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    //给被拖曳的 item 设置一个深颜色背景
                    viewHolder.itemView.setBackgroundColor(0x30e4ecee);
                    viewHolder.itemView.setScaleX(0.9f);
                    viewHolder.itemView.setScaleY(0.9f);
                }
                super.onSelectedChanged(viewHolder, actionState);
            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                viewHolder.itemView.setBackgroundColor(0x00e4ecee);
                viewHolder.itemView.setScaleX(1.0f);
                viewHolder.itemView.setScaleY(1.0f);
                super.clearView(recyclerView, viewHolder);
                myAdapter.notifyDataSetChanged();
            }


        });
        itemTouchHelper.attachToRecyclerView(mList);

    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private ArrayList<Navigation.ItemInfo> mData;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;
            public ImageView mImage;
            public ViewHolder(View v) {
                super(v);
                mTextView = (TextView) v.findViewById(R.id.ItemTextView);
                mImage = (ImageView) v.findViewById(R.id.ItemImageView);
            }
        }

        public MyAdapter(ArrayList<Navigation.ItemInfo> data) {


                if (Application.Web == false){
                    mData = data;
                }else {
                    mData = Application.mData;
                    Application.Web = false;
                }

        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            //holder.mTextView.setText((CharSequence) mData.get(position));

            holder.mTextView.setText(mData.get(position).mItemName);
            holder.mImage.setImageResource(mData.get(position).mItemImage);
            //短按
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("短", mData.get(position).mItemName);

                    switch (mData.get(position).mItemName){
                        case "最新消息" :
                            setmUri(0);
                            break;
                        case "基金淨值" :
                            setmUri(1);
                            break;
                        case "優惠好康" :
                            setmUri(2);
                            break;
                        case "基金交易" :
                            setmUri(3);
                            break;
                        case "到價通知" :
                            setmUri(4);
                            break;
                        case "關注基金" :
                            setmUri(5);
                            break;
                        case "理財講堂" :
                            setmUri(6);
                            break;
                        case "關於我們" :
                            setmUri(7);
                            break;
                        case "線上客服" :
                            setmUri(8);
                            break;
                        case "線上開戶" :
                            setmUri(9);
                            break;
                        case "我要開戶" :
                            setmUri(10);
                            break;
                        case "配息一欄表" :
                            setmUri(11);
                            break;
                        case "基金手續費一欄表" :
                            setmUri(12);
                            break;
                        case "理財講座報名查詢" :
                            setmUri(13);
                            break;
                        case "投資試算" :
                            setmUri(14);
                            break;
                    }
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Log.e("長", String.valueOf(position));
                    return true;
                }
            });

        }

        @Override
        public int getItemCount() {
            return mData.size();
        }


        public void onItemMove(int fromPosition, int toPosition) {
            //交换位置
            Collections.swap(mData,fromPosition,toPosition);
            notifyItemMoved(fromPosition,toPosition);
            for(int i = 0 ; i<mData.size(); i++){
                Log.e("mdata2",mData.get(i).mItemName);
            }
            Application.mData = mData;
            Application.Login2 = true;
        }
    }
}

