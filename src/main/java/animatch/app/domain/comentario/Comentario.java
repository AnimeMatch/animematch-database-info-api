package animatch.app.domain.comentario;

import animatch.app.domain.topico.Topico;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único", example = "1")
    private int id;
    @NotBlank
    @Schema(description = "Conteúdo do comentário", example = "Melhor episódio que eu vi!")
    private String texto;
    @ManyToOne
    @Schema(description = "Tópico ao qual o anime pertence", example = "1")
    private Topico topico;
    @ManyToOne
    @Schema(description = "Comentário pai", example = "1")
    private Comentario comentarioPai;

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

}
