package animatch.app.domain.anime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import org.springframework.validation.annotation.Validated;

@Validated
@Entity
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único", example = "1")
    private int id;
    @NotNull
    @Positive
    @Schema(description = "Id do anime na API anilist", example = "253")
    private int idApi;
    private String nome;
    @DecimalMin("0.0")
    @DecimalMax("100.0")
    @Schema(description = "Nota média do anime provida pela API", example = "56.7")
    private double notaMedia;
    @Schema(description = "Link para imagem do anime",
            example = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx113415-bbBWj4pEFseh.jpg")
    private String imagem;
    @PositiveOrZero
    @Schema(description = "Quantidade de likes do anime", example = "10")
    private int likes;

    public Anime() {
    }

    public Anime(int idApi, String nome, double notaMedia, String imagem) {
        this.idApi = idApi;
        this.nome = nome;
        this.notaMedia = notaMedia;
        this.imagem = imagem;
        this.likes = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdApi() {
        return idApi;
    }

    public void setIdApi(int idApi) {
        this.idApi = idApi;
    }

    public double getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(double notaMedia) {
        this.notaMedia = notaMedia;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public int getLikes() {
        return likes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void somarLikes(){
        this.likes++;
    }
}
