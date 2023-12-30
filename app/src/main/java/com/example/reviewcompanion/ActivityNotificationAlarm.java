package com.example.reviewcompanion;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.core.app.NotificationCompat;


class ActivityNotificationAlarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction() == null ||
                !intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            // Check if the app hasn't been opened yet
            if (!isAppOpened(context)) {
                // App hasn't been opened, show a notification
                showNotification(context);
            }
        }
    }

    private boolean isAppOpened(Context context) {
        // Implement logic to check if the app has been opened
        // You can use SharedPreferences, database, or any other means to track this
        // For example, using SharedPreferences to track if the app has been opened
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("AppOpened", false);
    }

    private void showNotification(Context context) {
        // Create and show a notification
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Notification channel is required for Android Oreo and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("default_channel", "Default Channel", NotificationManager.IMPORTANCE_DEFAULT);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }

        Intent notificationIntent = new Intent(context, ActivityOTP.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "default_channel")
                .setContentTitle("hello!")
                .setContentText("Wanna Take a quiz?")
//              .setSmallIcon(R.drawable.ic_notification_icon) // icon for the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        if (notificationManager != null) {
            notificationManager.notify(1, builder.build());
        }
    }
}
