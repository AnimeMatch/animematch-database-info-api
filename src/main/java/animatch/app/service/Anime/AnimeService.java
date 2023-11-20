package animatch.app.service.Anime;

import animatch.app.domain.anime.Anime;
import animatch.app.domain.anime.repository.AnimeRepository;
import animatch.app.service.Anime.dto.AnimeParaSalvarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AnimeService {
    @Autowired
    AnimeRepository repository;
    public HttpStatus darLike(int idApi){
        Anime animeToChange = repository.findByIdApi(idApi);
        if (animeToChange == null) {
            Anime anime = salvarAnime(idApi);
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public Anime salvarAnime(int idApi){
        try {
            AnimeParaSalvarDto animeRequested = buscarAnime(idApi);
            Anime anime = new Anime(
                    animeRequested.getIdApi(),
                    animeRequested.getNotaMedia(),
                    animeRequested.getImagem()
            );
            return anime;
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
