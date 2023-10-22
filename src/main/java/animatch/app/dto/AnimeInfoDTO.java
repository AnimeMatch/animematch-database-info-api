package animatch.app.dto;

import animatch.app.domain.anime.Anime;

public class AnimeInfoDTO {
    private Anime anime;
    private String coverImage;
    private String nome;


    public AnimeInfoDTO(Anime anime) {
        this.anime = anime;
    }

    public Anime getAnime() {
        return anime;
    }
}
