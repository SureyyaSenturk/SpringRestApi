package repository;

import entity.Ogrenci;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author Süreyya
 * @see {@link JpaRepository}
 */
public interface OgrenciRepository extends JpaRepository<Ogrenci,Long> {
}
