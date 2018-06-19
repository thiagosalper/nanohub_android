package com.thiagosalper.cotacaoraiblocks.views.exchanges;

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
import com.thiagosalper.cotacaoraiblocks.model.Exchange;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExchangeActivity extends AppCompatActivity implements ValueEventListener {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference data_reference;

    public List<Exchange> lista = new ArrayList<>();
    private Gson gson;

    private ExchangeAdapter adaptador;
    @BindView(R.id.recyclerView) RecyclerView view_reciclada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);

        ButterKnife.bind(this);
        SugarContext.init(this);

        data_reference = database.getReference().child("exchanges");
        data_reference.addValueEventListener(this);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {

        lista.clear();

        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            Exchange objeto = snapshot.getValue(Exchange.class);

          //  try {
            //    if (objeto.getStatus()>0) {
                    lista.add(objeto);
           //     }
           /// }catch (Exception e){
           //     e.printStackTrace();
           // }
        }

        adaptador = new ExchangeAdapter(view_reciclada.getContext(), lista);
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
