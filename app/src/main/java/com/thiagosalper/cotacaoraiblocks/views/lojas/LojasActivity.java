package com.thiagosalper.cotacaoraiblocks.views.lojas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.orm.SugarContext;
import com.thiagosalper.cotacaoraiblocks.R;
import com.thiagosalper.cotacaoraiblocks.adapter.ExchangeAdapter;
import com.thiagosalper.cotacaoraiblocks.adapter.LojaAdapter;
import com.thiagosalper.cotacaoraiblocks.model.Exchange;
import com.thiagosalper.cotacaoraiblocks.model.Loja;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LojasActivity extends AppCompatActivity implements ValueEventListener {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference data_reference;

    public List<Loja> lista = new ArrayList<>();
    private Gson gson;

    private LojaAdapter adaptador;
    @BindView(R.id.recyclerView) RecyclerView view_reciclada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loja);

        ButterKnife.bind(this);
        SugarContext.init(this);

        data_reference = database.getReference().child("lojas");
        data_reference.addValueEventListener(this);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {

        lista.clear();

        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            Loja objeto = snapshot.getValue(Loja.class);
            //if(objeto.getStatus()>0) {
                lista.add(objeto);
            //}
        }

        adaptador = new LojaAdapter(view_reciclada.getContext(), lista);
        view_reciclada.setAdapter(adaptador);
        view_reciclada.setHasFixedSize(true);
        view_reciclada.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        Toast.makeText(this, "Erro :( Try again ...", Toast.LENGTH_SHORT).show();
        Log.w("RETORNO", "loadPost:onCancelled", databaseError.toException());
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
