package com.example.ken.tatakabu;

import java.io.Serializable;
import java.util.Random;

public class aite implements Serializable {
    String name;    //名前（難易度）
    int hp = 3;         //体力（３）
    int hannou;     //反応時間

    int  janKen() { //呼び出されるとランダムでぐーちょきぱーを返す
        Random random = new Random();
        int n = random.nextInt(3); //0:グー、1:チョき,2:パー
        return n;
    }

    int tataku(){     //呼び出されると反応時間を待ったのち叩く    0:なにもしない,1:叩く
        int t = 1;
        return t;
    }

    int kaburu() {  //呼び出されると反応時間を待ったのちかぶる   0:なにもしない,2:かぶる
        int k = 2;
        return k;
    }
}
