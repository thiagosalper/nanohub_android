package com.thiagosalper.cotacaoraiblocks.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thiagosalper.cotacaoraiblocks.holder.AlertaHolder;
import com.thiagosalper.cotacaoraiblocks.model.Alerta;
import com.thiagosalper.cotacaoraiblocks.views.alertas.AlertasAddActivity;
import com.thiagosalper.cotacaoraiblocks.views.enderecos.EnderecosDetalhesActivity;
import com.thiagosalper.cotacaoraiblocks.holder.EnderecoHolder;
import com.thiagosalper.cotacaoraiblocks.model.Endereco;

import java.util.List;

/**
 * Created by thiagopereira on 18/01/2018.
 */

public class AlertasAdapter extends RecyclerView.Adapter<AlertaHolder>{
    private List<Alerta> lista;
    private Context context;

    public AlertasAdapter(Context context, List<Alerta> lista) {
        this.lista      = lista;
        this.context    = context;
    }

    @Override
    public AlertaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AlertaHolder(LayoutInflater.from(parent.getContext()), parent);
    }
    @Override
    public void onBindViewHolder(AlertaHolder holder, int i) {
        final Alerta objeto = lista.get(i);

        holder.txtnome.setText(objeto.getNome());
        holder.txtvariacao.setText(objeto.getPorcentagem()+"%");
        holder.alerta = objeto;
//
//        holder.itemView.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                Intent intent = new Intent(v.getContext(), AlertasAddActivity.class);
//                //intent.putExtra("endereco", objeto.getEndereco());
//                v.getContext().startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return (null != lista ? lista.size() : 0 );
    }
}