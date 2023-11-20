package animatch.app.domain.animelista;

import animatch.app.domain.lista.Lista;
import animatch.app.domain.anime.Anime;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class AnimeLista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único", example = "1")
    private int AnimeListaId;
    @NotNull
    @ManyToOne
    @Schema(description = "Anime cadastrado no banco", example = "1")
    private Anime animeId;
    @NotNull
    @ManyToOne
    @Schema(description = "Lista na qual anime passará a pertencer", example = "1")
    private Lista listaId;

    public AnimeLista() {
    }

    public AnimeLista(Anime animeId, Lista listaId) {
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
