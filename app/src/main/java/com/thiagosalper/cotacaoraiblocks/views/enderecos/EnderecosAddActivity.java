package com.thiagosalper.cotacaoraiblocks.views.enderecos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.orm.SugarContext;
import com.orm.SugarRecord;
import com.thiagosalper.cotacaoraiblocks.R;
import com.thiagosalper.cotacaoraiblocks.model.Endereco;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EnderecosAddActivity extends AppCompatActivity {
    
    @BindView(R.id.txtdescricao) EditText txtdescricao;
    @BindView(R.id.txtendereco) EditText txtendereco;
    @BindView(R.id.btconcluir) Button btconcluir;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enderecos_add);

        ButterKnife.bind(this);
        SugarContext.init(this);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle("Adicionar Endereço");
        
        
    }

    @OnClick(R.id.btqrcode) void clicaQrCode(){
        Toast.makeText(this, "soon :)", Toast.LENGTH_SHORT).show();
    }
    
    @OnClick(R.id.btconcluir) void clicaConcluir(){
        Endereco objeto = new Endereco();
        objeto.setDescricao(txtdescricao.getText().toString());
        objeto.setEndereco(txtendereco.getText().toString());

        Long incluir = SugarRecord.save(objeto);
        
        if(incluir>0) {
            finish();
            //startActivity(new Intent(this, EnderecosActivity.class));
        }else{
            Toast.makeText(this, "Ops. Erro ao incluir.", Toast.LENGTH_SHORT).show();
        }
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
