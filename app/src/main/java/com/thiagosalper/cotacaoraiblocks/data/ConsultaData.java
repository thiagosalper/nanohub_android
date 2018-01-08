package com.thiagosalper.cotacaoraiblocks.data;

import com.thiagosalper.cotacaoraiblocks.presenter.ConsultaPresenter;

/**
 * Created by thiagopereira on 04/12/2017.
 */

public interface ConsultaData {
    void busca(final ConsultaPresenter view);
    void buscaBitcoin();
    void buscaRaiBlocks();

}
