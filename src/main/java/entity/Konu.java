package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Konu")
@AllArgsConstructor
@NoArgsConstructor
public class Konu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false,length = 50)
    private String konuAd;
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(foreignKey = @ForeignKey(name = "DERS_FK"))
private Ders ders;

}
