package animatch.app.service.usuario;

import animatch.app.api.configuration.security.jwt.GerenciadorTokenJwt;
import animatch.app.api.controller.ListaController;
import animatch.app.domain.usuario.Usuario;
import animatch.app.domain.usuario.repository.UsuarioRepository;
import animatch.app.service.usuario.autenticacao.dto.UsuarioTokenDTO;
import animatch.app.service.usuario.dto.UsuarioCadastrarDTO;
import animatch.app.service.usuario.dto.UsuarioLoginDTO;
import animatch.app.service.usuario.dto.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ListaController listController;

    public ResponseEntity<Usuario> criar(UsuarioCadastrarDTO usuarioCadastrarDTO){
        final Usuario novoUsuario = UsuarioMapper.of(usuarioCadastrarDTO);
        if(this.usuarioRepository.existsByEmail(novoUsuario.getEmail())){
            return ResponseEntity.status(409).build();
        }

        String senhaCriptografada = passwordEncoder.encode(novoUsuario.getPassword());
        novoUsuario.setPassword(senhaCriptografada);

        usuarioRepository.save(novoUsuario);
        listController.defaultList(novoUsuario.getId());
        return ResponseEntity.status(201).body(novoUsuario);
    }

//    public ResponseEntity<Usuario> login(UsuarioLoginDTO usuarioLoginDTO){
////        final UsuarioLoginDTO login = UsuarioLoginMapper.of(usuarioLoginDTO);
//        Usuario user = usuarioRepository.findUserByEmailPasword(usuarioLoginDTO.getEmail(), usuarioLoginDTO.getPassword());
//        if (user != null){
//            return ResponseEntity.status(200).body(user);
//        }
//        return ResponseEntity.status(403).build();
//    }

    public ResponseEntity<UsuarioTokenDTO> autenticar(UsuarioLoginDTO login) {
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                login.getEmail(), login.getPassword());
        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Usuario usuarioAutenticado =
                usuarioRepository.findByEmail(login.getEmail());

//        Integer status = usuarioAutenticado == null ? 403 : 200;
        if(usuarioAutenticado == null){
            return ResponseEntity.status(403).build();
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = gerenciadorTokenJwt.generateToken(authentication);
//        return ResponseEntity.status(200).body(UsuarioMapper.of(usuarioAutenticado, token));
        return ResponseEntity.status(200).body(UsuarioMapper.of(usuarioAutenticado, token));
    }
}
