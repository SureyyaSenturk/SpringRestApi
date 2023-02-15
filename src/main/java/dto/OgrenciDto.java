package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OgrenciDto {
    private long id;
    private String ogrenciAd;
    private String soyad;
    private int okulNo;
    private long yas;

    private Set<DersOgrenciDto> dersOgrenciDtos;
}
