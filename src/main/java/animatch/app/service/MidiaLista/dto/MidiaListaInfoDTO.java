package animatch.app.service.MidiaLista.dto;

import animatch.app.domain.lista.Lista;
import animatch.app.domain.midia.Midia;

public class MidiaListaInfoDTO {
    private Midia midiaId;
    private Lista listaId;

    public MidiaListaInfoDTO() {
    }

    public MidiaListaInfoDTO(Midia midiaId, Lista listaId) {
        this.midiaId = midiaId;
        this.listaId = listaId;
    }

    public Midia getMidiaId() {
        return midiaId;
    }

    public Lista getListaId() {
        return listaId;
    }
}
