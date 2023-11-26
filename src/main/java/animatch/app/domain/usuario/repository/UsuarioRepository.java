package animatch.app.domain.usuario.repository;

import animatch.app.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    public boolean existsByEmail(String email);

    public boolean existsByPassword(String password);

    @Query("""
            select u
            from Usuario u
            where u.email = ?1 and u.password = ?2 and u.status = true
            """)
    Usuario findUserByEmailPasword(String email, String password);

    Usuario findUserById(int id);
    @Query("""
            select count(l.id)
            from Lista l
            where l.userId = ?1
            """)
    Integer countByUsuarioId(int usuarioId);
    Usuario findByEmail(String email);

    @Query("""
            select count(l) from Lista l
            where l.userId.id = ?1
            """)
    Integer countQuantiadeListas(Integer userId);
    List<Usuario> findAll();
    Usuario findById(int id);
}
