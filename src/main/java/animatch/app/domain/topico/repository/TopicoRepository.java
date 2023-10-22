package animatch.app.domain.topico.repository;

import animatch.app.domain.topico.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico,Integer> {
    Topico findTopicoById(int id);
}
