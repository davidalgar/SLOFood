package me.algar.slofood;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.parse.ParseAnalytics;
import com.parse.ParsePushBroadcastReceiver;

import org.json.JSONException;
import org.json.JSONObject;


public class PushNotificationReciever extends ParsePushBroadcastReceiver {
    @Override
    protected void onPushOpen(Context context, Intent intent) {
        ParseAnalytics.trackAppOpenedInBackground(intent);

        String gpsCoords = null;
        String truckName = null;
        String schedule = null;
        try {
            JSONObject cls = new JSONObject(intent.getStringExtra("com.parse.Data"));
            gpsCoords = cls.optString("gps", null);
            truckName = cls.optString("truckName", null);
            schedule = cls.optString("schedule", null);
        } catch (JSONException exception) {
            Log.e("Tag", "bad things happened");
        }

        // if strings are setup correctly, we can fashion an intent to MapsActivity
        Intent showTruck = new Intent(context, MapsActivity.class);
        showTruck.putExtra(MapsActivity.EXTRA_GPS, gpsCoords);
        showTruck.putExtra(MapsActivity.EXTRA_TRUCK_NAME, truckName);
        showTruck.putExtra(MapsActivity.EXTRA_SCHEDULE, schedule);

        showTruck.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(showTruck);
    }
}
