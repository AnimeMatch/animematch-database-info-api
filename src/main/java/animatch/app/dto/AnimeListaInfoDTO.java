package animatch.app.dto;

import animatch.app.model.Anime;
import animatch.app.model.Lista;

public class AnimeListaInfoDTO {
    private Anime animeId;
    private ListaInfoDTO listaId;

    public AnimeListaInfoDTO() {
    }

    public AnimeListaInfoDTO(Anime animeId, ListaInfoDTO listaId) {
        this.animeId = animeId;
        this.listaId = listaId;
    }

    public Anime getAnimeId() {
        return animeId;
    }

    public ListaInfoDTO getListaId() {
        return listaId;
    }
}
