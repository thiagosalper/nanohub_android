package com.thiagosalper.cotacaoraiblocks.holder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.orm.SugarContext;
import com.orm.SugarRecord;
import com.thiagosalper.cotacaoraiblocks.R;
import com.thiagosalper.cotacaoraiblocks.model.Endereco;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thiagopereira on 09/01/2018.
 */

public class EnderecoHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.txtnome) public TextView txtnome;
    @BindView(R.id.btremove) public ImageButton btremove;
    @BindView(R.id.btshare) public ImageButton btshare;

    public Endereco endereco;

    public EnderecoHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.item_endereco, parent, false));
        ButterKnife.bind(this, itemView);
        SugarContext.init(itemView.getContext());

        final Context cc = parent.getContext();


        btremove.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final Context c = v.getContext();

                AlertDialog.Builder alert = new AlertDialog.Builder(c);
                alert.setTitle("Remover");
                alert.setMessage("Confirma a remoção deste endereço?");
                alert.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Endereco item = SugarRecord.findById(Endereco.class, endereco.getId());
                        SugarRecord.delete(item);

                        Toast.makeText(c, "Endereço removido!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                        Intent intent = ((Activity) cc).getIntent();
                        ((Activity) cc).finish();
                        //((Activity) cc).startActivity(intent);
                    }
                });

                alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alert.show();
            }
        });

        btshare.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Context c = v.getContext();
                // compartilhar
                Intent intent2 = new Intent(); intent2.setAction(Intent.ACTION_SEND);
                intent2.setType("text/plain");
                intent2.putExtra(Intent.EXTRA_TEXT, ""+endereco.getEndereco() );
                c.startActivity(Intent.createChooser(intent2, "Envie para"));
            }
        });
    }
}