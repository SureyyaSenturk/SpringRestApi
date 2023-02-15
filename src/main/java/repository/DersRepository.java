package repository;

import entity.Ders;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author Süreyya
 * @see {@link JpaRepository}
 */
public interface DersRepository extends JpaRepository<Ders,Long> {
}
