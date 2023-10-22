package animatch.app.service.usuario.dto;

import animatch.app.domain.usuario.Usuario;
import animatch.app.service.usuario.autenticacao.dto.UsuarioTokenDTO;

public class UsuarioMapper {
    public static Usuario of(UsuarioCadastrarDTO usuarioDTO){
        Usuario usuario = new Usuario();

        usuario.setName(usuarioDTO.getName());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setPassword(usuarioDTO.getPassword());
        usuario.setCoverImage(usuarioDTO.getCoverImage());
        usuario.setProfileImage(usuarioDTO.getProfileImage());
        usuario.setCriacao(usuarioDTO.getNascimento());

        return usuario;
    }

    public static UsuarioTokenDTO of(Usuario usuario, String token){
        UsuarioTokenDTO usuarioTokenDTO = new UsuarioTokenDTO();

        usuarioTokenDTO.setUserId(usuario.getId());
        usuarioTokenDTO.setEmail(usuario.getEmail());
        usuarioTokenDTO.setToken(token);

        return usuarioTokenDTO;
    }
}
