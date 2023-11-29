package animatch.app.dto;

import java.time.LocalDate;

public class ComentarioSimplesDTO {
    private int id;
    private String texto;
    private LocalDate data;
    private int qtdLikes;
    private int qtdDeslikes;
    private int qtdComentariosFilhos;
    private UsuarioSimplesDto usuarioSimplesDto;

    public ComentarioSimplesDTO() {
    }

    public ComentarioSimplesDTO(int id, String texto, LocalDate data, int qtdLikes, int qtdDeslikes) {
        this.id = id;
        this.texto = texto;
        this.data = data;
        this.qtdLikes = qtdLikes;
        this.qtdDeslikes = qtdDeslikes;
        this.qtdComentariosFilhos = 0;
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

    public int getQtdLikes() {
        return qtdLikes;
    }

    public void setQtdLikes(int qtdLikes) {
        this.qtdLikes = qtdLikes;
    }

    public int getQtdDeslikes() {
        return qtdDeslikes;
    }

    public void setQtdDeslikes(int qtdDeslikes) {
        this.qtdDeslikes = qtdDeslikes;
    }

    public int getQtdComentariosFilhos() {
        return qtdComentariosFilhos;
    }

    public void setQtdComentariosFilhos(int qtdComentariosFilhos) {
        this.qtdComentariosFilhos = qtdComentariosFilhos;
    }
}
