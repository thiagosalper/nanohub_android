package com.thiagosalper.cotacaoraiblocks.model;

import com.orm.dsl.Table;

/**
 * Created by thiagopereira on 09/01/2018.
 */

@Table
public class Endereco {
    private Long id;
    private String endereco;
    private String descricao;

    public Endereco() {
    }

    public Endereco(Long id, String endereco, String descricao) {
        this.id = id;
        this.endereco = endereco;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
