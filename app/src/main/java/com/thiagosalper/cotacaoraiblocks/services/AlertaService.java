package com.thiagosalper.cotacaoraiblocks.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import com.google.gson.Gson;
import com.orm.SugarContext;
import com.orm.SugarRecord;
import com.thiagosalper.cotacaoraiblocks.MainActivity;
import com.thiagosalper.cotacaoraiblocks.R;
import com.thiagosalper.cotacaoraiblocks.WidgetProvider;
import com.thiagosalper.cotacaoraiblocks.data.ConsultaApi;
import com.thiagosalper.cotacaoraiblocks.data.ConsultaData;
import com.thiagosalper.cotacaoraiblocks.model.Alerta;
import com.thiagosalper.cotacaoraiblocks.model.Endereco;
import com.thiagosalper.cotacaoraiblocks.model.Moeda;
import com.thiagosalper.cotacaoraiblocks.presenter.ConsultaPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thiagopereira on 18/01/2018.
 */

public class AlertaService extends Service implements ConsultaPresenter {
    private ConsultaData consulta;
    public ConsultaPresenter contexto = this;
    public List<Alerta> lista_alerta = new ArrayList<>();
    public Moeda retorno = new Moeda();
    public Handler handler;
    public Runnable runnable;

    @Override
    public void onStart(Intent intent, int startId) {

        SugarContext.init(this);

        //Log.d("ALERTA_SERVICE", "iniciou");
        consulta = new ConsultaApi();
        consulta.busca(contexto);

        handler = new Handler(Looper.getMainLooper());
        runnable = new Runnable() {
            @Override
            public void run() {
                //Log.d("ALERTA_SERVICE", "consultou");
                consulta.busca(contexto);
                handler.postDelayed(this, 1800000);
            }
        };
        handler.postDelayed(runnable, 1800000);

        //stopSelf();
        //super.onStart(intent, startId);
    }

    @Override
    public void onDestroy(){
        //handler.removeCallbacksAndMessages(runnable);
        //thread.stop();
        super.onDestroy();
    }

    @Override
    public void carregando() {

    }

    @Override
    public void erro(String erro) {

    }

    @Override
    public void mostrar(Moeda valor) {
        List<Alerta> lista = SugarRecord.listAll(Alerta.class);
//        Gson gson     = new Gson();
//        gson.toJson(lista);
//
        for (int i = 0; i < lista.size(); i++) {
            Alerta alerta = lista.get(i);

            if (alerta.isAtivo()) {
                Float numero1 = Float.parseFloat(valor.getPercent_change_1h());
                Float numero2 = Float.parseFloat(alerta.getPorcentagem());

                if(numero2 >= 0) {
                    if (numero1 >= numero2) {
                        notificaUsuario(valor.getPercent_change_1h());
                    }
                }else{
                    if (numero1 <= numero2) {
                        notificaUsuario(valor.getPercent_change_1h());
                    }
                }
            }
        }

    }

    public void notificaUsuario(String variacao) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_alerta)
                .setContentTitle(getString(R.string.alerta_xrb))
                .setSound(defaultSoundUri)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setContentText(getString(R.string.alerta_texto) + " " + variacao + "%");

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(001, mBuilder.build());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}