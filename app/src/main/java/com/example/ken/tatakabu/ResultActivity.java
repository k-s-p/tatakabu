package com.example.ken.tatakabu;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ResultActivity extends AppCompatActivity {
    Button retry;
    TextView resultmes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int me;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tatakabu_result);
        retry = (Button) findViewById(R.id.retry);
        resultmes = (TextView) findViewById(R.id.resultmes);

        Intent intent = getIntent();    //マニフェストに書き込むのを忘れずに！
        me = intent.getIntExtra("me", 0);

        if(me == 0){
            resultmes.setText("あなたの負けです");
        } else {
            resultmes.setText("あなたの勝ちです");
        }
    }

    public void retrybutton(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
