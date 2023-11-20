package animatch.app.service.Anime.dto;

import animatch.app.domain.anime.Anime;
import animatch.app.service.lista.dto.ListaInfoDTO;

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
