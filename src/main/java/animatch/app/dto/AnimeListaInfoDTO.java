package animatch.app.dto;

import animatch.app.domain.Anime;
import animatch.app.domain.Lista;

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
