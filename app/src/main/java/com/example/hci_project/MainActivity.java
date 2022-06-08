package com.example.hci_project;

import static android.widget.Toast.makeText;

import static androidx.core.app.NotificationCompat.DEFAULT_SOUND;
import static androidx.core.app.NotificationCompat.DEFAULT_VIBRATE;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.time.DayOfWeek;
import java.time.LocalDate;

import com.example.hci_project.script.MessageScript;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends LightSensor implements View.OnClickListener, AdapterView.OnItemSelectedListener {

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


    private int count = 1;

    final static String[] AM_PM = {"AM", "PM"};
    final static int WEEK = 7;


    static String[] hours;
    static String[] minute;

    private SharedPreferences timeData;
    // 월 - 일 의 AM/PM | 시간 | 분 을 저장하기 위한 배열
    private int[] weekAMPM; // 0 : AM, 1 : PM
    private String[] weekHours;
    private String[] weekMinutes;

    // 월 - 일 의 AM/PM | 시간 | 분 의 NumberPicker 정의
    private NumberPicker[] weekAMPMNumberPicker;
    private NumberPicker[] weekHourNumberPicker;
    private NumberPicker[] weekMinNumberPicker;

    // 다음날 AM/PM, 시간, 분에 대해 저장하는 textview
    private TextView tomorrowAMPM;
    private TextView tomorrowHour;
    private TextView tomorrowMin;

    private LinearLayout[] weekSections;

    private static final String CHANNEL_ID = "10000";


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
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
        weekAMPM = new int[WEEK];
        weekHours = new String[WEEK];
        weekMinutes = new String[WEEK];

        // 월 - 일 AM/PM | 시간 | 분 NumberPicker 설정
        weekAMPMNumberPicker = new NumberPicker[WEEK];
        weekHourNumberPicker = new NumberPicker[WEEK];
        weekMinNumberPicker = new NumberPicker[WEEK];

        // 월 - 일 다음날 알람에 대한 뒷배경 변경을 위한 Linearlayout 초기화
        weekSections = new LinearLayout[WEEK];


        weekHourNumberPicker[0] = findViewById(R.id.monday_hour);
        weekHourNumberPicker[1] = findViewById(R.id.tuesday_hour);
        weekHourNumberPicker[2] = findViewById(R.id.wednesday_hour);
        weekHourNumberPicker[3] = findViewById(R.id.thursday_hour);
        weekHourNumberPicker[4] = findViewById(R.id.friday_hour);
        weekHourNumberPicker[5] = findViewById(R.id.saturday_hour);
        weekHourNumberPicker[6] = findViewById(R.id.sunday_hour);

        weekMinNumberPicker[0] = findViewById(R.id.monday_min);
        weekMinNumberPicker[1] = findViewById(R.id.tuesday_min);
        weekMinNumberPicker[2] = findViewById(R.id.wednesday_min);
        weekMinNumberPicker[3] = findViewById(R.id.thursday_min);
        weekMinNumberPicker[4] = findViewById(R.id.friday_min);
        weekMinNumberPicker[5] = findViewById(R.id.saturday_min);
        weekMinNumberPicker[6] = findViewById(R.id.sunday_min);

        weekAMPMNumberPicker[0] = findViewById(R.id.monday_AMPM);
        weekAMPMNumberPicker[1] = findViewById(R.id.tuesday_AMPM);
        weekAMPMNumberPicker[2] = findViewById(R.id.wednesday_AMPM);
        weekAMPMNumberPicker[3] = findViewById(R.id.thursday_AMPM);
        weekAMPMNumberPicker[4] = findViewById(R.id.friday_AMPM);
        weekAMPMNumberPicker[5] = findViewById(R.id.saturday_AMPM);
        weekAMPMNumberPicker[6] = findViewById(R.id.sunday_AMPM);

        alarmTestButton = findViewById(R.id.alarmTest);

        mondaySaveButton = findViewById(R.id.monday_save);
        tuesdaySaveButton = findViewById(R.id.tuesday_save);
        wednesdaySaveButton = findViewById(R.id.wednesday_save);
        thursdaySaveButton = findViewById(R.id.thursday_save);
        fridaySaveButton = findViewById(R.id.friday_save);
        saturdaySaveButton = findViewById(R.id.saturday_save);
        sundaySaveButton = findViewById(R.id.sunday_save);

        mondaySaveButton.setOnClickListener(this);
        tuesdaySaveButton.setOnClickListener(this);
        wednesdaySaveButton.setOnClickListener(this);
        thursdaySaveButton.setOnClickListener(this);
        fridaySaveButton.setOnClickListener(this);
        saturdaySaveButton.setOnClickListener(this);
        sundaySaveButton.setOnClickListener(this);

        alarmTestButton.setOnClickListener(this);

        tomorrowAMPM = findViewById(R.id.tomorrow_AMPM);
        tomorrowHour = findViewById(R.id.tomorrow_hour);
        tomorrowMin = findViewById(R.id.tomorrow_min);

        weekSections[0] = findViewById(R.id.monday_section);
        weekSections[1] = findViewById(R.id.tuesday_section);
        weekSections[2] = findViewById(R.id.wednesday_section);
        weekSections[3] = findViewById(R.id.thursday_section);
        weekSections[4] = findViewById(R.id.friday_section);
        weekSections[5] = findViewById(R.id.saturday_section);
        weekSections[6] = findViewById(R.id.sunday_section);

        // 잠시 없어져라 얍
//        alarmTestButton.setVisibility(View.GONE);

        for (int i = 0; i < WEEK; i++) {
            setNumberPickerHour(weekHourNumberPicker[i]);
            setNumberPickerMinute(weekMinNumberPicker[i]);
            setNumberPickerAMPM(weekAMPMNumberPicker[i]);
        }

        loadTime();
        displayTomorrowAlarmTime();

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
     *
     * @param day : 월=0, 화 =1, 수=2, 목=3, 금=4, 토=5, 일=6
     * @return 저장 여부
     */
    private boolean saveTime(int day) {
        SharedPreferences.Editor editor = timeData.edit();

        if (-1 < day && day < weekHours.length) {

            editor.putString(String.format("%s Hour", day), weekHours[day]);
            editor.putString(String.format("%s Min", day), weekMinutes[day]);
            // "0" : AM , "1" : PM
            editor.putString(String.format("%s_AMPM", day), weekAMPM[day] + "");

            editor.apply();
            return true;
        }
        return false;
    }

    /**
     * 다음날이 무슨 요일인지 반환하는 메소드
     * @return 1 ~ 7 | 1: 월요일, 7: 일요일
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private int getTomorrowDay(){
        LocalDate date = LocalDate.now();
        DayOfWeek dayofWeek = date.getDayOfWeek();

        // 다음날 어떤 요일인지 저장 (2 ~ 8)
        int tomorrowDayOfWeekNumber = dayofWeek.getValue() + 1;

        return tomorrowDayOfWeekNumber > 7 ? 1 : tomorrowDayOfWeekNumber;

    }

    /**
     * 다음날 저장된 알람 정보에 대해 상단에 표시
     * @return true
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean displayTomorrowAlarmTime(){
        int tomorrow = getTomorrowDay() - 1;

        changeDaySectionColor(tomorrow);

        tomorrowAMPM.setText(AM_PM[weekAMPM[tomorrow]]);
        tomorrowHour.setText(weekHours[tomorrow]);
        tomorrowMin.setText(weekMinutes[tomorrow]);

        return true;
    }

    private boolean changeDaySectionColor(int day){

        weekSections[day].setBackgroundResource(R.drawable.selectshape);

        for(int i = 0; i < WEEK && i != day; i++ ){
            weekSections[i].setBackgroundResource(R.drawable.unselectshape);
        }

        return true;
    }

    /**
     * 상단바 Notification 및 head-up 알림
     *
     * @param channelId : 알람 체널 id
     * @param id        : 알람 id (int)
     * @param title     : 알람 타이틀
     * @param text      : 알람 메세지 내용
     * @return          : 알람 성공 시 true 반환
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean createNotification(String channelId, int id, String title, String text) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, "sleep notify", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("this is sleep notification");
            channel.setShowBadge(true);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

            NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);

            RemoteViews notificationLayout = new RemoteViews(getPackageName(), R.layout.custom_notification);
            notificationLayout.setImageViewResource(R.id.img, R.mipmap.ic_launcher);
            notificationLayout.setTextViewText(R.id.title, "수면 배터리");
            notificationLayout.setTextViewText(R.id.message, getMessageScript(new Random().nextInt(10)));

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setSmallIcon(getNotification(count))
                    .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                    .setCustomContentView(notificationLayout)
                    .setDefaults(DEFAULT_SOUND | DEFAULT_VIBRATE)
                    .setContentTitle(title)
                    .setContentText(text)
                    .setOngoing(true);
            notificationManager.notify(id, builder.build());

            return true;
        }
        return false;
    }
    private int getNotification(int noti){
        int notiImage = 0;
        switch(noti){
            case 1 :
                notiImage = R.drawable.battery1;
                break;
            case 2 :
                notiImage = R.drawable.battery2;
                break;
            case 3 :
                notiImage = R.drawable.battery3;
                break;
            case 4 :
                notiImage = R.drawable.battery4;
                break;
            case 5 :
                notiImage = R.drawable.battery5;
                break;
            case 6 :
                notiImage = R.drawable.battery6;
                break;
            case 7 :
                notiImage = R.drawable.battery7;
                break;
            case 8 :
                notiImage = R.drawable.battery8;
                break;
            default :
                notiImage = R.drawable.battery9;
                break;
        }
        return notiImage;
    }


    private boolean loadTime() {

        for (int i = 0; i < WEEK; i++) {
            weekHours[i] = timeData.getString(String.format("%s Hour", i), "1");
            weekMinutes[i] = timeData.getString(String.format("%s Min", i), "0");
            weekAMPM[i] = Integer.parseInt(timeData.getString(String.format("%s_AMPM", i), "0"));
        }

        for (int i = 0; i < WEEK; i++) {
            weekAMPMNumberPicker[i].setValue(weekAMPM[i]);
            weekHourNumberPicker[i].setValue(Integer.parseInt(weekHours[i]) - 1);
            weekMinNumberPicker[i].setValue(Integer.parseInt(weekMinutes[i]));
        }
        // TODO : AM PM에 대해 구현 필요

        return true;
    }

    public boolean setNumberPickerAMPM(NumberPicker np) {
        if (np == null) {
            return false;
        }

        np.setMinValue(0);
        np.setMaxValue(1);
        np.setDisplayedValues(AM_PM);
        np.setWrapSelectorWheel(true);

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


    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean saveHourMin(String day, NumberPicker AMPMnp, NumberPicker hnp, NumberPicker mnp) {
        if (AMPMnp == null || hnp == null || mnp == null) {
            return false;
        }

        String tAMPM = AM_PM[AMPMnp.getValue()];
        int iAMPM = 0;

        if (tAMPM.equals("PM")) {
            iAMPM = 1;
        }
        String tHour = hours[hnp.getValue()];
        String tMin = minute[mnp.getValue()];

        switch (day) {
            case "월요일":
                weekHours[0] = tHour;
                weekMinutes[0] = tMin;
                weekAMPM[0] = iAMPM;
                saveTime(0);
                break;
            case "화요일":
                weekHours[1] = tHour;
                weekMinutes[1] = tMin;
                weekAMPM[1] = iAMPM;
                saveTime(1);
                break;
            case "수요일":
                weekHours[2] = tHour;
                weekMinutes[2] = tMin;
                weekAMPM[2] = iAMPM;
                saveTime(2);
                break;
            case "목요일":
                weekHours[3] = tHour;
                weekMinutes[3] = tMin;
                weekAMPM[3] = iAMPM;
                saveTime(3);
                break;
            case "금요일":
                weekHours[4] = tHour;
                weekMinutes[4] = tMin;
                weekAMPM[4] = iAMPM;
                saveTime(4);
                break;
            case "토요일":
                weekHours[5] = tHour;
                weekMinutes[5] = tMin;
                weekAMPM[5] = iAMPM;
                saveTime(5);
                break;
            case "일요일":
                weekHours[6] = tHour;
                weekMinutes[6] = tMin;
                weekAMPM[6] = iAMPM;
                saveTime(6);
                break;
            default:
                return false;
        }

        Toast.makeText(getApplicationContext(), String.format("%s %s %s시 %s분 알람 저장 완료!",
                day, tAMPM, tHour, tMin), Toast.LENGTH_SHORT).show();

        displayTomorrowAlarmTime();

        return true;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.alarmTest:
                int alarmType = (int) (Math.random() * 9); // 0 ~ 8
                int alarmScript = (int) (Math.random() * 10); // 0 ~ 9

                getAlarm(alarmType, alarmScript);
                createNotification(CHANNEL_ID, 1, "test", "test");
                count++;
                break;
            case R.id.monday_save:

                saveHourMin("월요일", weekAMPMNumberPicker[0], weekHourNumberPicker[0], weekMinNumberPicker[0]);
                break;
            case R.id.tuesday_save:
                saveHourMin("화요일", weekAMPMNumberPicker[1], weekHourNumberPicker[1], weekMinNumberPicker[1]);
                break;
            case R.id.wednesday_save:
                saveHourMin("수요일", weekAMPMNumberPicker[2], weekHourNumberPicker[2], weekMinNumberPicker[2]);
                break;
            case R.id.thursday_save:
                saveHourMin("목요일", weekAMPMNumberPicker[3], weekHourNumberPicker[3], weekMinNumberPicker[3]);
                break;
            case R.id.friday_save:
                saveHourMin("금요일", weekAMPMNumberPicker[4], weekHourNumberPicker[4], weekMinNumberPicker[4]);
                break;
            case R.id.saturday_save:
                saveHourMin("토요일", weekAMPMNumberPicker[5], weekHourNumberPicker[5], weekMinNumberPicker[5]);
                break;
            case R.id.sunday_save:
                saveHourMin("일요일", weekAMPMNumberPicker[6], weekHourNumberPicker[6], weekMinNumberPicker[6]);

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