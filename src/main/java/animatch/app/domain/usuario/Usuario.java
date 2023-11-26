package animatch.app.domain.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Validated
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único", example = "1")
    private int id;
    @Schema(description = "Nome do usuário", example = "fulano de tal")
    private String name;
    @Email
//    @Pattern(
//        regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$"
//    )
    @Schema(description = "E-mail do usuário", example = "usuario@email.com")
    private String email;
    @Schema(description = "Senha do usuario", example = "senhasecreta")
    private String password;
    @Schema(description = "Link para imagem de perfil", example = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx113415-bbBWj4pEFseh.jpg")
    private String profileImage;
    @Schema(description = "Link para imagem de capa do perfil", example = "https://s4.anilist.co/file/anilistcdn/media/anime/banner/113415-jQBSkxWAAk83.jpg")
    private String coverImage;
    @Schema(description = "Data de criação da conta", example = "2000-01-01")
    @Past
    private LocalDate criacao;
    @Schema(description = "Status de conta ativa 'true' ou 'false'", example = "true")
    private boolean status = true;
    @Schema(description = "Genero de orintação sexual", example = "masculino")
    private String genero;

    public Usuario() {
    }

    public Usuario(int id, String name, String email, String password, String profileImage, String coverImage, LocalDate criacao, boolean status, String genero) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.profileImage = profileImage;
        this.coverImage = coverImage;
        this.criacao = criacao;
        this.status = status;
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public LocalDate getCriacao() {
        return criacao;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public void setCriacao(LocalDate nascimento) {
        this.criacao = nascimento;
    }


    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
