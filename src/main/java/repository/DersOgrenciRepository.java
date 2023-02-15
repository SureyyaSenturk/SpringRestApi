package repository;

import entity.Dersogrenci;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Süreyya
 * @see {@link JpaRepository}
 */
public interface DersOgrenciRepository extends JpaRepository<Dersogrenci,Long> {
}
