package com.thiagosalper.cotacaoraiblocks.model;

import com.orm.dsl.Table;

/**
 * Created by thiagopereira on 18/01/2018.
 */

//@Table
public class Exchange {
    private Long id;
    private String nome;
    private Long status;
    private String url;

    public Exchange() {
    }

    public Exchange(Long id, String nome, Long status, String url) {
        this.id = id;
        this.nome = nome;
        this.status = status;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
