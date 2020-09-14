package com.example.mytherapy;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper {
    public static  final String channelid="channel1";
    private NotificationManager notificationManager;
    public  static final String channelname="Patients Personal Assistant";

    public NotificationHelper(Context base) {
        super(base);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
            creatChannels();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void creatChannels() {
        NotificationChannel channel1=new NotificationChannel(channelid,channelname, NotificationManager.IMPORTANCE_HIGH);
        channel1.enableLights(true);
        channel1.enableVibration(true);
        channel1.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getManager().createNotificationChannel(channel1);
    }
    public NotificationManager getManager()
    {
       if (notificationManager==null)
       {
           notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
       }
       return notificationManager;
    }
public NotificationCompat.Builder getchannel1Notification(String title,String message)
{
    return new NotificationCompat.Builder(getApplicationContext(),channelid)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_baseline_notifications_active_24);
}
}
