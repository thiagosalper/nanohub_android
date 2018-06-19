package com.thiagosalper.cotacaoraiblocks;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.thiagosalper.cotacaoraiblocks.data.ConsultaApi;
import com.thiagosalper.cotacaoraiblocks.data.ConsultaData;
import com.thiagosalper.cotacaoraiblocks.model.Moeda;
import com.thiagosalper.cotacaoraiblocks.presenter.ConsultaPresenter;
import com.thiagosalper.cotacaoraiblocks.views.ajude.AjudeActivity;
import com.thiagosalper.cotacaoraiblocks.views.alertas.AlertasActivity;
import com.thiagosalper.cotacaoraiblocks.views.enderecos.EnderecosActivity;
import com.thiagosalper.cotacaoraiblocks.views.exchanges.ExchangeActivity;
import com.thiagosalper.cotacaoraiblocks.views.lojas.LojasActivity;
import com.thiagosalper.cotacaoraiblocks.views.saldos.SaldosActivity;
import com.thiagosalper.cotacaoraiblocks.views.sobre.SobreActivity;
import com.thiagosalper.cotacaoraiblocks.views.venda.VendaActivity;

import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements ConsultaPresenter, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.txtcotacao_venda) TextView txtcotacao_venda;
    @BindView(R.id.txtdolar2) TextView txtdolar;
    @BindView(R.id.txtcrescimento) TextView txtcrescimento;
    @BindView(R.id.txtporcento24) TextView txtporcento24;
    @BindView(R.id.txtprecobtc) TextView txtprecobtc;
    @BindView(R.id.btreload) Button btreload;
    @BindView(R.id.cubiclinechart) ValueLineChart mCubicValueLineChart;
    private ConsultaPresenter contexto = this;
    private ConsultaData consulta;
    private Context contextoapp = this;

    private Moeda preco_atual = new Moeda();

    private String LANG;

    private ValueLineSeries series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(" ");

        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        consulta = new ConsultaApi();
        consulta.busca(contexto);

        LANG = Resources.getSystem().getConfiguration().locale.getLanguage();


        final Handler handler = new Handler(Looper.getMainLooper());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                consulta.busca(contexto);
                handler.postDelayed(this, 300000);
            }
        };
        handler.postDelayed(runnable, 300000);




    }

    @Override
    public void carregando() {
        btreload.setVisibility(View.GONE);
    }

    @Override
    public void erro(String erro) {
        Toast.makeText(this, "Erro: "+erro, Toast.LENGTH_SHORT).show();
        btreload.setVisibility(View.VISIBLE);
    }

    @Override
    public void mostrar(Moeda moeda) {
        preco_atual = moeda;
        if(LANG.equals("pt")) {
            txtcotacao_venda.setText(getString(R.string.prefixo) + " " + moeda.getValor_real());
        }else{
            txtcotacao_venda.setText(getString(R.string.prefixo) + " " + moeda.getPrice_usd());
        }
        txtprecobtc.setText(moeda.getPrice_btc());
        txtdolar.setText(getString(R.string.prefixo_dolar)+" "+moeda.getPrice_usd());
        txtcrescimento.setText(moeda.getPercent_change_1h()+"%");
        txtporcento24.setText(moeda.getPercent_change_24h()+"%");

        //Float preco24h = Float.parseFloat(moeda.getPrice_btc()) ;


        // graficos
        series = new ValueLineSeries();
        series.setColor(R.color.verde_claro);
        //series.addPoint(new ValueLinePoint("", 2.4f));
        series.addPoint(new ValueLinePoint("", Float.parseFloat(moeda.getPercent_change_24h())));
        series.addPoint(new ValueLinePoint("24h", Float.parseFloat(moeda.getPercent_change_24h())));
        series.addPoint(new ValueLinePoint("1h", Float.parseFloat(moeda.getPercent_change_1h())));
        series.addPoint(new ValueLinePoint("NOW", Float.parseFloat(moeda.getPercent_change_1h())));
        series.addPoint(new ValueLinePoint("", Float.parseFloat(moeda.getPercent_change_1h())));

        mCubicValueLineChart.clearChart();
        mCubicValueLineChart.addSeries(series);
        mCubicValueLineChart.startAnimation();

        btreload.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btreload) void reloadData(){
        consulta.busca(contexto);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_calculadora) {
            startActivity(new Intent(this, CalculadoraActivity.class));
        } else if (id == R.id.nav_venda) {
            startActivity(new Intent(this, VendaActivity.class));
        } else if (id == R.id.nav_carteiras) {
            startActivity(new Intent(this, EnderecosActivity.class));
        } else if (id == R.id.nav_alertas) {
            startActivity(new Intent(this, AlertasActivity.class));
        } else if (id == R.id.nav_saldos) {
            Intent saldo_intent = new Intent(this, SaldosActivity.class);
            saldo_intent.putExtra("dolar", preco_atual.getPrice_usd());
            saldo_intent.putExtra("dolar_btc", preco_atual.getPrice_btc());
            startActivity(saldo_intent);
        } else if (id == R.id.nav_exchanges) {
            startActivity(new Intent(this, ExchangeActivity.class));
        } else if (id == R.id.nav_lojas) {
            startActivity(new Intent(this, LojasActivity.class));
        } else if (id == R.id.nav_sobre) {
            startActivity(new Intent(this, SobreActivity.class));
        } else if (id == R.id.nav_share) {
            compartilhar();
        } else if (id == R.id.nav_ajude) {
            startActivity(new Intent(this, AjudeActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void compartilhar(){
        Intent intent2 = new Intent();
        intent2.setAction(Intent.ACTION_SEND);
        intent2.setType("text/plain");
        intent2.putExtra(Intent.EXTRA_TEXT, getString(R.string.compartilhar_string)+": https://goo.gl/K9r9gW " );
        startActivity(Intent.createChooser(intent2, getString(R.string.compartilhar_com)));
    }
}
