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
import com.thiagosalper.cotacaoraiblocks.model.Alerta;
import com.thiagosalper.cotacaoraiblocks.model.Endereco;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thiagopereira on 18/01/2018.
 */

public class AlertaHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.txtnome) public TextView txtnome;
    @BindView(R.id.txtvariacao) public TextView txtvariacao;
    @BindView(R.id.btremove) public ImageButton btremove;

    public Alerta alerta;

    public AlertaHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.item_alerta, parent, false));

        ButterKnife.bind(this, itemView);
        SugarContext.init(itemView.getContext());

        final Context cc = parent.getContext();


        btremove.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final Context c = v.getContext();

                AlertDialog.Builder alert = new AlertDialog.Builder(c);
                alert.setTitle(R.string.remover);
                alert.setMessage(R.string.remover_alerta);
                alert.setPositiveButton(R.string.confirmar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Alerta item = SugarRecord.findById(Alerta.class, alerta.getId());
                        SugarRecord.delete(item);

                        Toast.makeText(c, R.string.alerta_removido, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                        Intent intent = ((Activity) cc).getIntent();
                        ((Activity) cc).finish();
                    }
                });

                alert.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alert.show();
            }
        });

    }
}