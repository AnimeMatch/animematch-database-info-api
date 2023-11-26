package animatch.app.dto;

import animatch.app.domain.comentario.Comentario;
import animatch.app.domain.topico.Topico;

import java.util.List;

public class ComentarioCompletoDTO {
    private int id;
    private String texto;
    private Topico topico;
    private Comentario comentarioPai;
    private UsuarioSimplesDto usuarioSimplesDto;

    public ComentarioCompletoDTO() {
    }

    public ComentarioCompletoDTO(int id, String texto, Topico topico, Comentario comentarioPai, UsuarioSimplesDto usuarioSimplesDto) {
        this.id = id;
        this.texto = texto;
        this.topico = topico;
        this.comentarioPai = comentarioPai;
        this.usuarioSimplesDto = usuarioSimplesDto;
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

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    public Comentario getComentarioPai() {
        return comentarioPai;
    }

    public void setComentarioPai(Comentario comentarioPai) {
        this.comentarioPai = comentarioPai;
    }

    public UsuarioSimplesDto getUsuarioSimplesDto() {
        return usuarioSimplesDto;
    }

    public void setUsuarioSimplesDto(UsuarioSimplesDto usuarioSimplesDto) {
        this.usuarioSimplesDto = usuarioSimplesDto;
    }
}
