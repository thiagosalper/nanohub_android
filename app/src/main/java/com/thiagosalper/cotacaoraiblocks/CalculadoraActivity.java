package com.thiagosalper.cotacaoraiblocks;

import android.app.Dialog;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.orm.SugarContext;
import com.orm.SugarRecord;
import com.thiagosalper.cotacaoraiblocks.data.ConsultaApi;
import com.thiagosalper.cotacaoraiblocks.data.ConsultaData;
import com.thiagosalper.cotacaoraiblocks.model.Moeda;
import com.thiagosalper.cotacaoraiblocks.model.Saldo;
import com.thiagosalper.cotacaoraiblocks.presenter.ConsultaPresenter;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalculadoraActivity extends AppCompatActivity implements ConsultaPresenter {

    @BindView(R.id.txtresultado) TextView txtresultado;
    @BindView(R.id.txtquantidade) EditText txtquantidade;
    @BindView(R.id.txtvalor) EditText txtvalor;
    @BindView(R.id.btconcluir) Button btconcluir;

    private ConsultaPresenter contexto = this;
    private ConsultaData consulta;

    private Moeda preco_atual = new Moeda();

    private String LANG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        ButterKnife.bind(this);
        SugarContext.init(this);

        LANG = Resources.getSystem().getConfiguration().locale.getLanguage();

        consulta = new ConsultaApi();
        consulta.busca(contexto);

    }

    @OnClick(R.id.btconcluir) void calculadora(){
        // se for brasil
        String qtde = txtvalor.getText().toString();

        if(!TextUtils.isEmpty(qtde)){

            String valorcalculado = formatarFloat(calculaPreco(txtvalor.getText().toString(), Float.parseFloat(txtquantidade.getText().toString())));

            txtresultado.setText(getString(R.string.prefixo) + " " + valorcalculado);
        }
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

    @Override
    public void carregando() {
        txtvalor.setHint(getString(R.string.aguarde));
        btconcluir.setVisibility(View.GONE);
    }

    @Override
    public void erro(String erro) {
        txtvalor.setHint(getString(R.string.erro));
        Toast.makeText(this, "Erro: "+erro, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mostrar(Moeda moeda) {
        btconcluir.setVisibility(View.VISIBLE);
        if(LANG.equals("pt")) {
            txtvalor.setText(moeda.getValor_real());
        }else{
            txtvalor.setText(moeda.getPrice_usd());
        }
        txtvalor.setHint("0.00");
        preco_atual = moeda;
    }


    // acao de salvar o saldo para acompanhar o valor
    @OnClick(R.id.btsalvar) void salvarSaldo(){
        if(txtquantidade.getText()!=null){
            salvar();
        }else{
            Toast.makeText(this, ""+R.string.preencha_qtde, Toast.LENGTH_SHORT).show();
        }
    }

    public void salvar(){
        final Dialog dialog_1;
        dialog_1 = new Dialog(this);
        dialog_1.setContentView(R.layout.dialog_salvar_saldo);

        final EditText txtdescricao = (EditText) dialog_1.findViewById(R.id.txtdescricao);

        Button dialog_btcalcular = (Button) dialog_1.findViewById(R.id.btsalvar);
        dialog_btcalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Saldo meusaldo = new Saldo();
                meusaldo.setValor(Float.parseFloat(txtquantidade.getText().toString()));
                meusaldo.setDescricao(txtdescricao.getText().toString());

                SugarRecord.save(meusaldo);

                dialog_1.dismiss();
                Toast.makeText(CalculadoraActivity.this, "Ok :)", Toast.LENGTH_SHORT).show();
            }
        });

//        Button dialog_btfechar = (Button) dialog_1.findViewById(R.id.btfechar);
//        dialog_btfechar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog_1.dismiss();
//            }
//        });

        dialog_1.show();
    }



    public Float calculaPreco(String valor, Float qtd){
        String valor1 = valor.replaceAll(",", ".");
        return Float.parseFloat(valor1) * qtd;
    }

    public String formatarFloat(float numero){
        String retorno = "";
        DecimalFormat formatter = new DecimalFormat("#.00");
        try{
            retorno = formatter.format(numero);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return retorno;
    }
}
