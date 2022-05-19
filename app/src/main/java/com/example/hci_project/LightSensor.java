package com.example.hci_project;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LightSensor extends AlramTimer implements SensorEventListener {

    public SensorManager sensorManager;
    public Sensor lightSensor;
    public String light = "";
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
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if( sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT){
            light = String.valueOf(sensorEvent.values[0]);
            Log.d(TAG,"ligth : "+ light);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
