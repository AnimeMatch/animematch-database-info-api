package animatch.app.dto;

import animatch.app.model.Usuario;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

public class ListaComQtdDTO {
    private int id;
    private String name;
    private int qtdAnimes;

    public ListaComQtdDTO() {
    }

    public ListaComQtdDTO(int id, String name, int qtdAnimes) {
        this.id = id;
        this.name = name;
        this.qtdAnimes = qtdAnimes;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQtdAnimes() {
        return qtdAnimes;
    }
}
