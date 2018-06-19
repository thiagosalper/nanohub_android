package com.thiagosalper.cotacaoraiblocks.data;

import com.thiagosalper.cotacaoraiblocks.presenter.ConsultaPresenter;

public interface BitvalorData {
    // https://api.bitvalor.com/v1/ticker.json
    void busca(final ConsultaPresenter view);
}
