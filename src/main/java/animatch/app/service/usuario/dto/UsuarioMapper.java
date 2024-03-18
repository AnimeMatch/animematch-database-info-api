package animatch.app.service.usuario.dto;

import animatch.app.domain.usuario.Usuario;
import animatch.app.service.usuario.autenticacao.dto.UsuarioTokenDTO;

import java.time.LocalDate;

public class UsuarioMapper {
    public static Usuario of(UsuarioCadastrarDTO usuarioDTO){
        Usuario usuario = new Usuario();

        usuario.setName(usuarioDTO.getName());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setPassword(usuarioDTO.getPassword());
        usuario.setCoverImage(usuarioDTO.getCoverImage());
        usuario.setProfileImage(usuarioDTO.getProfileImage());
        usuario.setCriacao(LocalDate.now());
        usuario.setGenero(usuarioDTO.getGenero());
        usuario.setBio(usuarioDTO.getBio());

        return usuario;
    }

    public static UsuarioTokenDTO of(Usuario usuario, String token){
        UsuarioTokenDTO usuarioTokenDTO = new UsuarioTokenDTO();

        usuarioTokenDTO.setUserId(usuario.getId());
        usuarioTokenDTO.setName(usuario.getName());
        usuarioTokenDTO.setEmail(usuario.getEmail());
        usuarioTokenDTO.setToken(token);

        return usuarioTokenDTO;
    }
    public static UsuarioTokenDTO of(Usuario usuario){
        UsuarioTokenDTO usuarioTokenDTO = new UsuarioTokenDTO();

        usuarioTokenDTO.setUserId(usuario.getId());
        usuarioTokenDTO.setName(usuario.getName());
        usuarioTokenDTO.setEmail(usuario.getEmail());

        return usuarioTokenDTO;
    }

    public static Usuario usuarioAtualizar(UsuarioAtualizarDto usuarioAtualizacao, Usuario usuarioAnterior){
        final Usuario usuarioRetorno = new Usuario(
                usuarioAnterior.getId(),
                usuarioAnterior.getName(),
                usuarioAnterior.getEmail(),
                usuarioAnterior.getPassword(),
                usuarioAnterior.getProfileImage(),
                usuarioAnterior.getCoverImage(),
                usuarioAnterior.getCriacao(),
                usuarioAnterior.isStatus(),
                usuarioAnterior.getGenero(),
                usuarioAnterior.getBio()
        );
        if (usuarioAtualizacao.getName() != null) {
            usuarioRetorno.setName(usuarioAtualizacao.getName());
        }

        if (usuarioAtualizacao.getPassword() != null) {
            usuarioRetorno.setPassword(usuarioAtualizacao.getPassword());
        }

        if (usuarioAtualizacao.getProfileImage() != null) {
            usuarioRetorno.setProfileImage(usuarioAtualizacao.getProfileImage());
        }

        if (usuarioAtualizacao.getCoverImage() != null) {
            usuarioRetorno.setCoverImage(usuarioAtualizacao.getCoverImage());
        }

        if (usuarioAtualizacao.getGenero() != null) {
            usuarioRetorno.setGenero(usuarioAtualizacao.getGenero());
        }

        if (usuarioAtualizacao.getBio() != null) {
            usuarioRetorno.setBio(usuarioAtualizacao.getBio());
        }

        return usuarioRetorno;
    }

}
