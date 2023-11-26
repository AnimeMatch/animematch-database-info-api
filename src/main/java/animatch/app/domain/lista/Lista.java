package animatch.app.domain.lista;

import animatch.app.domain.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

@Validated
@Entity
public class Lista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único", example = "1")
    private int id;
    @NotNull
    @ManyToOne
    @JsonIgnore
    @Schema(description = "Id do usuário criador da lista", example = "1")
    private Usuario userId;
    @NotNull
    @Size(min=1, max=45)
    @Schema(description = "Nome da lista", example = "Favoritos")
    private String name;

    public Lista() {
    }
    public Lista(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Usuario getUserId() {
        return userId;
    }

    public void setUserId(Usuario userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }
}
