package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DersOgrenci")
@Check(constraints = "NOT<101") // Öğrenci notunun 100'den büyük olamaz sınırını koydum.
public class Dersogrenci {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
@Column(nullable = false,length = 2)
    private int devamsizlik;
@Column(nullable = false)
private int not;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "DERS_FK")) //foreign key'e isim vermek için.
    private Ders ders;
@ManyToOne
@JoinColumn(foreignKey = @ForeignKey(name = "OGRENCI_FK"))
    private Ogrenci ogrenci;
}
