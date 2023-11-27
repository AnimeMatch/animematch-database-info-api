package animatch.app.service.usuario;

import animatch.app.api.configuration.security.jwt.GerenciadorTokenJwt;
import animatch.app.domain.lista.repository.ListaRepository;
import animatch.app.domain.usuario.Usuario;
import animatch.app.domain.usuario.repository.UsuarioRepository;
import animatch.app.dto.UsuarioImportacaoDTO;
import animatch.app.service.lista.ListaService;
import animatch.app.service.usuario.autenticacao.AutenticacaoService;
import animatch.app.service.usuario.autenticacao.AutenticacaoService;
import animatch.app.service.usuario.autenticacao.dto.UsuarioTokenDTO;
import animatch.app.service.usuario.dto.UsuarioAtualizarDto;
import animatch.app.service.usuario.dto.UsuarioCadastrarDTO;
import animatch.app.service.usuario.dto.UsuarioLoginDTO;
import animatch.app.service.usuario.dto.UsuarioMapper;
import animatch.app.service.usuario.mapper.UsuarioRelatorioMapper;
import animatch.app.utils.GerenciadorArquivoTxt;
import animatch.app.utils.GerenciadorDeArquivo;
import animatch.app.utils.ListaObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private ListaRepository listaRepository;
    @Autowired
    private ListaService listaService;
    @Autowired
    private AutenticacaoService usuarioAutorizacaoService;

    public void verificarUsuarioExiste(int userId) {
        if (!repository.existsById(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
    }

    public ResponseEntity<Usuario> criar(UsuarioCadastrarDTO usuarioCadastrarDTO) {
        final Usuario novoUsuario = UsuarioMapper.of(usuarioCadastrarDTO);
        if (this.repository.existsByEmail(novoUsuario.getEmail())) {
            return ResponseEntity.status(409).build();
        }

        String senhaCriptografada = passwordEncoder.encode(novoUsuario.getPassword());
        novoUsuario.setPassword(senhaCriptografada);

        repository.save(novoUsuario);
        listaService.adicionarListasDefault(novoUsuario.getId());
        return ResponseEntity.status(201).body(novoUsuario);
    }

    public ResponseEntity<UsuarioTokenDTO> autenticar(UsuarioLoginDTO login) {
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                login.getEmail(), login.getPassword());
        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Usuario usuarioAutenticado =
                repository.findByEmail(login.getEmail());

        if (usuarioAutenticado == null) {
            return ResponseEntity.status(403).build();
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = gerenciadorTokenJwt.generateToken(authentication);
        return ResponseEntity.status(200).body(UsuarioMapper.of(usuarioAutenticado, token));
    }


    public ResponseEntity<UsuarioTokenDTO> atualizar(UsuarioAtualizarDto usuarioAtualizar) {
        Usuario user = repository.findUserById(usuarioAtualizar.getId());

        if (user == null) {
            return ResponseEntity.status(404).build();
        }
        Usuario usuarioMapeado = UsuarioMapper.usuarioAtualizar(usuarioAtualizar, user);
        if (usuarioAtualizar.getPassword() != null) {
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
            repository.save(usuarioMapeado);

            final UsernamePasswordAuthenticationToken newCredentials = new UsernamePasswordAuthenticationToken(
                    usuarioMapeado.getEmail(), usuarioMapeado.getPassword());
            final Authentication newAuthentication = this.authenticationManager.authenticate(newCredentials);

            final String newToken = gerenciadorTokenJwt.generateToken(newAuthentication);
            listaService.adicionarListasDefault(usuarioAtualizar.getId());
            return ResponseEntity.status(200).body(UsuarioMapper.of(usuarioMapeado, newToken));

        } else {
            repository.save(usuarioMapeado);
            return ResponseEntity.status(200).body(UsuarioMapper.of(usuarioMapeado));
        }
    }

    public void gravarCsv() {
        List<Usuario> users = repository.findAll();
        List<Integer> qtds = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            Integer quantidade = repository.countQuantiadeListas(users.get(i).getId());
            qtds.add(quantidade);
        }
        ListaObj<Usuario> listaObj = new ListaObj<>(users.size());
        for (int i = 0; i < users.size(); i++) {
            listaObj.adiciona(users.get(i));
        }
        GerenciadorDeArquivo.gravaArquivoCsv(listaObj, "arquivoDeUsuarios", qtds);
    }

    public void gravarTxt() {
        List<Usuario> users = repository.findAll();
        if (users.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        List<UsuarioImportacaoDTO> usersDto = new ArrayList<>();
        UsuarioRelatorioMapper usuarioRelatorioMapper = new UsuarioRelatorioMapper();
        for (int i = 0; i < users.size(); i++) {
            usersDto.add(usuarioRelatorioMapper.paraDTO(users.get(i)));
            Integer quantidade = repository.countQuantiadeListas(users.get(i).getId());
            usersDto.get(i).setQuantidade(quantidade);
        }
        GerenciadorArquivoTxt.gravaArquivoTxt(usersDto, "usuarios");
    }

}
