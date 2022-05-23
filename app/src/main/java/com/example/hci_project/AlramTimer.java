package com.example.hci_project;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AlramTimer extends AppCompatActivity  {
    AlarmManager alarm_manager;
    Intent alarmIntent;
    PendingIntent pendingIntent;
    @SuppressLint({"UnspecifiedImmutableFlag", "ShortAlarm"})
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Toast.makeText(this,""+calendar.getTimeInMillis(),Toast.LENGTH_SHORT).show();
        alarm_manager = (AlarmManager) getApplicationContext().getSystemService(ALARM_SERVICE);
//
        alarmIntent = new Intent(this, AlramReceiver.class);
//
        pendingIntent = PendingIntent.getBroadcast(this,1111,alarmIntent, PendingIntent.FLAG_MUTABLE);
        setAlarm(23,5,6000);
    }

    /**
     *
     * 매개변수에 값을 넣으면 그 시간에 해당하는 시간에 알람이 간다.
     * 처음에는 안오고 interval만큼 반복적으로 실행한다.
     *
     * @param hour          --> 알람을 울리고 싶은 시간대
     * @param minute        --> 알람을 울리고 싶은 시간의 분
     *
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setAlarm(int hour, int minute, int inverval){

        final Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        alarm_manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,  calendar.getTimeInMillis(), pendingIntent);
        alarm_manager.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), inverval, pendingIntent);
    }

    public void cancelAlram(){

        // 알람매니저 취소
        alarm_manager.cancel(pendingIntent);
        alarmIntent.putExtra("state","ALARM_OFF");

        // 알람 취소
        sendBroadcast(alarmIntent);

    }
}

