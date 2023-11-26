package animatch.app.dto;

import java.time.LocalDate;

public class ComentarioSimplesDTO {
    private int id;
    private String texto;
    private LocalDate data;
    private UsuarioSimplesDto usuarioSimplesDto;

    public ComentarioSimplesDTO() {
    }

    public ComentarioSimplesDTO(int id, String texto, LocalDate data) {
        this.id = id;
        this.texto = texto;
        this.data = data;
        this.usuarioSimplesDto = null;
    }

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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public UsuarioSimplesDto getUsuarioSimplesDto() {
        return usuarioSimplesDto;
    }

    public void setUsuarioSimplesDto(UsuarioSimplesDto usuarioSimplesDto) {
        this.usuarioSimplesDto = usuarioSimplesDto;
    }
}
