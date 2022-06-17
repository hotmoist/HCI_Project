package com.example.hci_project;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.Random;

public class AlramReceiver extends BroadcastReceiver {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
//        Toast.makeText(context.getApplicationContext(),"이거지",Toast.LENGTH_SHORT).show();
        Log.d("이거지","이거지");
        Log.d("이거지",MainActivity.interval+"");
        MainActivity.interval+=1;
        if(MainActivity.interval%90==1){
            MainActivity.alram=true;
        }
    }
}
