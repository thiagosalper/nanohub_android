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
import com.thiagosalper.cotacaoraiblocks.model.Exchange;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thiagopereira on 18/01/2018.
 */

public class ExchangeHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.txtnome) public TextView txtnome;

    public Exchange objeto;

    public ExchangeHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.item_exchange, parent, false));

        ButterKnife.bind(this, itemView);
        //SugarContext.init(itemView.getContext());

    }
}