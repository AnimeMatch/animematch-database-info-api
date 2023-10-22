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
    @Schema(description = "Titulo do anime", example = "Naruto")
    private String nome;
    @Schema(description = "Nota média do anime provida pela API", example = "56.7")
    private double notaMedia;
    @Schema(description = "Link para imagem do anime", example = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx113415-bbBWj4pEFseh.jpg")
    private String imagem;

    public Anime() {
    }

    public Anime(int id, int idApi, String nome, double notaMedia, String imagem) {
        this.id = id;
        this.idApi = idApi;
        this.nome = nome;
        this.notaMedia = notaMedia;
        this.imagem = imagem;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
}
