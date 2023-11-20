package animatch.app.domain.comentario;

import animatch.app.domain.topico.Topico;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único", example = "1")
    private int id;
    @NotBlank
    @Size(min = 5, max = 250)
    @Schema(description = "Conteúdo do comentário", example = "Melhor episódio que eu vi!")
    private String texto;

    @ManyToOne
    @Schema(description = "Tópico ao qual o anime pertence", example = "1")
    private Topico topico;
    @ManyToOne
    @JsonIgnore
    @Schema(description = "Comentário pai", example = "1")
    private Comentario comentarioPai;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataComentario = LocalDate.now();

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

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    public Comentario getComentario() {
        return comentarioPai;
    }

    public void setComentario(Comentario comentario) {
        this.comentarioPai = comentario;
    }

    public Comentario getComentarioPai() {
        return comentarioPai;
    }

    public void setComentarioPai(Comentario comentarioPai) {
        this.comentarioPai = comentarioPai;
    }

    public LocalDate getDateComentario() {
        return dataComentario;
    }

    public void setDateComentario(LocalDate dateComentario) {
        this.dataComentario = dateComentario;
    }
}
