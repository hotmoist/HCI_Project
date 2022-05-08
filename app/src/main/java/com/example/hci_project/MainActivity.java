package com.example.hci_project;

import static android.widget.Toast.makeText;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hci_project.script.MessageScript;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    /** 컴포넌트 변수 */
    private Button mondaySaveButton;
    private Button tuesdaySaveButton;
    private Button wednesdaySaveButton;
    private Button thursdaySaveButton;
    private Button fridaySaveButton;
    private Button saturdaySaveButton;
    private Button sundaySaveButton;

    private Button alarmTestButton;

    private Spinner mondayAMPMSpinner;
    private Spinner tuesdayAMPMSpinner;
    private Spinner wednesdayAMPMSpinner;
    private Spinner thursdayAMPMSpinner;
    private Spinner fridayAMPMSpinner;
    private Spinner saturdayAMPMSpinner;
    private Spinner sundayAMPMSpinner;

    private EditText mondayHourEditText;
    private EditText tuesdayHourEditText;
    private EditText wednesdayHourEditText;
    private EditText thursdayHourEditText;
    private EditText fridayHourEditText;
    private EditText saturdayHourEditText;
    private EditText sundayHourEditText;

    private EditText mondayMinEditText;
    private EditText tuesdayMinEditText;
    private EditText wednesdayMinEditText;
    private EditText thursdayMinEditText;
    private EditText fridayMinEditText;
    private EditText saturdayMinEditText;
    private EditText sundayMinEditText;
    final static String[] AM_PM = {"AM", "PM"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarmTestButton = findViewById(R.id.alarmTest);

        mondayHourEditText = findViewById(R.id.monday_hour);
        tuesdayHourEditText = findViewById(R.id.tuesday_hour);
        wednesdayHourEditText = findViewById(R.id.wednesday_hour);
        thursdayHourEditText = findViewById(R.id.thursday_hour);
        fridayHourEditText = findViewById(R.id.friday_hour);
        saturdayHourEditText = findViewById(R.id.saturday_hour);
        sundayHourEditText = findViewById(R.id.sunday_hour);

        mondayMinEditText = findViewById(R.id.monday_min);
        tuesdayMinEditText = findViewById(R.id.tuesday_min);
        wednesdayMinEditText = findViewById(R.id.wednesday_min);
        thursdayMinEditText = findViewById(R.id.thursday_min);
        fridayMinEditText = findViewById(R.id.friday_min);
        saturdayMinEditText = findViewById(R.id.saturday_min);
        sundayMinEditText = findViewById(R.id.sunday_min);

        mondaySaveButton = findViewById(R.id.monday_save);
        tuesdaySaveButton = findViewById(R.id.tuesday_save);
        wednesdaySaveButton = findViewById(R.id.wednesday_save);
        thursdaySaveButton = findViewById(R.id.thursday_save);
        fridaySaveButton = findViewById(R.id.friday_save);
        saturdaySaveButton = findViewById(R.id.saturday_save);
        sundaySaveButton = findViewById(R.id.sunday_save);

        mondayAMPMSpinner = findViewById(R.id.monday_AMPM);
        tuesdayAMPMSpinner = findViewById(R.id.tuesday_AMPM);
        wednesdayAMPMSpinner = findViewById(R.id.wednesday_AMPM);
        thursdayAMPMSpinner = findViewById(R.id.thursday_AMPM);
        fridayAMPMSpinner = findViewById(R.id.friday_AMPM);
        saturdayAMPMSpinner = findViewById(R.id.saturday_AMPM);
        sundayAMPMSpinner = findViewById(R.id.sunday_AMPM);

        mondaySaveButton.setOnClickListener(this);
        tuesdaySaveButton.setOnClickListener(this);
        wednesdaySaveButton.setOnClickListener(this);
        thursdaySaveButton.setOnClickListener(this);
        fridaySaveButton.setOnClickListener(this);
        saturdaySaveButton.setOnClickListener(this);
        sundaySaveButton.setOnClickListener(this);

        alarmTestButton.setOnClickListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, AM_PM);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mondayAMPMSpinner.setAdapter(adapter);
        mondayAMPMSpinner.setOnItemSelectedListener(this);
        tuesdayAMPMSpinner.setAdapter(adapter);
        tuesdayAMPMSpinner.setOnItemSelectedListener(this);
        wednesdayAMPMSpinner.setAdapter(adapter);
        wednesdayAMPMSpinner.setOnItemSelectedListener(this);
        thursdayAMPMSpinner.setAdapter(adapter);
        thursdayAMPMSpinner.setOnItemSelectedListener(this);
        fridayAMPMSpinner.setAdapter(adapter);
        fridayAMPMSpinner.setOnItemSelectedListener(this);
        saturdayAMPMSpinner.setAdapter(adapter);
        saturdayAMPMSpinner.setOnItemSelectedListener(this);
        sundayAMPMSpinner.setAdapter(adapter);
        sundayAMPMSpinner.setOnItemSelectedListener(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.alarmTest:
                int alarmType = (int) (Math.random() * 9); // 0 ~ 8
                int alarmScript = (int) (Math.random() * 10); // 0 ~ 9
                getAlarm(alarmType, alarmScript);
                break;
            case R.id.monday_save:
                Toast.makeText(getApplicationContext(), String.format("월요일 %s %s시 %s분 알람 저장 완료! ",
                        mondayAMPMSpinner.getSelectedItem().toString(), mondayHourEditText.getText(), mondayMinEditText.getText()),Toast.LENGTH_LONG).show();
                break;
            case R.id.tuesday_save:
                Toast.makeText(getApplicationContext(), String.format("화요일 %s %s시 %s분 알람 저장 완료! ",
                        tuesdayAMPMSpinner.getSelectedItem().toString(), tuesdayHourEditText.getText(), tuesdayMinEditText.getText()), Toast.LENGTH_LONG).show();
                break;
            case R.id.wednesday_save:
                Toast.makeText(getApplicationContext(), String.format("수요일 %s %s시 %s분 알람 저장 완료! ",
                        wednesdayAMPMSpinner.getSelectedItem().toString(), wednesdayHourEditText.getText(), wednesdayMinEditText.getText()), Toast.LENGTH_LONG).show();
                break;
            case R.id.thursday_save:
                Toast.makeText(getApplicationContext(), String.format("목요일 %s %s시 %s분 알람 저장 완료! ",
                        thursdayAMPMSpinner.getSelectedItem().toString(), thursdayHourEditText.getText(), thursdayMinEditText.getText()), Toast.LENGTH_LONG).show();
                break;
            case R.id.friday_save:
                Toast.makeText(getApplicationContext(), String.format("금요일 %s %s시 %s분 알람 저장 완료! ",
                        fridayAMPMSpinner.getSelectedItem().toString(), fridayHourEditText.getText(), fridayMinEditText.getText()), Toast.LENGTH_LONG).show();
                break;
            case R.id.saturday_save:
                Toast.makeText(getApplicationContext(), String.format("토요일 %s %s시 %s분 알람 저장 완료! ",
                        saturdayAMPMSpinner.getSelectedItem().toString(), saturdayHourEditText.getText(), saturdayMinEditText.getText()), Toast.LENGTH_LONG).show();
                break;
            case R.id.sunday_save:
                Toast.makeText(getApplicationContext(), String.format("일요일 %s %s시 %s분 알람 저장 완료! ",
                        sundayAMPMSpinner.getSelectedItem().toString(), sundayHourEditText.getText(), sundayMinEditText.getText()), Toast.LENGTH_LONG).show();
                break;
            default:
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        ((TextView) adapterView.getChildAt(0)).setTextSize(20);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void getAlarm(int type, int messageIndex){
        if(type < 10){
            showToastAlarm(type, messageIndex);
        }
    }

    public void showToastAlarm(int type, int messageIndex){
        String message = getMessageScript(messageIndex);
        switch (type){
            case 1 :
                FancyToast.makeText(this, message, FancyToast.LENGTH_LONG, FancyToast.DEFAULT, true).show();
                break;
            case 2:
                FancyToast.makeText(this, message, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                break;
            case 3:
                FancyToast.makeText(this, message, FancyToast.LENGTH_LONG, FancyToast.INFO, true).show();
                break;
            case 4:
                FancyToast.makeText(this, message, FancyToast.LENGTH_LONG, FancyToast.WARNING, true).show();
                break;
            case 5:
                FancyToast.makeText(this, message, FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                break;
            case 6:
                FancyToast.makeText(this, message, FancyToast.LENGTH_LONG, FancyToast.CONFUSING, true).show();
                break;
            default:
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }

    public String getMessageScript(int index){
        return MessageScript.getMessage(index);
    }

    public void alarmTimer(){
        // 알람을 작동하기 위한 타이머 구현
    }
}