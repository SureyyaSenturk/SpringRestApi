package repository;

import entity.Konu;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author Süreyya
 * @see {@link JpaRepository}
 */
public interface KonuRepository extends JpaRepository<Konu,Long> {
}
