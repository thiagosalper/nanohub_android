package com.thiagosalper.cotacaoraiblocks.model;

import com.orm.dsl.Table;

/**
 * Created by thiagopereira on 18/01/2018.
 */

@Table
public class Alerta {
    private Long id;
    private boolean ativo;
    private String valor;
    private String porcentagem;
    private String nome;

    public Alerta() {
    }

    public Alerta(Long id, boolean ativo, String valor, String porcentagem, String nome) {
        this.id = id;
        this.ativo = ativo;
        this.valor = valor;
        this.porcentagem = porcentagem;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(String porcentagem) {
        this.porcentagem = porcentagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
