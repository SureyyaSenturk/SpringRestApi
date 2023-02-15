package repository;


import entity.Ogretmen;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author Süreyya
 * @see {@link JpaRepository}
 */
public interface OgretmenRepository extends JpaRepository<Ogretmen,Long> {
}
