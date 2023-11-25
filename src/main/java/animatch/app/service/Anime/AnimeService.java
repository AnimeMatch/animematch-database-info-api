package animatch.app.service.Anime;

import animatch.app.domain.anime.Anime;
import animatch.app.domain.anime.repository.AnimeRepository;
import animatch.app.service.Anime.dto.AnimeDadosComplementaresDto;
import animatch.app.service.Anime.dto.AnimeParaSalvarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimeService {
    @Autowired
    private AnimeRepository repository;
    public HttpStatus darLike(int idApi){
        Anime animeToChange = repository.findByIdApi(idApi);
        if (animeToChange == null) {
            Anime anime = construirAnime(idApi);
            anime.somarLikes();
            return HttpStatus.OK;
        }
        animeToChange.somarLikes();
        repository.save(animeToChange);
        return HttpStatus.OK;
    }

    public AnimeParaSalvarDto buscarAnime(int idApi){
        try {
            String url = "https://localhost:8081/animes/anime-para-salvar";
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForObject(
                    url,
                    AnimeParaSalvarDto.class,
                    idApi
            );
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime de id %d não encontrado".formatted(idApi));
        }
    }

    public Anime construirAnime(int idApi){
        try {
            AnimeParaSalvarDto animeRequested = buscarAnime(idApi);
            Anime anime = new Anime(
                    animeRequested.getIdApi(),
                    animeRequested.getNome(),
                    animeRequested.getNotaMedia(),
                    animeRequested.getImagem()
            );
            return anime;
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao contruir anime recebido pela API");
        }
    }

    public void salvarAnime(int idApi){
        Anime animeToSave = construirAnime(idApi);
        repository.save(animeToSave);
    }

    public AnimeDadosComplementaresDto dadosComplementares(int id){
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime de id %d não encontrado".formatted(id));
        }
        try{
            Integer like = repository.qtdDeslikesAnime(id);
            Integer deslike = repository.qtdLikesAnime(id);
            Integer assistido = repository.qtdAssistido(id);
            return new AnimeDadosComplementaresDto(
                    like,
                    deslike,
                    assistido);

        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ("houve um erro ao carregar dados de likes," +
                    " deslike e vezes assistido para o anime de id %d").formatted(id));
        }
    }

    public List<Anime> ordenarPelaNota(){
        List<Anime> animes = repository.findAll();
        for (int i = 0; i < animes.size() - 1; i++) {
            for (int j = 0; j < animes.size() - 1; j++) {
                if (animes.get(j).getNotaMedia() > animes.get(j+1).getNotaMedia()) {
                    var aux = animes.get(j);
                    animes.set(j,animes.get(j+1));
                    animes.set(j+1,aux);
                }
            }
        }
        return animes;
    }
}
