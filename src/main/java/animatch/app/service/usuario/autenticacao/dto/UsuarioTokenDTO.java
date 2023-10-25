package animatch.app.service.usuario.autenticacao.dto;

public class UsuarioTokenDTO {
    private int userId;
    private String name;
    private String email;
    private String token;

    public UsuarioTokenDTO() {
    }

    public UsuarioTokenDTO(int userId, String email, String token) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.token = token;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
