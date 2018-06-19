package com.thiagosalper.cotacaoraiblocks.model;

import com.orm.dsl.Table;

/**
 * Created by thiagopereira on 27/01/2018.
 */

@Table
public class Saldo {
    private Long id;
    private Float valor;
    private String descricao;

    public Saldo() {
    }

    public Saldo(Long id, Float valor, String descricao) {
        this.id = id;
        this.valor = valor;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
