package com.example.segundoauqui.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    MyBoundService myBoundService;

    Button playSong;
    Button pauseSong;
    Button stopSong;
    public static final String TAG = "BindTag";
    private boolean isBound = false;
    Integer getRandomData;
    static MediaPlayer mp3P;
    EditText etText;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        playSong = (Button) findViewById(R.id.playMusic);
        stopSong = (Button) findViewById(R.id.stopMusic);
        etText = (EditText) findViewById(R.id.etText);



        playSong.setOnClickListener(this);
        stopSong.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == playSong){
            startService(new Intent(this, MediaPalyerService.class));
        }else if(view == stopSong){
            stopService(new Intent(this, MediaPalyerService.class));

        }

    }

//
//    else if(view == stopSong){
//        stopService(new Intent(this, MediaPalyerService.class));




    public void startService(View view) {

        Intent normalIntent = new Intent(this, MyNormalService.class);
        Intent intIntent = new Intent(this, MyIntentService.class);

        Intent boundIntent = new Intent(this, MyBoundService.class);

        Intent integerI = new Intent(this, ReceiveService.class);
        Intent intent = new Intent(this, ReceiveService.class);


        switch (view.getId()){


            case R.id.btnStartNormalService:
                normalIntent.putExtra("data", "This is a normal service");
                startService(normalIntent);
                break;

            case R.id.btnStopNormalService:
                stopService(normalIntent);
                break;

            case R.id.btnStartIntentService:
                intIntent.putExtra("data", "This is an intent service");
                startService(intIntent);
                break;

            case R.id.btnBindService:
                bindService(boundIntent,serviceConnection,Context.BIND_AUTO_CREATE);
                break;

            case R.id.btnSendI:

                intent.putExtra("text", etText.getText().toString());
                startActivity(intent);
                break;





//                Random randomGenerator = new Random();
//                int val = randomGenerator.nextInt(100);
//                integerI.putExtra("data", String.valueOf(val));
//                startActivity(integerI);
//                Log.d(TAG, "startService: " + integerI.getStringExtra("data"));

            case R.id.btnUnBindService:

                if (isBound) {
                    unbindService((serviceConnection));
                    isBound = false;
                }
                break;
        }
    }

    ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyBoundService.MyBinder myBinder = (MyBoundService.MyBinder) iBinder;
            myBoundService = myBinder.getService();
            myBoundService.getRandomData();
            Log.d(TAG, "onServiceConnected: " + myBoundService.getRandomData());
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected: ");
            isBound = true;

        }
    };


}
