package com.example.tom.bankapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.tom.bankapp.R.drawable.f1;
import static com.example.tom.bankapp.R.drawable.f2;
import static com.example.tom.bankapp.R.drawable.f3;
import static com.example.tom.bankapp.R.drawable.f4;
import static com.example.tom.bankapp.R.drawable.f5;
import static com.example.tom.bankapp.R.drawable.f6;
import static com.example.tom.bankapp.R.drawable.f7;
import static com.example.tom.bankapp.R.drawable.f8;
import static com.example.tom.bankapp.R.drawable.pig64;

public class LoginActivity extends AppCompatActivity {
    EditText editName ,editNumber;
    String cName , cNumber;
    CheckBox checkBox;
    int image = f1
            ,image2 = f2
            ,image3 = f3
            ,image4 = f4
            ,image5 = f5
            ,image6 = f6
            ,image7 = f7
            ,image8 = f8
            ,image9 = pig64
            ,image10 = pig64
            ,image11 = pig64
            ,image12 = pig64
            ,image13 = pig64
            ,image14 = pig64
            ,image15 = pig64;
    String name = "最新消息"
            ,name2 = "基金淨值"
            ,name3 = "優惠好康"
            ,name4 = "基金交易"
            ,name5 = "到價通知"
            ,name6 = "關注基金"
            ,name7 = "理財講堂"
            ,name8 = "關於我們"
            ,name9 = "線上客服"
            ,name10 = "線上開戶"
            ,name11 = "我要開戶"
            ,name12 = "配息一欄表"
            ,name13 = "基金手續費一欄表"
            ,name14 = "理財講座報名查詢"
            ,name15 = "投資試算";

    //登入後 預設功能
    private String[] itemName4 = {name,name2,name3,name4,name5,name6,name7,name8};
    private int[] imageRes4 = {image,image2,image3,image4,image5,image6,image7,image8};


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //帳號若輸入正確 記住登入帳號
        SharedPreferences setting =
                getSharedPreferences("Login", MODE_PRIVATE);
        EditText uId = (EditText) findViewById(R.id.editText);
        uId.setText(setting.getString("userName", ""));
        toolBar();


        checkBox = (CheckBox)findViewById(R.id.checkBox2);
        editName = (EditText)findViewById(R.id.editText);

        editName.setBackground(getResources().getDrawable(R.drawable.edittext));

        if(editName.getText().toString().length()> 0){
            checkBox.setChecked(true);
        }else {
            checkBox.setChecked(false);
        }

        setButton(findViewById(R.id.cancel),Application.LoginCancelTextSize,Application.LoginCancelTextColor,Application.LoginCancelBackgroundColor);
        setButton(findViewById(R.id.Login),Application.LoginLoginTextSize,Application.LoginLoginTextColor,Application.LoginLoginBackgroundColor);
    }

    private void toolBar() {
        //Toolbar 設定
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        TextView textView = (TextView)findViewById(R.id.textTitle);
        textView.setText(Application.LoginToolbarText);
        toolbar.setBackgroundColor(Application.LoginToolbarBackGroundColor);
        textView.setTextColor(Application.LoginToolbarTextColor);
        textView.setTextSize(Application.LoginToolbarTextSize);
    }

    public void onCancel (View v){
        Intent it = new Intent(this,Navigation.class);
        startActivity(it);
        LoginActivity.this.finish();

    }

    public void onLogin (View v){
        editName = (EditText)findViewById(R.id.editText);
        editNumber = (EditText)findViewById(R.id.editText2);

        cName = editName.getText().toString();
        cNumber = editNumber.getText().toString();
        //登入判斷
        if(cName.equals("tom") && cNumber.equals("123")){
            Application.Login = true;
            Intent it = new Intent(this,Navigation.class);
            //登入後預設功能
            Application.itemName4 = itemName4;
            Application.imageRes4 = imageRes4;
            //是否記住帳號
            checkBox = (CheckBox)findViewById(R.id.checkBox2);
            setCheckbox();
            //登入後回到首頁
            startActivity(it);
            LoginActivity.this.finish();

        }else {
            Toast.makeText(this,"登入失敗",Toast.LENGTH_SHORT).show();
        }
    }
    private void setCheckbox(){
        checkBox = (CheckBox)findViewById(R.id.checkBox2);

        if(checkBox.isChecked()){
            SharedPreferences setting = getSharedPreferences("Login", MODE_PRIVATE);
            setting.edit()
                    .putString("userName", cName)
                    .commit();

        }else {
            SharedPreferences setting = getSharedPreferences("Login", MODE_PRIVATE);
            setting.edit()
                    .putString("userName", "")
                    .commit();
            cName = null;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void setButton (View v , int TextSize , int TextColor , int BackgroundColor ){
        Button button = (Button)v ;
        button.setTextSize(TextSize);
        button.setTextColor(TextColor);

        button.setBackground(getResources().getDrawable(R.drawable.button));
    }
}
