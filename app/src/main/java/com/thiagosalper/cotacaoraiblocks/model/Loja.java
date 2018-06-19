package com.thiagosalper.cotacaoraiblocks.model;

/**
 * Created by thiagopereira on 23/01/2018.
 */

public class Loja {
    private Long id;
    private String nome;
    private Long status;
    private String url;
    private String descricao;


    public Loja() {
    }

    public Loja(Long id, String nome, Long status, String url, String descricao) {
        this.id = id;
        this.nome = nome;
        this.status = status;
        this.url = url;
        this.descricao = descricao;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
