package com.thiagosalper.cotacaoraiblocks.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thiagosalper.cotacaoraiblocks.holder.ExchangeHolder;
import com.thiagosalper.cotacaoraiblocks.holder.LojaHolder;
import com.thiagosalper.cotacaoraiblocks.model.Exchange;
import com.thiagosalper.cotacaoraiblocks.model.Loja;
import com.thiagosalper.cotacaoraiblocks.views.enderecos.EnderecosDetalhesActivity;
import com.thiagosalper.cotacaoraiblocks.holder.EnderecoHolder;
import com.thiagosalper.cotacaoraiblocks.model.Endereco;

import java.util.List;


public class LojaAdapter extends RecyclerView.Adapter<LojaHolder>{
    private List<Loja> lista;
    private Context context;

    public LojaAdapter(Context context, List<Loja> lista) {
        this.lista      = lista;
        this.context    = context;
    }

    @Override
    public LojaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LojaHolder(LayoutInflater.from(parent.getContext()), parent);
    }
    @Override
    public void onBindViewHolder(LojaHolder holder, int i) {
        final Loja objeto = lista.get(i);

        holder.txtnome.setText(objeto.getNome());
        holder.txtdescricao.setText(objeto.getDescricao());

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