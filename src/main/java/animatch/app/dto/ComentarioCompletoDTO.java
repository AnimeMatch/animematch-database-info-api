package animatch.app.dto;

import animatch.app.domain.comentario.Comentario;
import animatch.app.domain.topico.Topico;

public class ComentarioCompletoDTO {
    private int id;
    private String texto;
    private Topico topico;
    private Comentario comentarioPai;

    public ComentarioCompletoDTO(int id, String texto, Topico topico, Comentario comentarioPai) {
        this.id = id;
        this.texto = texto;
        this.topico = topico;
        this.comentarioPai = comentarioPai;
    }

    public int getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    public Topico getTopico() {
        return topico;
    }

    public Comentario getComentarioPai() {
        return comentarioPai;
    }
}
