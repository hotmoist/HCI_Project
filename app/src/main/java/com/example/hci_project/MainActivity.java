package com.example.hci_project;

import static android.widget.Toast.makeText;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /** 컴포넌트 변수 */
    private Button mondaySaveButton;
    private Button tuesdaySaveButton;
    private Button wednesdaySaveButton;
    private Button thursdaySaveButton;
    private Button fridaySaveButton;
    private Button saturdaySaveButton;
    private Button sundaySaveButton;

    private TextView mondayAMPMTextView;
    private TextView tuesdayAMPMTextView;
    private TextView wednesdayAMPMTextView;
    private TextView thursdayAMPMTextView;
    private TextView fridayAMPMTextView;
    private TextView saturdayAMPMTextView;
    private TextView sundayAMPMTextView;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mondaySaveButton = findViewById(R.id.monday_save);
        tuesdaySaveButton = findViewById(R.id.tuesday_save);
        wednesdaySaveButton = findViewById(R.id.wednesday_save);
        thursdaySaveButton = findViewById(R.id.thursday_save);
        fridaySaveButton = findViewById(R.id.friday_save);
        saturdaySaveButton = findViewById(R.id.saturday_save);
        sundaySaveButton = findViewById(R.id.sunday_save);

        mondayAMPMTextView = findViewById(R.id.monday_AMPM);
        tuesdayAMPMTextView = findViewById(R.id.tuesday_AMPM);
        wednesdayAMPMTextView = findViewById(R.id.wednesday_AMPM);
        thursdayAMPMTextView = findViewById(R.id.thursday_AMPM);
        fridayAMPMTextView = findViewById(R.id.friday_AMPM);
        saturdayAMPMTextView = findViewById(R.id.saturday_AMPM);
        sundayAMPMTextView = findViewById(R.id.sunday_AMPM);

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


    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
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
}