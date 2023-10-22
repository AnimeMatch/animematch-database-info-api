package animatch.app.domain.animelista;

import animatch.app.domain.lista.Lista;
import animatch.app.domain.anime.Anime;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
public class AnimeLista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único", example = "1")
    private int AnimeListaId;
    @ManyToOne
    @Schema(description = "Anime cadastrado no banco", example = "1")
    private Anime animeId;
    @ManyToOne
    @Schema(description = "Lista na qual anime passará a pertencer", example = "1")
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
