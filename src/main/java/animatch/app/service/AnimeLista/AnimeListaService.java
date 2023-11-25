package animatch.app.service.AnimeLista;

import animatch.app.domain.anime.Anime;
import animatch.app.domain.anime.repository.AnimeRepository;
import animatch.app.domain.animelista.AnimeLista;
import animatch.app.domain.animelista.repository.AnimeListaRepository;
import animatch.app.domain.lista.repository.ListaRepository;
import animatch.app.domain.usuario.repository.UsuarioRepository;
import animatch.app.service.Anime.AnimeService;
import animatch.app.service.Anime.dto.AnimeInfoDTO;
import animatch.app.service.AnimeLista.dto.AnimeListaInfoDTO;
import animatch.app.service.AnimeLista.dto.AnimeListaInfoDadosBrutosDto;
import animatch.app.service.usuario.UsuarioService;
import animatch.app.utils.ListaObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimeListaService {
    @Autowired
    private AnimeListaRepository repository;
    @Autowired
    private AnimeService animeService;
    @Autowired
    private AnimeRepository animeRepository;
    @Autowired
    private ListaRepository listaRepository;
    @Autowired
    private AnimeListaRepository animeListaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioService usuarioService;

    public void verificarListaExiste(int idLista){
        if (!listaRepository.existsById(idLista)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lista não encontrada");
        }
    }

    public void salvarAnimeLista(int idApi, int idLista){
        this.verificarListaExiste(idLista);
        if (animeRepository.existsByIdApi(idApi)){
            salvarAnimeListaNoBanco(idApi, idLista);
        } else {
            try{
                animeService.salvarAnime(idApi);
                salvarAnimeListaNoBanco(idApi, idLista);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao busca anime de id %d".formatted(idApi));
            }
        }
    }

    public void salvarAnimeListaNoBanco(int idApi, int idLista){
        this.verificarListaExiste(idLista);
        try {
            AnimeLista animeLista = new AnimeLista(
                    animeRepository.findByIdApi(idApi),
                    listaRepository.findListaById(idLista));
            animeListaRepository.save(animeLista);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao adicionar anime a uma lista");
        }
    }

    public ListaObj<Anime> vetorDeAnimes(){
        List<Anime> animes = repository.findAllInfo();
        ListaObj<Anime> lista = new ListaObj<Anime>(animes.size());
        for (int i = 0; i < animes.size(); i++) {
            lista.adiciona(animes.get(i));
        }
        return lista;
    }

    public List<AnimeLista> animeListaPorUsuario(int userId){
        usuarioService.verificarUsuarioExiste(userId);
        List<AnimeListaInfoDTO> animes = repository.findAllAnimeListaInfoByUserId(userId);
        List<AnimeLista> dados = new ArrayList<>();
        for (AnimeListaInfoDTO a: animes) {
            AnimeLista anime = new AnimeLista(a.getAnimeId(), a.getListaId());
            dados.add(anime);
        }

        return dados;
    }

    public List<AnimeLista> animeListaPorUsuarioPaginado(int userId, int paginacao){
        usuarioService.verificarUsuarioExiste(userId);
        Pageable pageable = PageRequest.of(0, paginacao);
        List<AnimeListaInfoDTO> animes = repository.findAllAnimeListaInfoByUserIdPaginacao(
                usuarioRepository.findUserById(userId),
                pageable);
        List<AnimeLista> dados = new ArrayList<>();
        for (AnimeListaInfoDTO a: animes) {
            AnimeLista anime = new AnimeLista(a.getAnimeId(), a.getListaId());
            dados.add(anime);
        }
        return dados;
    }

    public ListaObj<Anime> receberAnimesDeUmaLista(int listaId){
        this.verificarListaExiste(listaId);
        List<Anime> animes = repository.findAllAnimeInfoByListaId(listaId);
        ListaObj<Anime> lista = new ListaObj(animes.size());
        for (int i = 0; i < animes.size(); i++) {
            lista.adiciona(animes.get(i));
        }
        return lista;
    }

    public ListaObj<Anime> receberAnimesDeUmaListaPaginado(int listaId, int paginacao){
        verificarListaExiste(listaId);
        Pageable pageable = PageRequest.of(0, paginacao);
        List<Anime> animes = repository.findAllAnimePaginadoInfoByListaId(listaId, pageable);
        ListaObj<Anime> lista = new ListaObj(animes.size());
        for (int i = 0; i < animes.size(); i++) {
            lista.adiciona(animes.get(i));
        }
        return lista;
    }

}
