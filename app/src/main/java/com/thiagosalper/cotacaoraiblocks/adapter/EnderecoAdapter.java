package com.thiagosalper.cotacaoraiblocks.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thiagosalper.cotacaoraiblocks.views.enderecos.EnderecosDetalhesActivity;
import com.thiagosalper.cotacaoraiblocks.holder.EnderecoHolder;
import com.thiagosalper.cotacaoraiblocks.model.Endereco;

import java.util.List;

/**
 * Created by thiagopereira on 09/01/2018.
 */

public class EnderecoAdapter extends RecyclerView.Adapter<EnderecoHolder>{
    private List<Endereco> lista;
    private Context context;

    public EnderecoAdapter(Context context, List<Endereco> lista) {
        this.lista      = lista;
        this.context    = context;
    }

    @Override
    public EnderecoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EnderecoHolder(LayoutInflater.from(parent.getContext()), parent);
    }
    @Override
    public void onBindViewHolder(EnderecoHolder holder, int i) {
        final Endereco objeto = lista.get(i);

        holder.txtnome.setText(objeto.getDescricao());
        //holder.txtendereco.setText(objeto.getEndereco());
        holder.endereco = objeto;

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), EnderecosDetalhesActivity.class);
                intent.putExtra("endereco", objeto.getEndereco());
                intent.putExtra("nome", objeto.getDescricao());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != lista ? lista.size() : 0 );
    }
}
