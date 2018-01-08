package com.thiagosalper.cotacaoraiblocks;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.thiagosalper.cotacaoraiblocks.data.ConsultaApi;
import com.thiagosalper.cotacaoraiblocks.data.ConsultaData;
import com.thiagosalper.cotacaoraiblocks.model.Moeda;
import com.thiagosalper.cotacaoraiblocks.presenter.ConsultaPresenter;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements ConsultaPresenter {

    @BindView(R.id.txtcotacao_venda) TextView txtcotacao_venda;
    @BindView(R.id.txtdolar2) TextView txtdolar;
    @BindView(R.id.txtcrescimento) TextView txtcrescimento;
    @BindView(R.id.txtprecobtc) TextView txtprecobtc;
    @BindView(R.id.btreload) Button btreload;
    private ConsultaPresenter contexto = this;
    private ConsultaData consulta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    
        consulta = new ConsultaApi();
        consulta.busca(contexto);


        final Handler handler = new Handler(Looper.getMainLooper());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                consulta.busca(contexto);
                handler.postDelayed(this, 30000);
            }
        };
        handler.postDelayed(runnable, 30000);

    }

    @Override
    public void carregando() {
        //Toast.makeText(this, "Buscando ...", Toast.LENGTH_SHORT).show();
        //txtcotacao_venda.setText("...");
        //txtcotacao_venda.setText("...");
        btreload.setVisibility(View.GONE);
    }

    @Override
    public void erro(String erro) {
        Toast.makeText(this, "Erro: "+erro, Toast.LENGTH_SHORT).show();
        //txtcotacao_venda.setText("...");
        btreload.setVisibility(View.VISIBLE);
    }

    @Override
    public void mostrar(Moeda moeda) {
        txtcotacao_venda.setText("R$ "+moeda.getValor_real());
        txtprecobtc.setText(moeda.getPrice_btc());
        txtdolar.setText("$ "+moeda.getPrice_usd());
        txtcrescimento.setText(moeda.getPercent_change_1h()+"%");
        Float crescimento = Float.parseFloat(moeda.getPercent_change_1h());
        if(crescimento>0){
            txtcrescimento.setTextColor(getResources().getColor(R.color.verde));
        }else{
            txtcrescimento.setTextColor(getResources().getColor(R.color.vermelho));
        }
        btreload.setVisibility(View.VISIBLE);
    }

//    @OnClick(R.id.btconfig) void clicaConfig(){
//        Toast.makeText(this, "Em breve!", Toast.LENGTH_SHORT).show();
//        //startActivity(new Intent(this, ConfigActivity.class));
//    }
//
//    @OnClick(R.id.btshare) void clicaShare(){
//        Intent intent2 = new Intent(); intent2.setAction(Intent.ACTION_SEND);
//        intent2.setType("text/plain");
//        intent2.putExtra(Intent.EXTRA_TEXT, "Cotação RaiBlocks em reais: https://goo.gl/xJ7HeU " );
//        startActivity(Intent.createChooser(intent2, "Compartilhar com"));
//    }

    @OnClick(R.id.btreload) void reloadData(){
        consulta.busca(contexto);
    }
}
