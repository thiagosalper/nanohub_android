package com.thiagosalper.cotacaoraiblocks;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.RemoteViews;

import com.thiagosalper.cotacaoraiblocks.services.WidgetServiceProvider;

import java.util.Random;


public class WidgetProviderDark extends AppWidgetProvider{
    Intent intent;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int count = appWidgetIds.length;

        ComponentName thisWidget = new ComponentName(context, WidgetProviderDark.class);
        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);

        intent = new Intent(context.getApplicationContext(), WidgetServiceProvider.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds);
        context.startService(intent);
    }

}
