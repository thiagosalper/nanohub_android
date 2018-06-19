package com.thiagosalper.cotacaoraiblocks.views.alertas;

import android.app.ActivityManager;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.ToggleButton;

import com.google.gson.Gson;
import com.orm.SugarContext;
import com.orm.SugarRecord;
import com.thiagosalper.cotacaoraiblocks.R;
import com.thiagosalper.cotacaoraiblocks.adapter.AlertasAdapter;
import com.thiagosalper.cotacaoraiblocks.adapter.EnderecoAdapter;
import com.thiagosalper.cotacaoraiblocks.model.Alerta;
import com.thiagosalper.cotacaoraiblocks.model.Endereco;
import com.thiagosalper.cotacaoraiblocks.services.AlertaService;
import com.thiagosalper.cotacaoraiblocks.services.WidgetServiceProvider;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlertasActivity extends AppCompatActivity {

    @BindView(R.id.ativa_alerta) Switch ativo;
    @BindView(R.id.recyclerView) RecyclerView view_reciclada;
    //public Switch ativo;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alertas);

        ButterKnife.bind(this);
        SugarContext.init(this);

        //ativo = (Switch) findViewById(R.id.ativa_alerta);

        if(isMyServiceRunning(AlertaService.class)){
            ativo.setChecked(true);
        }else{
            ativo.setChecked(false);
        }

        ativo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    iniciarServico(true);
                } else {
                    iniciarServico(false);
                }
            }
        });


        List<Alerta> lista = SugarRecord.listAll(Alerta.class);

        Gson gson     = new Gson();
        gson.toJson(lista);

        AlertasAdapter adaptador = new AlertasAdapter(view_reciclada.getContext(), lista);
        view_reciclada.setAdapter(adaptador);
        view_reciclada.setHasFixedSize(true);
        view_reciclada.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public void iniciarServico(boolean ativar){
        intent = new Intent(this, AlertaService.class);
        if(ativar) {
            startService(intent);
        }else{
            stopService(intent);
        }
    }

    @OnClick(R.id.btrevisar) void abrirNovo(){
        startActivity(new Intent(this, AlertasAddActivity.class));
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item){
//        int id = item.getItemId();
//        if(id==android.R.id.home){
//            onBackPressed();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}
