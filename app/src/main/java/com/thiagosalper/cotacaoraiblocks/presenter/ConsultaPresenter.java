package com.thiagosalper.cotacaoraiblocks.presenter;

import com.thiagosalper.cotacaoraiblocks.model.Moeda;

/**
 * Created by thiagopereira on 04/12/2017.
 */

public interface ConsultaPresenter {
    void carregando();
    void erro(String erro);
    void mostrar(Moeda moeda);
}
