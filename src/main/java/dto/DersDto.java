package dto;
/**
 * Entity sınıflarıma Dto' lar üzerinden erişim sağlanacaktır.Taşıyıcı obje görevi görmektedir.
 * Dto sınıflarım güvenliği sağlamak için kullanılmaktadır.
 * ManytoOne ilişkili tablolar dto sınıflarında tanımlanmamaktadır.
 */

import lombok.*;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DersDto {
    private Long id;
    private String dersAdi;
    private String dersKodu;
    private Set<KonuDto> konular;
    private Set<DersOgrenciDto> dersOgrenciDtos;
}
