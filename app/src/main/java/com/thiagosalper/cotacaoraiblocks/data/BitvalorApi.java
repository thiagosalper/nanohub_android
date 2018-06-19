package com.thiagosalper.cotacaoraiblocks.data;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.thiagosalper.cotacaoraiblocks.model.Moeda;
import com.thiagosalper.cotacaoraiblocks.presenter.ConsultaPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

import cz.msebera.android.httpclient.Header;

public class BitvalorApi implements BitvalorData {

    Moeda moeda;
    float retorno1;
    float retorno2;
    ConsultaPresenter minhaview;

    @Override
    public void busca(final ConsultaPresenter view) {
        minhaview = view;

        minhaview.carregando();

        //buscaRaiBlocks();
    }

    public String formatarFloat(float numero){
        String retorno = "";
        DecimalFormat formatter = new DecimalFormat("#.00");
        try{
            retorno = formatter.format(numero);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return retorno;
    }
//
//    @Override
//    public void buscaBitcoin() {
//
//        AsyncHttpClient client = new AsyncHttpClient();
//        client.get("https://www.mercadobitcoin.net/api/BTC/ticker/", new JsonHttpResponseHandler() {
//            @Override
//            public void onStart() { }
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject json) {
//                try {
//                    JSONObject json2 = json.getJSONObject("ticker");
//
//                    String valor = json2.getString("last");
//                    retorno2 = Float.parseFloat(valor);
//                    String retorno = formatarFloat(Float.parseFloat(moeda.getPrice_btc()) * retorno2);
//
//                    moeda.setValor_real(retorno);
//
//                    minhaview.mostrar(moeda);
//
//                }catch(JSONException e) {
//                    e.printStackTrace();
//                    minhaview.erro("Erro ao retornar dados");
//                }
//            }
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
//                minhaview.erro("Erro na consulta");
//            }
//            @Override
//            public void onFailure(int status, Header[] h, Throwable t, JSONObject jo){
//                minhaview.erro("Erro na consulta");
//            }
//        });
//    }
//
//    @Override
//    public void buscaRaiBlocks() {
//        AsyncHttpClient client = new AsyncHttpClient();
//        client.get("https://api.coinmarketcap.com/v1/ticker/raiblocks/", new JsonHttpResponseHandler() {
//            @Override
//            public void onStart() { }
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
//                try {
//                    JSONObject json2 = json.getJSONObject(0);
//
//                    moeda = new Moeda();
//
//                    //String valor = json2.getString("price_btc");
//                    moeda.setRank(json2.getString("rank"));
//                    moeda.setPrice_usd(formatarFloat(Float.parseFloat(json2.getString("price_usd"))));
//                    moeda.setPrice_btc(json2.getString("price_btc"));
//                    moeda.setPercent_change_1h(json2.getString("percent_change_1h"));
//                    moeda.setPercent_change_24h(json2.getString("percent_change_24h"));
//                    moeda.setLast_update(json2.getString("last_updated"));
//
//                    buscaBitcoin();
//
//                }catch(JSONException e) {
//                    e.printStackTrace();
//                    minhaview.erro("Erro ao retornar dados");
//                }
//            }
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
//                minhaview.erro("Erro na consulta");
//            }
//            @Override
//            public void onFailure(int status, Header[] h, Throwable t, JSONObject jo){
//                minhaview.erro("Erro na consulta");
//            }
//        });

//    }

}
