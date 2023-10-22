package animatch.app.service.usuario.autenticacao.dto;

import animatch.app.domain.usuario.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UsuarioDetalhesDTO implements UserDetails {
    private final String name;
    private final String email;
    private final String senha;

    public UsuarioDetalhesDTO(Usuario usuario) {
        this.email = usuario.getEmail();
        this.senha = usuario.getPassword();
        this.name = usuario.getName();
    }

    public String getName() {
        return name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
