package animatch.app.domain.midialista;

import animatch.app.domain.lista.Lista;
import animatch.app.domain.midia.Midia;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
@Entity
public class MidiaLista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único", example = "1")
    private int midiaListaId;
    @NotNull
    @ManyToOne
    @Schema(description = "Midia cadastrado no banco", example = "1")
    private Midia midiaId;
    @NotNull
    @ManyToOne
    // @JsonIgnore // Caso haja recursividade infinita
    @Schema(description = "Lista na qual midia passará a pertencer", example = "1")
    private Lista listaId;

    public MidiaLista() {
    }

    public MidiaLista(Midia midiaId, Lista listaId) {
        this.midiaId = midiaId;
        this.listaId = listaId;
    }

    public MidiaLista(int midiaListaId, Midia midiaId, Lista listaId) {
        this.midiaListaId = midiaListaId;
        this.midiaId = midiaId;
        this.listaId = listaId;
    }

    public int getMidiaListaId() {
        return midiaListaId;
    }

    public void setMidiaListaId(int midiaListaId) {
        this.midiaListaId = midiaListaId;
    }

    public Midia getMidiaId() {
        return midiaId;
    }

    public void setMidiaId(Midia midiaId) {
        this.midiaId = midiaId;
    }

    public Lista getListaId() {
        return listaId;
    }

    public void setListaId(Lista listaId) {
        this.listaId = listaId;
    }
}
