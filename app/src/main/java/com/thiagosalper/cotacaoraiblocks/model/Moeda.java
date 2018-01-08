package com.thiagosalper.cotacaoraiblocks.model;

/**
 * Created by thiagopereira on 04/12/2017.
 */

public class Moeda {
    private String rank;
    private String price_usd;
    private String price_btc;
    private String percent_change_1h;
    private String percent_change_24h;
    private String last_update;
    private String valor_real;

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPrice_usd() {
        return price_usd;
    }

    public void setPrice_usd(String price_usd) {
        this.price_usd = price_usd;
    }

    public String getPrice_btc() {
        return price_btc;
    }

    public void setPrice_btc(String price_btc) {
        this.price_btc = price_btc;
    }

    public String getPercent_change_1h() {
        return percent_change_1h;
    }

    public void setPercent_change_1h(String percent_change_1h) {
        this.percent_change_1h = percent_change_1h;
    }

    public String getPercent_change_24h() {
        return percent_change_24h;
    }

    public void setPercent_change_24h(String percent_change_24h) {
        this.percent_change_24h = percent_change_24h;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    public String getValor_real() {
        return valor_real;
    }

    public void setValor_real(String valor_real) {
        this.valor_real = valor_real;
    }
}
