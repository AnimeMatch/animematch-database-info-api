package animatch.app.service.Anime.dto;


public class AnimeParaSalvarDto {
    private int idApi;
    private double notaMedia;
    private String imagem;

    public AnimeParaSalvarDto() {
    }

    public AnimeParaSalvarDto(int idApi, double notaMedia, String imagem) {
        this.idApi = idApi;
        this.notaMedia = notaMedia;
        this.imagem = imagem;
    }

    public int getIdApi() {
        return idApi;
    }

    public void setIdApi(int idApi) {
        this.idApi = idApi;
    }

    public double getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(double notaMedia) {
        this.notaMedia = notaMedia;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
