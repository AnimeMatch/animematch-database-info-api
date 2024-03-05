package animatch.app.domain.comentarioAnime;

import animatch.app.domain.comentario.Comentario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
public class ComentarioAnime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único", example = "1")
    private int id;
    @NotBlank
    @Size(min = 5, max = 250)
    @Schema(description = "Conteúdo do comentário", example = "Melhor episódio que eu vi!")
    private String texto;
    @ManyToOne
    @JsonIgnore
    @Schema(description = "Comentário pai", example = "1")
    private ComentarioAnime comentarioAnimePai;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataComentarioAnime = LocalDate.now();

    @NotNull
    private String emailUsuario;

    private int idMidiaApi;

    private int qtdLikes;

    private int qtdDeslikes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }


    public LocalDate getDateComentario() {
        return dataComentarioAnime;
    }

    public void setDateComentario(LocalDate dateComentario) {
        this.dataComentarioAnime = dateComentario;
    }

    public ComentarioAnime getComentarioPai() {
        return comentarioAnimePai;
    }

    public void setComentarioPai(ComentarioAnime comentarioPai) {
        this.comentarioAnimePai = comentarioPai;
    }

    public LocalDate getDataComentario() {
        return dataComentarioAnime;
    }

    public void setDataComentario(LocalDate dataComentarioAnime) {
        this.dataComentarioAnime = dataComentarioAnime;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public int getIdMidiaApi() {
        return idMidiaApi;
    }

    public void setIdMidiaApi(int idMidiaApi) {
        this.idMidiaApi = idMidiaApi;
    }

    public int getQtdLikes() {
        return qtdLikes;
    }

    public void setQtdLikes(int qtdLikes) {
        this.qtdLikes = qtdLikes;
    }

    public int getQtdDeslikes() {
        return qtdDeslikes;
    }

    public void setQtdDeslikes(int qtdDeslikes) {
        this.qtdDeslikes = qtdDeslikes;
    }

}
