package animatch.app.dto;

import animatch.app.domain.Anime;

public class AnimeInfoDTO {
    private Anime anime;

    public AnimeInfoDTO(Anime anime) {
        this.anime = anime;
    }

    public Anime getAnime() {
        return anime;
    }
}