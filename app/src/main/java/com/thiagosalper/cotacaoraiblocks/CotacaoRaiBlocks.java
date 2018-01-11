package com.thiagosalper.cotacaoraiblocks;

import android.app.Application;

import com.onesignal.OneSignal;

/**
 * Created by thiagopereira on 11/01/2018.
 */

public class CotacaoRaiBlocks extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

        // Call syncHashedEmail anywhere in your app if you have the user's email.
        // This improves the effectiveness of OneSignal's "best-time" notification scheduling feature.
        // OneSignal.syncHashedEmail(userEmail);
    }
}