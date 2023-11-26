package animatch.app.service.AnimeLista.dto;

import animatch.app.domain.anime.Anime;
import animatch.app.service.lista.dto.ListaInfoDTO;

public class AnimeListaInfoDadosBrutosDto {
    private int animeId;
    private int listaId;

    public AnimeListaInfoDadosBrutosDto() {
    }

    public AnimeListaInfoDadosBrutosDto(int animeId, int listaId) {
        this.animeId = animeId;
        this.listaId = listaId;
    }

    public int getAnimeId() {
        return animeId;
    }

    public int getListaId() {
        return listaId;
    }
}
