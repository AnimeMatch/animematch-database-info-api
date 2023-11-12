package animatch.app.service.usuario;

import animatch.app.api.configuration.security.jwt.GerenciadorTokenJwt;
import animatch.app.api.controller.ListaController;
import animatch.app.domain.usuario.Usuario;
import animatch.app.domain.usuario.repository.UsuarioRepository;
import animatch.app.service.usuario.autenticacao.AutenticacaoService;
import animatch.app.service.usuario.autenticacao.dto.UsuarioTokenDTO;
import animatch.app.service.usuario.dto.UsuarioAtualizarDto;
import animatch.app.service.usuario.dto.UsuarioCadastrarDTO;
import animatch.app.service.usuario.dto.UsuarioLoginDTO;
import animatch.app.service.usuario.dto.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
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
    @Autowired
    private AutenticacaoService usuarioAutorizacaoService;

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


    public ResponseEntity<UsuarioTokenDTO> atualizar(UsuarioAtualizarDto usuarioAtualizar){
        Usuario user = usuarioRepository.findUserById(usuarioAtualizar.getId());

        if (user == null) {
            return ResponseEntity.status(404).build();
        }
        Usuario usuarioMapeado = UsuarioMapper.usuarioAtualizar(usuarioAtualizar, user);
        if (usuarioAtualizar.getPassword() != null){
            UserDetails userDetails = usuarioAutorizacaoService.loadUserByUsername(user.getEmail());
            final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                    user.getEmail(), userDetails.getPassword());
            try {
                this.authenticationManager.authenticate(credentials);
            } catch (AuthenticationException e) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "%s".formatted(e));
            }
            usuarioMapeado.setPassword(usuarioAtualizar.getPassword());
            String senhaCriptografada = passwordEncoder.encode(usuarioMapeado.getPassword());
            usuarioMapeado.setPassword(senhaCriptografada);
            usuarioRepository.save(usuarioMapeado);

            final UsernamePasswordAuthenticationToken newCredentials = new UsernamePasswordAuthenticationToken(
                    usuarioMapeado.getEmail(), usuarioMapeado.getPassword());
            final Authentication newAuthentication = this.authenticationManager.authenticate(newCredentials);

            final String newToken = gerenciadorTokenJwt.generateToken(newAuthentication);
            listController.defaultList(usuarioAtualizar.getId());
            return ResponseEntity.status(200).body(UsuarioMapper.of(usuarioMapeado, newToken));

        } else {
            usuarioRepository.save(usuarioMapeado);
            return ResponseEntity.status(200).body(UsuarioMapper.of(usuarioMapeado));
        }
    }
//    public ResponseEntity<UsuarioTokenDTO> atualizar(UsuarioAtualizarDto usuarioAtualizar) {
//        Usuario user = usuarioRepository.findUserById(usuarioAtualizar.getId());
//        if (user == null) {
//            return ResponseEntity.status(404).build();
//        }
//        Usuario usuarioMapeado = UsuarioMapper.usuarioAtualizar(usuarioAtualizar, user);
//        if (usuarioAtualizar.getPassword() != null) {
//            UserDetails userDetails = usuarioAutorizacaoService.loadUserByUsername(user.getEmail());
//            final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
//                    user.getEmail(), userDetails.getPassword());
//            try {
//                this.authenticationManager.authenticate(credentials);
//            } catch (AuthenticationException e) {
//                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "%s".formatted(e));
//            }
//
//            // Atualizar a senha no objeto usuário antes de criptografar
//            usuarioMapeado.setPassword(usuarioAtualizar.getPassword());
//
//            // Criptografar a nova senha
//            String senhaCriptografada = passwordEncoder.encode(usuarioMapeado.getPassword());
//            usuarioMapeado.setPassword(senhaCriptografada);
//        }
//        usuarioRepository.save(usuarioMapeado);
//
//        final UsernamePasswordAuthenticationToken newCredentials = new UsernamePasswordAuthenticationToken(
//                usuarioMapeado.getEmail(), usuarioMapeado.getPassword());
//        final Authentication newAuthentication = this.authenticationManager.authenticate(newCredentials);
//
//        final String newToken = gerenciadorTokenJwt.generateToken(newAuthentication);
//        listController.defaultList(usuarioAtualizar.getId());
//        return ResponseEntity.status(200).body(UsuarioMapper.of(usuarioMapeado, newToken));
//    }
}
