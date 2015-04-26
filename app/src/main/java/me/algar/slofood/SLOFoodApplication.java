package me.algar.slofood;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;

public class SLOFoodApplication extends Application {
    public void onCreate() {
        Parse.initialize(this, "NlMpGGjKvJ3QsDBu2YELTFJAHi33BoUjDSKRswzN", "Hj97nheLRxuhRJgPMI1c9jOA9bhiCKocN9p9Fuc2");
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
