package com.example.hci_project;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalTime;

public class LightSensor extends AlramTimer implements SensorEventListener {

    public SensorManager sensorManager;
    public Sensor lightSensor;
    public String light = "";
    public int darknessCount = 0;
    public String TAG = "LightSensor";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"Create");

        sensorManager = (SensorManager) getApplicationContext().getSystemService(Context.SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        if( lightSensor == null )
            Toast.makeText(this, "No Light Sensor Found!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"Resume");
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if( sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT){
            light = String.valueOf(sensorEvent.values[0]);
            if(Integer.parseInt(light) < 500){
                // FIXME : 어돕고 휴대폰 할때 light의 변수 값에 대해 파악해서 숫자 변경
                darknessCount += 1;
            }else{
                darknessCount = 0;
            }

            if(darknessCount > 50 && getCurrentHour() > 22){
                // TODO : 알람 trigger을 위한 broadcast 진행
                //      : 특정 시간 이상일때 작동하도록 설정 (현재 오후 10로 설정)
            }
            Log.d(TAG,"ligth : "+ light);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public int getCurrentHour(){
        return LocalTime.now().getHour();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
