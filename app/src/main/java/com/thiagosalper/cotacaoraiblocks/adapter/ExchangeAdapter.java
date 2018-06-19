package com.thiagosalper.cotacaoraiblocks.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thiagosalper.cotacaoraiblocks.holder.ExchangeHolder;
import com.thiagosalper.cotacaoraiblocks.model.Exchange;
import com.thiagosalper.cotacaoraiblocks.views.enderecos.EnderecosDetalhesActivity;
import com.thiagosalper.cotacaoraiblocks.holder.EnderecoHolder;
import com.thiagosalper.cotacaoraiblocks.model.Endereco;

import java.util.List;

/**
 * Created by thiagopereira on 18/01/2018.
 */

public class ExchangeAdapter extends RecyclerView.Adapter<ExchangeHolder>{
    private List<Exchange> lista;
    private Context context;

    public ExchangeAdapter(Context context, List<Exchange> lista) {
        this.lista      = lista;
        this.context    = context;
    }

    @Override
    public ExchangeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ExchangeHolder(LayoutInflater.from(parent.getContext()), parent);
    }
    @Override
    public void onBindViewHolder(ExchangeHolder holder, int i) {
        final Exchange objeto = lista.get(i);

        holder.txtnome.setText(objeto.getNome());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(objeto.getUrl()));
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != lista ? lista.size() : 0 );
    }
}