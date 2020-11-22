package com.example.ken.tatakabu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button)findViewById(R.id.first)).setOnClickListener(this);
        ((Button)findViewById(R.id.second)).setOnClickListener(this);
        ((Button)findViewById(R.id.third)).setOnClickListener(this);

    }

    private void onfirst(View view) { //firstが押されたとき、相手と自分を作成して次のスレッドにインテントする
        //初期値を設定
        aite a = new aite();
        a.name = "初級";
        a.hannou = 1500;

        zibun z = new zibun();
        //じゃんけん画面に送信
        Intent intent = new Intent(this, janActivity.class);
        intent.putExtra("boku", a); //シリアライズ化が必要？
        intent.putExtra("teki", z);
        startActivity(intent);
    }
    private void onsecond(View view) { //secondが押されたとき、相手と自分を作成して次のスレッドにインテントする
        //初期値を設定
        aite a = new aite();
        a.name = "中級";
        a.hannou = 1000;

        zibun z = new zibun();
        //じゃんけん画面に送信
        Intent intent = new Intent(this, janActivity.class);
        intent.putExtra("boku", a); //シリアライズ化が必要？
        intent.putExtra("teki", z);
        startActivity(intent);
    }

    private void onthird(View view) { //thirdが押されたとき、相手と自分を作成して次のスレッドにインテントする
        //初期値を設定
        aite a = new aite();
        a.name = "上級";
        a.hannou = 700;

        zibun z = new zibun();
        //じゃんけん画面に送信
        Intent intent = new Intent(this, janActivity.class);
        intent.putExtra("boku", a); //シリアライズ化が必要？
        intent.putExtra("teki", z);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
            switch(v.getId()) {
                case R.id.first:
                    onfirst(v);
                    break;

                case R.id.second:
                    onsecond(v);
                    break;

                case R.id.third:
                    onthird(v);
                    break;
            }
    }
}
