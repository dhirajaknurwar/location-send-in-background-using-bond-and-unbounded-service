package com.master.locationsendsinbackground;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

public class LocationProviderOnOffReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {

        ContentResolver contentResolver = context.getContentResolver();
        // Find out what the settings say about which providers are enabled
        int mode = Settings.Secure.getInt(
                contentResolver, Settings.Secure.LOCATION_MODE, Settings.Secure.LOCATION_MODE_OFF);

        if (mode == Settings.Secure.LOCATION_MODE_OFF) {
            // Location is turned OFF!
            showNotification(context, "You turned Offed Location, to use this Network Enable Location.");
        }
    }

    private void showNotification(Context context, String title) {
        int NOTIFICATION_ID = (int) System.currentTimeMillis();


        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context)
                        .setColor(context.getResources().getColor(R.color.colorPrimary))
                        .setSmallIcon(getNotificationIcon())
                        .setContentTitle("Location Off Alert")
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(title))
                        .setDefaults(NotificationCompat.DEFAULT_VIBRATE)
                        .setDefaults(NotificationCompat.DEFAULT_LIGHTS)
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                        .setAutoCancel(false);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        Notification notification = notificationBuilder.build();
        notification.flags |= Notification.FLAG_ONLY_ALERT_ONCE;
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    //Getting notification icon as per SDK version
    public static int getNotificationIcon() {
        boolean whiteIcon = (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP);
        return whiteIcon ? R.mipmap.ic_launcher : R.mipmap.ic_launcher;
    }
}