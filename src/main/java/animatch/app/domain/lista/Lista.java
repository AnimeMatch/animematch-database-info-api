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
    @ManyToOne
    @JsonIgnore
    @Schema(description = "Id do usuário criador da lista", example = "1")
    private Usuario userId;
    @NotNull
    @Size(min=1, max=45)
    @Schema(description = "Nome da lista", example = "Favoritos")
    private String name;

    @Size(min=1, max=250)
    @Schema(description = "Nome da lista", example = "Favoritos")
    private String descricao;

    public Lista() {
    }
    public Lista(String name, String descricao) {
        this.name = name;
        this.descricao = descricao;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
