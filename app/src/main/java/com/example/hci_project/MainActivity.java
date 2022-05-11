package com.example.hci_project;

import static android.widget.Toast.makeText;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hci_project.script.MessageScript;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    /**
     * 컴포넌트 변수
     */
    private Button mondaySaveButton;
    private Button tuesdaySaveButton;
    private Button wednesdaySaveButton;
    private Button thursdaySaveButton;
    private Button fridaySaveButton;
    private Button saturdaySaveButton;
    private Button sundaySaveButton;

    private Button alarmTestButton;


    // TODO : 1. Spinner --> NumberPicker 형태로 변경 필요
    //      : 2. 모두 배열로 구현하기 (Refactoring)

    private Spinner mondayAMPMSpinner;
    private Spinner tuesdayAMPMSpinner;
    private Spinner wednesdayAMPMSpinner;
    private Spinner thursdayAMPMSpinner;
    private Spinner fridayAMPMSpinner;
    private Spinner saturdayAMPMSpinner;
    private Spinner sundayAMPMSpinner;

    private NumberPicker mondayHourNumberPicker;
    private NumberPicker tuesdayHourNumberPicker;
    private NumberPicker wednesdayHourNumberPicker;
    private NumberPicker thursdayHourNumberPicker;
    private NumberPicker fridayHourNumberPicker;
    private NumberPicker saturdayHourNumberPicker;
    private NumberPicker sundayHourNumberPicker;

    private NumberPicker mondayMinNumberPicker;
    private NumberPicker tuesdayMinNumberPicker;
    private NumberPicker wednesdayMinNumberPicker;
    private NumberPicker thursdayMinNumberPicker;
    private NumberPicker fridayMinNumberPicker;
    private NumberPicker saturdayMinNumberPicker;
    private NumberPicker sundayMinNumberPicker;

    final static String[] AM_PM = {"AM", "PM"};
    static String[] hours;
    static String[] minute;

    private SharedPreferences timeData;
    // 월 - 일 의 시간과 분을 저장하기 위한 배열
    private String[] weekHours;
    private String[] weekMinutes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        // 데이터 저장을 위한 공간 설정
        timeData = getSharedPreferences("timeData", MODE_PRIVATE);


        // 시간, 분 설정
        hours = new String[12];
        minute = new String[60];

        for (int i = 0; i < 12; i++) {
            hours[i] = i + 1 + "";
        }

        for (int i = 0; i < 60; i++) {
            if (i < 10) {
                minute[i] = "0" + i + "";
            } else {
                minute[i] = i + "";
            }
        }

        // 월 - 일 시간, 분 저장 공간 설정
        weekHours = new String[7];
        weekMinutes = new String[7];


        alarmTestButton = findViewById(R.id.alarmTest);

        mondayHourNumberPicker = findViewById(R.id.monday_hour);
        tuesdayHourNumberPicker = findViewById(R.id.tuesday_hour);
        wednesdayHourNumberPicker = findViewById(R.id.wednesday_hour);
        thursdayHourNumberPicker = findViewById(R.id.thursday_hour);
        fridayHourNumberPicker = findViewById(R.id.friday_hour);
        saturdayHourNumberPicker = findViewById(R.id.saturday_hour);
        sundayHourNumberPicker = findViewById(R.id.sunday_hour);


        mondayMinNumberPicker = findViewById(R.id.monday_min);
        tuesdayMinNumberPicker = findViewById(R.id.tuesday_min);
        wednesdayMinNumberPicker = findViewById(R.id.wednesday_min);
        thursdayMinNumberPicker = findViewById(R.id.thursday_min);
        fridayMinNumberPicker = findViewById(R.id.friday_min);
        saturdayMinNumberPicker = findViewById(R.id.saturday_min);
        sundayMinNumberPicker = findViewById(R.id.sunday_min);


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

        // 잠시 없어져라 얍
        alarmTestButton.setVisibility(View.GONE);

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


        setNumberPickerHour(mondayHourNumberPicker);
        setNumberPickerHour(tuesdayHourNumberPicker);
        setNumberPickerHour(wednesdayHourNumberPicker);
        setNumberPickerHour(thursdayHourNumberPicker);
        setNumberPickerHour(fridayHourNumberPicker);
        setNumberPickerHour(saturdayHourNumberPicker);
        setNumberPickerHour(sundayHourNumberPicker);

        setNumberPickerMinute(mondayMinNumberPicker);
        setNumberPickerMinute(tuesdayMinNumberPicker);
        setNumberPickerMinute(wednesdayMinNumberPicker);
        setNumberPickerMinute(thursdayMinNumberPicker);
        setNumberPickerMinute(fridayMinNumberPicker);
        setNumberPickerMinute(saturdayMinNumberPicker);
        setNumberPickerMinute(sundayMinNumberPicker);


        loadTime();
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

    /**
     * [저장] 버튼이 눌러질 때, 시간 저장
     * @param day : 월=0, 화 =1, 수=2, 목=3, 금=4, 토=5, 일=6
     * @return 저장 여부
     */
    private boolean saveTime(int day) {
        SharedPreferences.Editor editor = timeData.edit();

        if(-1 < day && day < weekHours.length ){
            // TODO : AM PM 에 대해 구현 필요

            editor.putString(String.format("%s Hour", day), weekHours[day]);
            editor.putString(String.format("%s Min", day), weekMinutes[day]);

            editor.apply();
            return true;
        }
        return false;
    }

    private boolean loadTime(){

        for(int i = 0; i < weekHours.length; i++){
            weekHours[i] = timeData.getString(String.format("%s Hour", i), "1");
            weekMinutes[i] = timeData.getString(String.format("%s Min", i), "0");
        }

        mondayHourNumberPicker.setValue(Integer.parseInt(weekHours[0])-1);
        tuesdayHourNumberPicker.setValue(Integer.parseInt(weekHours[1])-1);
        wednesdayHourNumberPicker.setValue(Integer.parseInt(weekHours[2])-1);
        thursdayHourNumberPicker.setValue(Integer.parseInt(weekHours[3])-1);
        fridayHourNumberPicker.setValue(Integer.parseInt(weekHours[4])-1);
        saturdayHourNumberPicker.setValue(Integer.parseInt(weekHours[5])-1);
        sundayHourNumberPicker.setValue(Integer.parseInt(weekHours[6])-1);

        mondayMinNumberPicker.setValue(Integer.parseInt(weekMinutes[0]));
        tuesdayMinNumberPicker.setValue(Integer.parseInt(weekMinutes[1]));
        wednesdayMinNumberPicker.setValue(Integer.parseInt(weekMinutes[2]));
        thursdayMinNumberPicker.setValue(Integer.parseInt(weekMinutes[3]));
        fridayMinNumberPicker.setValue(Integer.parseInt(weekMinutes[4]));
        saturdayMinNumberPicker.setValue(Integer.parseInt(weekMinutes[5]));
        sundayMinNumberPicker.setValue(Integer.parseInt(weekMinutes[6]));


        // TODO : AM PM에 대해 구현 필요

        return true;
    }

    public boolean setNumberPickerHour(NumberPicker np) {
        if (np == null) {
            return false;
        }

        np.setMinValue(0);
        np.setMaxValue(hours.length - 1);
        np.setDisplayedValues(hours);
        np.setWrapSelectorWheel(true);

        return true;
    }

    public boolean setNumberPickerMinute(NumberPicker np) {
        if (np == null) {
            return false;
        }

        np.setMinValue(0);
        np.setMaxValue(minute.length - 1);
        np.setDisplayedValues(minute);
        np.setWrapSelectorWheel(true);

        return true;
    }

    public boolean saveHourMin(String day, Spinner sp, NumberPicker hnp, NumberPicker mnp){
        if(sp == null || hnp == null || mnp == null){
            return false;
        }

        String tHour = hours[hnp.getValue()];
        String tMin = minute[mnp.getValue()];

        switch (day){
            case "월요일":
                weekHours[0] = tHour;
                weekMinutes[0] = tMin;
                saveTime(0);
                break;
            case "화요일":
                weekHours[1] = tHour;
                weekMinutes[1] = tMin;
                saveTime(1);
                break;
            case "수요일":
                weekHours[2] = tHour;
                weekMinutes[2] = tMin;
                saveTime(2);
                break;
            case "목요일":
                weekHours[3] = tHour;
                weekMinutes[3] = tMin;
                saveTime(3);
                break;
            case "금요일":
                weekHours[4] = tHour;
                weekMinutes[4] = tMin;
                saveTime(4);
                break;
            case "툐요일":
                weekHours[5] = tHour;
                weekMinutes[5] = tMin;
                saveTime(5);
                break;
            case "일요일":
                weekHours[6] = tHour;
                weekMinutes[6] = tMin;
                saveTime(6);
                break;
            default:
                return false;
        }

        Toast.makeText(getApplicationContext(), String.format("%s %s %s시 %s분 알람 저장 완료!",
                day, sp.getSelectedItem().toString(), tHour, tMin), Toast.LENGTH_SHORT).show();

        return true;
    }



    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.alarmTest:
                int alarmType = (int) (Math.random() * 9); // 0 ~ 8
                int alarmScript = (int) (Math.random() * 10); // 0 ~ 9
                getAlarm(alarmType, alarmScript);
                break;
            case R.id.monday_save:
                saveHourMin("월요일", mondayAMPMSpinner, mondayHourNumberPicker, mondayMinNumberPicker);
                break;
            case R.id.tuesday_save:
                saveHourMin("화요일", tuesdayAMPMSpinner, tuesdayHourNumberPicker, tuesdayMinNumberPicker);
                break;
            case R.id.wednesday_save:
                saveHourMin("수요일", wednesdayAMPMSpinner, wednesdayHourNumberPicker, wednesdayMinNumberPicker);
                break;
            case R.id.thursday_save:
                saveHourMin("목요일", thursdayAMPMSpinner, thursdayHourNumberPicker, thursdayMinNumberPicker);
                break;
            case R.id.friday_save:
                saveHourMin("금요일", fridayAMPMSpinner, fridayHourNumberPicker, fridayMinNumberPicker);
                break;
            case R.id.saturday_save:
                saveHourMin("토요일", saturdayAMPMSpinner, saturdayHourNumberPicker, saturdayMinNumberPicker);
                break;
            case R.id.sunday_save:
                saveHourMin("일요일", sundayAMPMSpinner, sundayHourNumberPicker, sundayMinNumberPicker);
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
        ((TextView) adapterView.getChildAt(0)).setTextSize(20);
    }

    public void getAlarm(int type, int messageIndex) {
        if (type < 10) {
            showToastAlarm(type, messageIndex);
        }
    }

    public void showToastAlarm(int type, int messageIndex) {
        String message = getMessageScript(messageIndex);
        switch (type) {
            case 1:
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

    public String getMessageScript(int index) {
        return MessageScript.getMessage(index);
    }

    public void alarmTimer() {
        // 알람을 작동하기 위한 타이머 구현
    }
}