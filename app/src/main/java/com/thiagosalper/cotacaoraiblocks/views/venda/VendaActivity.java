package com.thiagosalper.cotacaoraiblocks.views.venda;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.orm.SugarContext;
import com.thiagosalper.cotacaoraiblocks.R;
import com.thiagosalper.cotacaoraiblocks.data.ConsultaApi;
import com.thiagosalper.cotacaoraiblocks.data.ConsultaData;
import com.thiagosalper.cotacaoraiblocks.model.Moeda;
import com.thiagosalper.cotacaoraiblocks.presenter.ConsultaPresenter;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VendaActivity extends AppCompatActivity implements ConsultaPresenter {

    @BindView(R.id.txtresultado) TextView txtresultado;
    @BindView(R.id.txtvalor) EditText txtvalor;
    @BindView(R.id.btconcluir) Button btconcluir;
    @BindView(R.id.moeda1) RadioButton moeda1;
    @BindView(R.id.moeda2) RadioButton moeda2;

    private ConsultaPresenter contexto = this;
    private ConsultaData consulta;

    private Moeda preco_atual = new Moeda();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venda);

        ButterKnife.bind(this);

        consulta = new ConsultaApi();
        consulta.busca(contexto);
    }

    @OnClick(R.id.btconcluir) void calculadora(){
        String valorcalculado = calculaPrecoNano(txtvalor.getText().toString());
        txtresultado.setText(valorcalculado + " NANO");
    }

    @Override
    public void carregando() {
        txtvalor.setHint(getString(R.string.aguarde));
        btconcluir.setVisibility(View.GONE);
    }

    @Override
    public void erro(String erro) {
        Toast.makeText(this, "Erro: "+erro, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mostrar(Moeda moeda) {
        btconcluir.setVisibility(View.VISIBLE);
        txtvalor.setHint("0.00");
        preco_atual = moeda;
    }


    public String calculaPrecoNano(String valor){
        String valor1 = valor.replaceAll(",", ".");

        Float valor_produto = Float.parseFloat(valor1);

        Float valor_nano = Float.parseFloat("0");

        if(moeda1.isChecked()) {
            // se for real
            valor_nano = valor_produto / Float.parseFloat(preco_atual.getValor_real().replaceAll(",", "."));
        }else if(moeda2.isChecked()) {
            // se for dolar
            valor_nano = valor_produto / Float.parseFloat(preco_atual.getPrice_usd().replaceAll(",", "."));
        }

        return formatarFloat(valor_nano);
    }

    public String formatarFloat(float numero){
        String retorno = "";
        DecimalFormat formatter = new DecimalFormat("#.0000");
        try{
            retorno = formatter.format(numero);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return retorno;
    }

}
