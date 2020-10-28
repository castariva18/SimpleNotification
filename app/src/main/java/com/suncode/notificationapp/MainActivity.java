package com.suncode.notificationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int notificationId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_set);
        findViewById(R.id.btn_cancel);

    }

    @Override
    public void onClick(View view) {

        EditText etTitle = findViewById(R.id.et_title);
        TimePicker timePicker = findViewById(R.id.time_Picker);

        Intent i = new Intent(MainActivity.this, AlarmReceiver.class);
        i.putExtra("notificationId", notificationId);
        i.putExtra("Message", etTitle.getText().toString());


        PendingIntent alarmIntent = PendingIntent.getBroadcast(
                MainActivity.this, 0, i, PendingIntent.FLAG_CANCEL_CURRENT
        );

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        switch (view.getId()) {
            case R.id.btn_set:
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hour);
                startTime.set(Calendar.MINUTE, minute);
                startTime.set(Calendar.SECOND, 0);

                long alarmStartTime = startTime.getTimeInMillis();

                Toast.makeText(MainActivity.this, "Done!", Toast.LENGTH_LONG).show();
                break;


            case R.id.btn_cancel:
                alarmManager.cancel(alarmIntent);
                Toast.makeText(MainActivity.this, "Canceled", Toast.LENGTH_LONG).show();
                break;
        }
    }
}