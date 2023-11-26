package animatch.app.service.lista.dto;

public class ListaInfoDTO {
    private int id;
    private String name;

    public ListaInfoDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
