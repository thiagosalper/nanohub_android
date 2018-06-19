package com.thiagosalper.cotacaoraiblocks.views.alertas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.orm.SugarContext;
import com.orm.SugarRecord;
import com.thiagosalper.cotacaoraiblocks.R;
import com.thiagosalper.cotacaoraiblocks.model.Alerta;
import com.thiagosalper.cotacaoraiblocks.model.Endereco;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlertasAddActivity extends AppCompatActivity {

    @BindView(R.id.txtnome) EditText txtnome;
    @BindView(R.id.txtvariacao) EditText txtvariacao;
    @BindView(R.id.ativo) Switch ativo;
    @BindView(R.id.btconcluir) Button btconcluir;
    private Boolean disponivel = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alertas_add);

        ButterKnife.bind(this);
        SugarContext.init(this);

        ativo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    disponivel = true;
                } else {
                    disponivel = false;
                }
            }
        });
    }

    @OnClick(R.id.btconcluir) void clicaConcluir(){
        Alerta objeto = new Alerta();
        objeto.setNome(txtnome.getText().toString());
        objeto.setPorcentagem(txtvariacao.getText().toString());
        objeto.setAtivo(disponivel);
        objeto.setValor("");

        Long incluir = SugarRecord.save(objeto);

        if(incluir>0) {
            finish();
        }else{
            Toast.makeText(this, "Ops. Erro ao incluir.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}
