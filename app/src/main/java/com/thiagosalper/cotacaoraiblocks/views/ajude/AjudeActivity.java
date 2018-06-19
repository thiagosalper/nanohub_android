package com.thiagosalper.cotacaoraiblocks.views.ajude;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.thiagosalper.cotacaoraiblocks.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AjudeActivity extends AppCompatActivity {
    @BindView(R.id.btconcluir) Button btconcluir;

    private String carteiadev = "xrb_378ka6hh4b7ktg9m8z8tioq4tm9p9uqam1mo8asdqpueymhuby1muhi8m4nj";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajude);

        ButterKnife.bind(this);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle("Ajude no crescimento");

    }

    @OnClick(R.id.btconcluir) void colaborar(){
        // compartilhar
        Intent intent2 = new Intent();
        intent2.setAction(Intent.ACTION_SEND);
        intent2.setType("text/plain");
        intent2.putExtra(Intent.EXTRA_TEXT, ""+carteiadev );
        startActivity(Intent.createChooser(intent2, "Copiar para"));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id==android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}
