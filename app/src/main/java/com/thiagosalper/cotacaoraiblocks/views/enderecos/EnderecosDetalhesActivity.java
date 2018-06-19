package com.thiagosalper.cotacaoraiblocks.views.enderecos;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.WriterException;
import com.orm.SugarContext;
import com.thiagosalper.cotacaoraiblocks.R;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EnderecosDetalhesActivity extends AppCompatActivity {

    @BindView(R.id.imgqrcode) ImageView imgqrcode;
    @BindView(R.id.txtnome) TextView txtnome;
    @BindView(R.id.txtendereco) TextView txtendereco;
    public String endereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enderecos_detalhes);

        ButterKnife.bind(this);
        SugarContext.init(this);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle("Adicionar Endereço");

        endereco = getIntent().getStringExtra("endereco");
        String nome = getIntent().getStringExtra("nome");

        txtendereco.setText(endereco);
        txtnome.setText(nome);

        QRGEncoder qrgEncoder = new QRGEncoder(endereco, null, QRGContents.Type.TEXT, 200);
        try {
            // Getting QR-Code as Bitmap
            Bitmap bitmap = qrgEncoder.encodeAsBitmap();
            // Setting Bitmap to ImageView
            imgqrcode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            Log.v("QRERRO", e.toString());
        }
    }

    @OnClick(R.id.btconcluir) void compartilhar(){
        Intent intent2 = new Intent(); intent2.setAction(Intent.ACTION_SEND);
        intent2.setType("text/plain");
        intent2.putExtra(Intent.EXTRA_TEXT, ""+endereco);
        startActivity(Intent.createChooser(intent2, getString(R.string.compartilhar_com)));
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
