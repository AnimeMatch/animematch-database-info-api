package animatch.app.service.midiaLista.dto;

public class MidiaListaInfoDadosBrutosDto {
    private int midiaId;
    private int listaId;

    public MidiaListaInfoDadosBrutosDto() {
    }

    public MidiaListaInfoDadosBrutosDto(int midiaId, int listaId) {
        this.midiaId = midiaId;
        this.listaId = listaId;
    }

    public int getMidiaId() {
        return midiaId;
    }

    public int getListaId() {
        return listaId;
    }
}
