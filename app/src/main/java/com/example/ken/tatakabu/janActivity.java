package com.example.ken.tatakabu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class janActivity extends AppCompatActivity implements OnClickListener{
    ImageButton gu,choki,pa;
    int hands[] = new int[3];
    int ehp[] = new int[3];
    int mhp[] = new int[3];
    ImageView me,enemy;
    TextView result;
    aite a = new aite();
    zibun z = new zibun();
    ImageView enemyhp,mehp;


    @Override
    protected void onCreate(Bundle savedInstanceState) { //ボタンを作成？
        super.onCreate(savedInstanceState);
        setContentView(R.layout.janactivity);
        hands[0]=R.drawable.gu;
        hands[1]=R.drawable.choki;
        hands[2]=R.drawable.pa;
        me = (ImageView) findViewById(R.id.me);
        enemy = (ImageView) findViewById(R.id.enemy);
        gu = (ImageButton) findViewById(R.id.gu);
        choki = (ImageButton) findViewById(R.id.choki);
        pa = (ImageButton) findViewById(R.id.pa);
        gu.setOnClickListener(this);
        choki.setOnClickListener(this);
        pa.setOnClickListener(this);
        result = (TextView) findViewById(R.id.result);
        Intent intent = getIntent();
        a = (aite)intent.getSerializableExtra("boku");    //aiteを受け取る
        z = (zibun)intent.getSerializableExtra("teki"); //zibunを受け取る

        //体力を表示
        enemyhp = (ImageView) findViewById(R.id.enemyhp);
        mehp = (ImageView) findViewById(R.id.mehp);
        ehp[0]=R.drawable.enehp1;
        ehp[1]=R.drawable.enehp2;
        ehp[2]=R.drawable.enehp3;
        mhp[0]=R.drawable.mehp1;
        mhp[1]=R.drawable.mehp2;
        mhp[2]=R.drawable.mehp3;

        enemyhp.setImageResource(ehp[a.hp-1]);
        mehp.setImageResource(mhp[z.hp-1]);
    }


    @Override
    public void onClick(View v){    //ボタンが押されたときの動作
        int n = a.janKen();
        int hand = 0;
        //0:gu,1:choki,2:pa
        if (v == gu) {
            hand = 0;
        } else if (v == choki) {
            hand = 1;
        } else if (v == pa) {
            hand = 2;
        }
        me.setImageResource(hands[hand]);
        enemy.setImageResource(hands[n]);

        int syouhai = (hand - n) + 3;
        if (syouhai % 3 == 0) {
            result.setText("あいこで...");
        } else {
            Intent intent = new Intent(this, tatakabuActivity.class);
            intent.putExtra("boku", a);
            intent.putExtra("teki", z);
            intent.putExtra("me", hand);
            intent.putExtra("enemy", n);
            startActivity(intent);
        }
    }
}
