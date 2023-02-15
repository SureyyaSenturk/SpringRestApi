package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OgretmenDto {

    private long id;
    private String ogretmenAd;
    private int yas;
    private String brans;
    private boolean idareciMi;
    private Set<DersDto> dersDtos;
}
