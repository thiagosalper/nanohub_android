package com.thiagosalper.cotacaoraiblocks.services;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.thiagosalper.cotacaoraiblocks.R;
import com.thiagosalper.cotacaoraiblocks.WidgetProvider;
import com.thiagosalper.cotacaoraiblocks.data.ConsultaApi;
import com.thiagosalper.cotacaoraiblocks.data.ConsultaData;
import com.thiagosalper.cotacaoraiblocks.model.Moeda;
import com.thiagosalper.cotacaoraiblocks.presenter.ConsultaPresenter;

import java.util.Random;

/**
 * Created by thiagopereira on 07/01/2018.
 */

public class WidgetServiceProvider extends Service implements ConsultaPresenter {
    private ConsultaData consulta;
    public ConsultaPresenter contexto = this;

    RemoteViews remoteViews;
    int[] allWidgetIds;
    AppWidgetManager appWidgetManager;
    String LANG;

    @Override
    public void onStart(Intent intent, int startId) {
        appWidgetManager = AppWidgetManager.getInstance(this.getApplicationContext());

        allWidgetIds = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);

        LANG = Resources.getSystem().getConfiguration().locale.getLanguage();

        consulta = new ConsultaApi();
        consulta.busca(contexto);


        stopSelf();

        super.onStart(intent, startId);
    }

    @Override
    public void carregando() {

        for (int widgetId : allWidgetIds) {
            remoteViews = new RemoteViews(this.getApplicationContext().getPackageName(), R.layout.widget_cotacao);

            Intent clickIntent = new Intent(this.getApplicationContext(), WidgetProvider.class);

            clickIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds);

            remoteViews.setTextViewText(R.id.txtvalor, "...");
            remoteViews.setViewVisibility(R.id.btreload, View.GONE);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.btreload, pendingIntent);
            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
    }

    @Override
    public void erro(String erro) {

        for (int widgetId : allWidgetIds) {
            remoteViews = new RemoteViews(this.getApplicationContext().getPackageName(), R.layout.widget_cotacao);

            Intent clickIntent = new Intent(this.getApplicationContext(), WidgetProvider.class);

            clickIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds);

            remoteViews.setTextViewText(R.id.txtvalor, "erro");
            remoteViews.setViewVisibility(R.id.btreload, View.VISIBLE);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.btreload, pendingIntent);
            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
    }

    @Override
    public void mostrar(Moeda valor) {
        //Log.d("RETORNO", "-"+valor);

        for (int widgetId : allWidgetIds) {
            remoteViews = new RemoteViews(this.getApplicationContext().getPackageName(), R.layout.widget_cotacao);

            Intent clickIntent = new Intent(this.getApplicationContext(), WidgetProvider.class);

            clickIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds);

            // para valor no brasil
            if(LANG.equals("pt")) {
                remoteViews.setTextViewText(R.id.txtvalor, getString(R.string.prefixo) + " " + valor.getValor_real());
            }else{
                remoteViews.setTextViewText(R.id.txtvalor, getString(R.string.prefixo) + " " + valor.getPrice_usd());
            }

            remoteViews.setTextViewText(R.id.txtbtc,  "BTC " + valor.getPrice_btc());

            remoteViews.setViewVisibility(R.id.btreload, View.VISIBLE);

            Float crescimento24 = Float.parseFloat(valor.getPercent_change_24h());
            if(crescimento24>0){
                remoteViews.setTextColor(R.id.txtbtc, getResources().getColor(R.color.verde));
            }else{
                remoteViews.setTextColor(R.id.txtbtc, getResources().getColor(R.color.vermelho));
            }

            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.btreload, pendingIntent);
            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}