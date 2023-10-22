package animatch.app.domain.anime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único", example = "1")
    private int id;
    @NotNull
    @Schema(description = "Id do anime na API anilist", example = "253")
    private int idApi;
    @Schema(description = "Nota média do anime provida pela API", example = "56.7")
    private double notaMedia;
    @Schema(description = "Link para imagem do anime", example = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx113415-bbBWj4pEFseh.jpg")
    private String imagem;

    public Anime() {
    }

    public Anime(int id, int idApi, double notaMedia) {
        this.id = id;
        this.idApi = idApi;
        this.notaMedia = notaMedia;
    }

    public int getId() {
        return id;
    }

    public int getIdApi() {
        return idApi;
    }

    public double getNotaMedia() {
        return notaMedia;
    }

    public String getImagem() {
        return imagem;
    }
}
