package com.thiagosalper.cotacaoraiblocks.views.saldos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Button;

import com.google.gson.Gson;
import com.orm.SugarContext;
import com.orm.SugarRecord;
import com.thiagosalper.cotacaoraiblocks.R;
import com.thiagosalper.cotacaoraiblocks.adapter.EnderecoAdapter;
import com.thiagosalper.cotacaoraiblocks.adapter.SaldoAdapter;
import com.thiagosalper.cotacaoraiblocks.model.Endereco;
import com.thiagosalper.cotacaoraiblocks.model.Saldo;
import com.thiagosalper.cotacaoraiblocks.views.enderecos.EnderecosAddActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by thiagopereira on 27/01/2018.
 */

public class SaldosActivity extends AppCompatActivity {

    private Gson gson;
    private SaldoAdapter adaptador;
    @BindView(R.id.recyclerView) RecyclerView view_reciclada;

    private String dolar;
    private String dolar_btc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saldo);

        ButterKnife.bind(this);
        SugarContext.init(this);

        dolar = getIntent().getStringExtra("dolar");
        dolar_btc = getIntent().getStringExtra("dolar_btc");

        List<Saldo> lista = SugarRecord.listAll(Saldo.class);

        gson     = new Gson();
        gson.toJson(lista);

        adaptador = new SaldoAdapter(view_reciclada.getContext(), lista, dolar, dolar_btc);
        view_reciclada.setAdapter(adaptador);
        view_reciclada.setHasFixedSize(true);
        view_reciclada.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

    }

    @Override
    public void onRestart(){
        super.onRestart();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id==android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}
