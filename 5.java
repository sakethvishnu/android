package com.example.week5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.view.View;
import android.widget.*;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button notify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("My Notification" , "My Notification" , NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        notify = (Button) findViewById(R.id.notify);
        notify.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        if(v.getId() == R.id.notify){
            NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this,"My Notification");
            builder.setContentTitle("Week 5");
            builder.setContentText("Successfully completed Week 5");
            builder.setSmallIcon(R.drawable.ic_launcher_background);
            builder.setAutoCancel(true);

            NotificationManagerCompat manager = NotificationManagerCompat.from(MainActivity.this);
            manager.notify(1, builder.build());
        }
    }
}