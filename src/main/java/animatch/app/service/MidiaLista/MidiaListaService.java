package animatch.app.service.MidiaLista;

import animatch.app.domain.midia.Midia;
import animatch.app.domain.midia.repository.MidiaRepository;
import animatch.app.domain.midialista.MidiaLista;
import animatch.app.domain.midialista.repository.MidiaListaRepository;
import animatch.app.domain.lista.repository.ListaRepository;
import animatch.app.domain.usuario.repository.UsuarioRepository;
import animatch.app.service.Midia.MidiaService;
import animatch.app.service.lista.dto.ListaInfoDTO;
import animatch.app.service.usuario.UsuarioService;
import animatch.app.utils.ListaObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MidiaListaService {
    @Autowired
    private MidiaListaRepository repository;
    @Autowired
    private MidiaService midiaService;
    @Autowired
    private MidiaRepository midiaRepository;
    @Autowired
    private ListaRepository listaRepository;
    @Autowired
    private MidiaListaRepository midiaListaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioService usuarioService;

    public void verificarListaExiste(int idLista){
        if (!listaRepository.existsById(idLista)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lista não encontrada");
        }
    }

    public void salvarMidiaLista(int idApi, int idLista){
        this.verificarListaExiste(idLista);
        if (midiaRepository.existsByIdApi(idApi)){
            salvarMidiaListaNoBanco(idApi, idLista);
        } else {
            try{
                midiaService.adicionarAoBanco(idApi);
                salvarMidiaListaNoBanco(idApi, idLista);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao busca midia de id %d".formatted(idApi));
            }
        }
    }

    public void salvarMidiaListaNoBanco(int idApi, int idLista){
        this.verificarListaExiste(idLista);
        try {
            if(!midiaListaRepository.existsMidiaListaByListaIdAndMidiaId(listaRepository.findListaById(idLista),midiaRepository.findByIdApi(idApi))){
            MidiaLista midiaLista = new MidiaLista(
                    midiaRepository.findByIdApi(idApi),
                    listaRepository.findListaById(idLista));
            midiaListaRepository.save(midiaLista);

            }
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao adicionar midia a uma lista");
        }
    }

    public List<MidiaLista> receberMidias(){
//        return repository.findAllInfo();
        return repository.findAll();
    }
    public ListaObj<Midia> vetorDeMidias(){
        List<Midia> midias = repository.findAllInfo();
        ListaObj<Midia> lista = new ListaObj<Midia>(midias.size());
        for (int i = 0; i < midias.size(); i++) {
            lista.adiciona(midias.get(i));
        }
        return lista;
    }

    public List<MidiaLista> midiaListaPorUsuario(String email){
        usuarioService.verificarUsuarioExistePorEmail(email);
        List<MidiaLista> midias = repository.findAllMidiaListaInfoByEmail(email);
        return midias;
    }

    public List<MidiaLista> midiaListaPorUsuarioPaginado(String email, int paginacao){
        usuarioService.verificarUsuarioExistePorEmail(email);
        Pageable pageable = PageRequest.of(0, paginacao);
        List<MidiaLista> midias = repository.findAllMidiaListaInfoByEmailPaginacao(
                email,
                pageable);
        return midias;
    }

    public List<Midia> receberMidiasDeUmaLista(int listaId){

        return repository.findAllMidiaInfoByListaId(listaId);

    }

    public List<MidiaLista> animesWithAssociativeId(int listaID){
        return repository.findAllMidiaWithAssociativeId(listaID);
    }

    public ListaObj<Midia> receberMidiasDeUmaListaVetor(int listaId){
        this.verificarListaExiste(listaId);
        List<Midia> midias = repository.findAllMidiaInfoByListaId(listaId);
        ListaObj<Midia> lista = new ListaObj(midias.size());
        for (int i = 0; i < midias.size(); i++) {
            lista.adiciona(midias.get(i));
        }
        return lista;
    }

    public List<Midia> recebreMidiasDeUmaListaPaginado(int listaId, int paginacao){
        Pageable pageable = PageRequest.of(0, paginacao);
        return repository.findAllMidiaPaginadoInfoByListaId(listaId, pageable);
    }

    public ListaObj<Midia> receberMidiasDeUmaListaPaginadoVetor(int listaId, int paginacao){
        verificarListaExiste(listaId);
        Pageable pageable = PageRequest.of(0, paginacao);
        List<Midia> midias = repository.findAllMidiaPaginadoInfoByListaId(listaId, pageable);
        ListaObj<Midia> lista = new ListaObj(midias.size());
        for (int i = 0; i < midias.size(); i++) {
            lista.adiciona(midias.get(i));
        }
        return lista;
    }

    public ResponseEntity adicionarFavorito(int idApi, String email){
        try {
            if (!repository.existsFavorito(idApi, email)) {
                this.salvarMidiaLista(idApi, repository.findListaFavoritoByEmail(email).getId());
                return ResponseEntity.status(200).build();
            }
            return ResponseEntity.status(409).build();
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "%s".formatted(e));
        }
    }

}
