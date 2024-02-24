package animatch.app.service.Midia.dto;


public class MidiaParaSalvarDto {
    private int idApi;
    private String nome;
    private double notaMedia;
    private String imagem;
    private String tipo;

    public MidiaParaSalvarDto() {
    }

    public MidiaParaSalvarDto(int idApi, String nome, double notaMedia, String imagem, String tipo) {
        this.idApi = idApi;
        this.nome = nome;
        this.notaMedia = notaMedia;
        this.imagem = imagem;
        this.tipo = tipo;
    }

    public int getIdApi() {
        return idApi;
    }

    public void setIdApi(int idApi) {
        this.idApi = idApi;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
