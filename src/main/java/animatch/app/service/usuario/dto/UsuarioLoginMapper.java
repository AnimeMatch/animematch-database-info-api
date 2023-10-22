package animatch.app.service.usuario.dto;

public class UsuarioLoginMapper {
    public static UsuarioLoginDTO of(UsuarioLoginDTO usuarioLoginDTO){
        final UsuarioLoginDTO usuario = new UsuarioLoginDTO();
        usuario.setPassword(usuarioLoginDTO.getPassword());
        usuario.setEmail(usuarioLoginDTO.getEmail());
        return usuario;
    }
}
