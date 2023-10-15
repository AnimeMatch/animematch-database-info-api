package animatch.app.dto;

public class ComentarioSimplesDTO {
    private int id;
    private String texto;

    public ComentarioSimplesDTO(int id, String texto) {
        this.id = id;
        this.texto = texto;
    }

    public int getId() {
        return id;
    }
    public String getTexto() {
        return texto;
    }
}
