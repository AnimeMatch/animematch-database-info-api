package animatch.app.service.AnimeLista;

import animatch.app.domain.anime.repository.AnimeRepository;
import animatch.app.domain.animelista.AnimeLista;
import animatch.app.domain.animelista.repository.AnimeListaRepository;
import animatch.app.domain.lista.repository.ListaRepository;
import animatch.app.service.Anime.AnimeService;
import animatch.app.service.Anime.dto.AnimeParaSalvarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AnimeListaService {
    @Autowired
    AnimeListaRepository repository;
    @Autowired
    AnimeService animeService;
    @Autowired
    AnimeRepository animeRepository;
    @Autowired
    ListaRepository listaRepository;
    @Autowired
    AnimeListaRepository animeListaRepository;

    public void salvarAnimeLista(int idApi, int idLista){
        if (!listaRepository.existsById(idLista)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lista não encontrada");
        }
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
        try {
            AnimeLista animeLista = new AnimeLista(
                    animeRepository.findByIdApi(idApi),
                    listaRepository.findListaById(idLista));
            animeListaRepository.save(animeLista);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao adicionar anime a uma lista");
        }
    }
}
