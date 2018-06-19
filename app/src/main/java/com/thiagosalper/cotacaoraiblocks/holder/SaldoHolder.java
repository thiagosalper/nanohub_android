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
import com.thiagosalper.cotacaoraiblocks.model.Saldo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thiagopereira on 09/01/2018.
 */

public class SaldoHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.txtdescricao) public TextView txtdescricao;
    @BindView(R.id.txtdolar) public TextView txtdolar;
    @BindView(R.id.txtqtde) public TextView txtqtde;
    @BindView(R.id.txtbtc) public TextView txtbtc;
    @BindView(R.id.btremove) public ImageButton btremove;

    public Saldo objeto;

    public SaldoHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.item_saldo, parent, false));
        ButterKnife.bind(this, itemView);
        SugarContext.init(itemView.getContext());

        final Context cc = parent.getContext();

        btremove.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final Context c = v.getContext();

                AlertDialog.Builder alert = new AlertDialog.Builder(c);
                alert.setTitle(R.string.remover);
                alert.setMessage(""+v.getResources().getString(R.string.deseja_remover));
                alert.setPositiveButton(""+v.getResources().getString(R.string.confirmar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Saldo item = SugarRecord.findById(Saldo.class, objeto.getId());
                        SugarRecord.delete(item);

                        Toast.makeText(c, ""+c.getResources().getString(R.string.sucesso), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                        Intent intent = ((Activity) cc).getIntent();
                        ((Activity) cc).finish();
                    }
                });

                alert.setNegativeButton(""+v.getResources().getString(R.string.cancelar), new DialogInterface.OnClickListener() {
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