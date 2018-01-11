package com.thiagosalper.cotacaoraiblocks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Button;

import com.google.gson.Gson;
import com.orm.SugarContext;
import com.orm.SugarRecord;
import com.thiagosalper.cotacaoraiblocks.adapter.EnderecoAdapter;
import com.thiagosalper.cotacaoraiblocks.model.Endereco;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EnderecosActivity extends AppCompatActivity {

    private Gson gson;
    private EnderecoAdapter adaptador;
    @BindView(R.id.recyclerView)
    RecyclerView view_reciclada;

    @BindView(R.id.btrevisar) Button btrevisar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enderecos);

        ButterKnife.bind(this);
        SugarContext.init(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Meus endereços");


        List<Endereco> lista = SugarRecord.listAll(Endereco.class);

        gson     = new Gson();
        gson.toJson(lista);

        adaptador = new EnderecoAdapter(view_reciclada.getContext(), lista);
        view_reciclada.setAdapter(adaptador);
        view_reciclada.setHasFixedSize(true);
        view_reciclada.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

    }

    @Override
    public void onRestart(){
        super.onRestart();
    }

    @OnClick(R.id.btrevisar) void addEndereco(){
        startActivity(new Intent(this, EnderecosAddActivity.class));
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
