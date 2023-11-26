package animatch.app.service.Anime.dto;

import animatch.app.domain.anime.Anime;

public class AnimeInfoDTO {
    private Anime anime;

    public AnimeInfoDTO(Anime anime) {
        this.anime = anime;
    }

    public Anime getAnime() {
        return anime;
    }
}
