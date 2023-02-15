package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@Table(name = "Ogrenci")
@AllArgsConstructor
@NoArgsConstructor
public class Ogrenci {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
@Column(nullable = false,length = 40)
    private String ogrenciAd;
    @Column(nullable = false,length = 40)
private String soyad;
  @Column(unique = true)
    private int okulNo;
 @Column(length = 2)
  private int yas;

 @OneToMany(mappedBy = "ogrenci",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    private Set<Dersogrenci> dersOgrenciler;

}
