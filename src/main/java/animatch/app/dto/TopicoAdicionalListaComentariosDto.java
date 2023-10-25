package animatch.app.dto;

import java.util.List;

public class TopicoAdicionalListaComentariosDto {

    private int id;

    private String titulo;

    private List<ComentarioSimplesDTO> comentarios;

    public TopicoAdicionalListaComentariosDto() {
    }

    public TopicoAdicionalListaComentariosDto(int id, String titulo, List<ComentarioSimplesDTO> comentarios) {
        this.id = id;
        this.titulo = titulo;
        this.comentarios = comentarios;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<ComentarioSimplesDTO> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioSimplesDTO> comentarios) {
        this.comentarios = comentarios;
    }
}
