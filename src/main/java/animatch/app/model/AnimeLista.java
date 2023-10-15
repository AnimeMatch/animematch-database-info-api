package animatch.app.model;

import jakarta.persistence.*;

@Entity
public class AnimeLista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int AnimeListaId;

    // É necessario adicionar apenas os ids
    @ManyToOne
    private Anime animeId;
    @ManyToOne
    private Lista listaId;

    public AnimeLista() {
    }

    public AnimeLista(int animeListaId, Anime animeId, Lista listaId) {
        AnimeListaId = animeListaId;
        this.animeId = animeId;
        this.listaId = listaId;
    }

    public int getAnimeListaId() {
        return AnimeListaId;
    }

    public void setAnimeListaId(int animeListaId) {
        AnimeListaId = animeListaId;
    }

    public Anime getAnimeId() {
        return animeId;
    }

    public void setAnimeId(Anime animeId) {
        this.animeId = animeId;
    }

    public Lista getListaId() {
        return listaId;
    }

    public void setListaId(Lista listaId) {
        this.listaId = listaId;
    }
}
