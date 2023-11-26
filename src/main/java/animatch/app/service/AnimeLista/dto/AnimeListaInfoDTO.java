package animatch.app.service.AnimeLista.dto;

import animatch.app.domain.anime.Anime;
import animatch.app.domain.lista.Lista;
import animatch.app.service.lista.dto.ListaInfoDTO;

public class AnimeListaInfoDTO {
    private Anime animeId;
    private Lista listaId;

    public AnimeListaInfoDTO() {
    }

    public AnimeListaInfoDTO(Anime animeId, Lista listaId) {
        this.animeId = animeId;
        this.listaId = listaId;
    }

    public Anime getAnimeId() {
        return animeId;
    }

    public Lista getListaId() {
        return listaId;
    }
}
