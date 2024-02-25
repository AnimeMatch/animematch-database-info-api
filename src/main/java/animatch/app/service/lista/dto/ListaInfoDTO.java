package animatch.app.service.lista.dto;

public class ListaInfoDTO {
    private int id;
    private String name;
    private int type;

    public ListaInfoDTO(int id, String name, int type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public int getType() {return type;}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
