package com.example.segundoauqui.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;


public class ReceiveService extends AppCompatActivity {

    private static final String TAG = "Data Received";
    MyBoundService myBoundService;
    boolean isBound;
    TextView tvReceive;
    Intent newintent;



    ArrayList<String> randomStringList;
    RecyclerView rvRandomStrings;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.ItemAnimator itemAnimator;
    RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_service);
        Intent intent = getIntent();
        String p = intent.getStringExtra("text");
        tvReceive = (TextView) findViewById(R.id.tvReceive);
        Intent boundIntent = new Intent(this, MyBoundService.class);
        boundIntent.putExtra("text",p);
        bindService(boundIntent, serviceConnection, Context.BIND_AUTO_CREATE);
        //recyclerview here
        rvRandomStrings = (RecyclerView) findViewById(R.id.rvRandomStrings);
        layoutManager = new LinearLayoutManager(this);
        itemAnimator = new DefaultItemAnimator();
        rvRandomStrings.setLayoutManager(layoutManager);
        rvRandomStrings.setItemAnimator(itemAnimator);
    }



    ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "onServiceConnectedStarted");
            MyBoundService.MyBinder myBinder = (MyBoundService.MyBinder) iBinder;
            myBoundService = myBinder.getService();
            randomStringList =  myBoundService.getRandomData();
            //readSum.setText(String.valueOf(myBoundService.getRandomData()));
            isBound = true;
            //adapter
            mAdapter = new Adapter(randomStringList);
            rvRandomStrings.setAdapter(mAdapter);





//            MyBoundService.MyBinder myBinder = (MyBoundService.MyBinder) iBinder;
//            myBoundService = myBinder.getService();
//            newSum = Integer.parseInt(getIntent().getStringExtra("data")) + myBoundService.getRandomData();
//            tvReceive.setText(String.valueOf(newSum));
//            Log.d(TAG, "onServiceConnected: " + myBoundService.getInteger());
//            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected: ");
            isBound = true;
        }
    };
}
