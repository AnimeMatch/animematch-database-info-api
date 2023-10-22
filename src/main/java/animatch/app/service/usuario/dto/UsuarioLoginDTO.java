package animatch.app.service.usuario.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

public class UsuarioLoginDTO {
    @Size(min=3, max=45)
    @Schema(description = "E-mail do usuário", example = "usuario@email.com")
    private String email;
    @Size(min=0, max=45)
    @Schema(description = "Senha do usuario", example = "senhasecreta")
    private String password;

    public UsuarioLoginDTO() {
    }

    public UsuarioLoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
