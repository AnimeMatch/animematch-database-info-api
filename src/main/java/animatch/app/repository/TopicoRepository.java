package animatch.app.repository;

import animatch.app.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico,Integer> {
    Topico findTopicoById(int id);
}
