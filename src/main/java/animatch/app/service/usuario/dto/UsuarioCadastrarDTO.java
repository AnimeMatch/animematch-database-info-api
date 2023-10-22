package animatch.app.service.usuario.dto;

import animatch.app.domain.usuario.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UsuarioCadastrarDTO {
    @Schema(description = "Nome do usuário", example = "fulano de tal")
    private String name;
    @Schema(description = "E-mail do usuário", example = "usuario@email.com")
    private String email;
    @Schema(description = "Senha do usuario", example = "senhasecreta")
    private String password;
    @Schema(description = "Link para imagem de perfil", example = "")
    private String profileImage;
    @Schema(description = "Link para imagem de capa do perfil", example = "")
    private String coverImage;
    @Schema(description = "Data de nascimento", example = "2000-01-01")
    private LocalDate nascimento;
    private boolean status = true;

    public UsuarioCadastrarDTO() {
    }

    public UsuarioCadastrarDTO(String name, String email, String password, String profileImage, String coverImage, LocalDate nascimento) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.profileImage = profileImage;
        this.coverImage = coverImage;
        this.nascimento = nascimento;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
