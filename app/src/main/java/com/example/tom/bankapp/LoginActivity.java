package com.example.tom.bankapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText editName ,editNumber;
    String cName , cNumber;
    CheckBox checkBox;
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

    //九宮格全部功能
    private String[] itemName4 = {name,name2,name3,name4,name5,name6,name7,name8,name9};
    // 九宮格全部功能的圖片
    private int[] imageRes4 = {image,image2,image3,image4,image5,image6,image7,image8,image9};


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

        if(editName.getText().toString().length()> 0){
            checkBox.setChecked(true);
        }else {
            checkBox.setChecked(false);
        }

    }

    private void toolBar() {
        //Toolbar 設定
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        TextView textView = (TextView)findViewById(R.id.textTitle);
        textView.setText(Application.toolbarTitle);
    }

    public void onCancel (View v){
        Intent it = new Intent(this,MainActivity.class);
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
            Intent it = new Intent(this,MainActivity.class);
            Application.itemName4 = itemName4;
            Application.imageRes4 = imageRes4;
            checkBox = (CheckBox)findViewById(R.id.checkBox2);

            setCheckbox();

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
}
