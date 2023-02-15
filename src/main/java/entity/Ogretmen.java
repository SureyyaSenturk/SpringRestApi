package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Ogretmen")
public class Ogretmen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false,length = 50)
    private String ogretmenAd;
    @Column(length = 2)
    private int yas;
    @Column(nullable = false,length = 50)
    private String brans;
    @Column(nullable = true)
    private boolean idareciMi;
@OneToMany(mappedBy = "ogretmen",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
private Set<Ders> dersler;


}
