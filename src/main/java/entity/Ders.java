package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

/**
 * @author Süreyya
 */
@Entity
@Data
@Table(name = "Ders",schema = "springboot")
@NoArgsConstructor
@AllArgsConstructor
public class Ders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false,length = 30)
    private String dersAdi;
    @Column(nullable = false,unique = true)
    private String dersKodu;

@OneToMany(mappedBy ="ders",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
//@ToString.Exclude  //Data anatasyonu kullanıldığı için tekrar kullanmaya gerek yok. Data anatasyonu içerisinde barındırır.
    private Set<Konu> konular;

@OneToMany(mappedBy ="ders",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
private Set<Dersogrenci> dersOgrenciler;
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(foreignKey = @ForeignKey(name = "OGRETMEN_FK"))
private Ogretmen ogretmen;

}
