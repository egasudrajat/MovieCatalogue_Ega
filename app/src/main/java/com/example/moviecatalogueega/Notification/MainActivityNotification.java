package com.example.moviecatalogueega.Notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.moviecatalogueega.MainActivity;
import com.example.moviecatalogueega.R;

public class MainActivityNotification extends AppCompatActivity {
    public static final String NOTIFICATION_CHANNEL_ID = "channel_id";
    public static final String CHANNEL_NAME = "Notification Channel";
    public static final int NOTIFICATION_ID = 888;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_notification);
        Button btnlaunch = findViewById(R.id.btnlaunch);
        btnlaunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                //Notification channel should only be created for devices running Android 26
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, CHANNEL_NAME, importance);
                    //Boolean value to set if lights are enabled for Notifications from this Channel
                    notificationChannel.enableLights(true);
                    //Boolean value to set if vibration are enabled for Notifications from this Channel
                    notificationChannel.enableVibration(true);
                    //Sets the color of Notification Light
                    notificationChannel.setLightColor(Color.GREEN);
                    //Set the vibration pattern for notifications. Pattern is in milliseconds with the format {delay,play,sleep,play,sleep...}
                    notificationChannel.setVibrationPattern(new long[]{ 500, 500, 500, 500, 500
                    });

                    //Sets whether notifications from these Channel should be visible on Lockscreen or not
                    notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    notificationManager.createNotificationChannel(notificationChannel);

                    Intent notifyIntent = new Intent(v.getContext(), MainActivity.class);
                    notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    PendingIntent notifyPendingIntent =
                            PendingIntent.getActivity(
                                    v.getContext(),
                                    0,
                                    notifyIntent,
                                    PendingIntent.FLAG_UPDATE_CURRENT
                            );

                    Notification notification = new NotificationCompat.Builder(v.getContext(), NOTIFICATION_CHANNEL_ID)
                            .setContentTitle("This is the title")
                            .setContentText("This is the subtext")
                            .setSmallIcon(R.drawable.ic_shopping)
                            .setContentIntent(notifyPendingIntent)
                            .setStyle(new NotificationCompat.BigTextStyle().bigText("This is the expandable content text"))
                            .build();
                    notification.flags |= Notification.FLAG_AUTO_CANCEL; //menghilangkan notifikasi setelah di click
                    NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(v.getContext());
                    notificationManagerCompat.notify(NOTIFICATION_ID, notification);

                }
            }
        });
    }

}
