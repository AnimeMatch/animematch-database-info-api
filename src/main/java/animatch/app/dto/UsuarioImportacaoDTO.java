package animatch.app.dto;

import animatch.app.domain.usuario.Usuario;

import java.time.LocalDate;

public class UsuarioImportacaoDTO {
    private int id;
    private String name;
    private String genero;
    private String email;
    private LocalDate criacao;
    private String profileImage;
    private String coverImage;
    private boolean status;
    private Integer quantidade;

    public UsuarioImportacaoDTO() {
    }

    public UsuarioImportacaoDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.name = usuario.getName();
        this.genero = usuario.getGenero();
        this.email = usuario.getEmail();
        this.criacao = usuario.getCriacao();
        this.profileImage = usuario.getProfileImage();
        this.coverImage = usuario.getCoverImage();
        this.status = usuario.isStatus();
        this.quantidade = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getCriacao() {
        return criacao;
    }

    public void setCriacao(LocalDate criacao) {
        this.criacao = criacao;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
