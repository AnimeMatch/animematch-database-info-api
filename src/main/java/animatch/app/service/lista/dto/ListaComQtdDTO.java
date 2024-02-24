package animatch.app.service.lista.dto;

public class ListaComQtdDTO {
    private int id;
    private String name;
    private int qtdMidias;

    public ListaComQtdDTO() {
    }

    public ListaComQtdDTO(int id, String name, int qtdMidias) {
        this.id = id;
        this.name = name;
        this.qtdMidias = qtdMidias;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQtdMidias() {
        return qtdMidias;
    }
}
