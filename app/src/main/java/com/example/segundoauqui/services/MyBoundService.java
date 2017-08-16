package com.example.segundoauqui.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class MyBoundService extends Service {
    private static final String TAG = "MyBoundServiceTag";
    IBinder iBinder = new MyBinder();

    String passed;

    public MyBoundService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }




    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: " );
        passed = intent.getStringExtra("text");
        return iBinder;
    }


    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

    public class MyBinder extends Binder {

       public  MyBoundService getService(){
            return MyBoundService.this;
        }
    }


    public Integer getInteger(){
        return 5;
    }


    public ArrayList<String> getRandomData(){
        Random random = new Random();
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < Integer.parseInt(passed) ; i++){
            list.add(new BigInteger(130, random).toString(32));
        }
        return list;
    }




}
