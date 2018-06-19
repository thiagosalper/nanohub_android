package com.thiagosalper.cotacaoraiblocks.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thiagosalper.cotacaoraiblocks.MainActivity;
import com.thiagosalper.cotacaoraiblocks.holder.EnderecoHolder;
import com.thiagosalper.cotacaoraiblocks.holder.SaldoHolder;
import com.thiagosalper.cotacaoraiblocks.model.Endereco;
import com.thiagosalper.cotacaoraiblocks.model.Moeda;
import com.thiagosalper.cotacaoraiblocks.model.Saldo;
import com.thiagosalper.cotacaoraiblocks.views.enderecos.EnderecosDetalhesActivity;
import com.thiagosalper.cotacaoraiblocks.views.saldos.SaldosActivity;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by thiagopereira on 27/01/2018.
 */

public class SaldoAdapter extends RecyclerView.Adapter<SaldoHolder>{
    private List<Saldo> lista;
    private Context context;
    private String moeda;
    private String moeda_btc;

    public SaldoAdapter(Context context, List<Saldo> lista, String moeda, String moeda_btc) {
        this.lista      = lista;
        this.context    = context;
        this.moeda = moeda;
        this.moeda_btc = moeda_btc;
    }

    @Override
    public SaldoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SaldoHolder(LayoutInflater.from(parent.getContext()), parent);
    }
    @Override
    public void onBindViewHolder(SaldoHolder holder, int i) {
        final Saldo objeto = lista.get(i);

        holder.txtdescricao.setText(objeto.getDescricao());
        //holder.txtdolar.setText("$" + Float.toString(objeto.getValor() * Float.parseFloat(moeda)));
        holder.txtdolar.setText("$" + formatarFloat(calculaPreco(moeda, objeto.getValor())));
        holder.txtbtc.setText("BTC " + Float.toString(calculaPreco(moeda_btc, objeto.getValor())));
        //holder.txtdolar.setText("$" + moeda);
        holder.txtqtde.setText(objeto.getValor().toString() + " XRB");

        holder.objeto = objeto;
    }

    @Override
    public int getItemCount() {
        return (null != lista ? lista.size() : 0 );
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
