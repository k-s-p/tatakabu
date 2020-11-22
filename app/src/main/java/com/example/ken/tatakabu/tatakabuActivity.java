package com.example.ken.tatakabu;

import java.util.Random;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

public class tatakabuActivity extends AppCompatActivity implements OnClickListener{
    ImageButton piko,gado;
    int hands[] = new int[3];
    int hands2[] = new int[3];
    int n2,jan;
    ImageView me2,enemy2;
    TextView result2;
    int hand=0, n=0;

    int syouri = 0;
    int make = 0;
    aite a = new aite();
    zibun z = new zibun();
    ImageView enemyhp,mehp;
    int ehp[] = new int[3];
    int mhp[] = new int[3];
    int hand2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tataiteactivity);
        hands[0] = R.drawable.gu;
        hands[1] = R.drawable.choki;
        hands[2] = R.drawable.pa;
        hands2[0] = R.drawable.nothing;
        hands2[2] = R.drawable.gado;
        hands2[1] = R.drawable.piko;
        me2 = (ImageView) findViewById(R.id.me2);
        enemy2 = (ImageView) findViewById(R.id.enemy2);
        gado = (ImageButton) findViewById(R.id.gado);
        piko = (ImageButton) findViewById(R.id.piko);
        gado.setOnClickListener(this);
        piko.setOnClickListener(this);
        result2 = (TextView) findViewById(R.id.result2);


        Intent intent = getIntent();
        a = (aite)intent.getSerializableExtra("boku");    //aiteを受け取る
        z = (zibun)intent.getSerializableExtra("teki"); //zibunを受け取る
        hand = intent.getIntExtra("me", 0);
        n = intent.getIntExtra("enemy", 0);

        me2.setImageResource(hands2[0]);  //初期は何もしない画像
        enemy2.setImageResource(hands2[0]);  //初期は何もしない画像

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

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {    //ハンドラを用いて一定時間後に動作させることができる（要勉強・・・）
            @Override
            public void run() {
                //じゃんけんに勝った時と負けたときの相手の手を選択
                jan = (hand - n) + 3;
                if (jan % 3 == 2) {
                    n2 = a.kaburu();
                } else {
                    n2 = a.tataku();
                    gado.setEnabled(false);
                    piko.setEnabled(false);

                    if (hand2 == 2) {   //自分が負け防ぐ
                        result2.setText("防いだ！");
                    } else {
                        result2.setText("叩かれた！");
                        z.hp--;
                    }
                    if(a.hp==0 || z.hp==0) {
                        //結果画面に遷移
                        int i = z.hp;
                        Intent intent = new Intent(tatakabuActivity.this, ResultActivity.class);
                        intent.putExtra("me", i);
                        startActivity(intent);
                    } else {
                        //じゃんけん画面に再送信
                        Intent intent2 = new Intent(tatakabuActivity.this, janActivity.class); //thisだけではだめ！　なぜかはわからん
                        intent2.putExtra("boku", a); //シリアライズ化が必要？
                        intent2.putExtra("teki", z);
                        startActivity(intent2);
                    }
                }
                enemy2.setImageResource(hands2[n2]);
            }
        }, a.hannou);


    }

    @Override
    public void onClick(View v) {
        gado.setEnabled(false);
        piko.setEnabled(false);

        //2:gado,1:piko
        if (v == gado) {
            hand2 = 2;
        } else if (v == piko) {
            hand2 = 1;
        }
        me2.setImageResource(hands2[hand2]);

        int jan = (hand - n) + 3;
        if (jan % 3 == 2) {//自分が勝って叩く
            if (hand2 == 1) {
                if (n2 != 2) {
                    result2.setText("叩いた！");
                    a.hp--;
                } else {
                    result2.setText("防がれた!");
                }
            }else {
                result2.setText("間違えた");
            }
            if(a.hp==0 || z.hp==0) {
                //結果画面に遷移
                int i = z.hp;
                Intent intent = new Intent(this, ResultActivity.class);
                intent.putExtra("me", i);
                startActivity(intent);
            } else {
                //じゃんけん画面に再送信
                Intent intent = new Intent(this, janActivity.class);
                intent.putExtra("boku", a); //シリアライズ化が必要？
                intent.putExtra("teki", z);
                startActivity(intent);
            }
        } else {
            if (hand2 == 2 ){
                if(n2 == 1) {   //自分が負け防ぐ
                    result2.setText("防いだ！");
                }
            }
        }


    }


}
