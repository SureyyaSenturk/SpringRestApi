package service.impl;

import dto.DersOgrenciDto;
import entity.Dersogrenci;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.DersOgrenciRepository;
import service.DersOgrenciService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class DersOgrenciServiceImpl implements DersOgrenciService {
  @Autowired
    private final DersOgrenciRepository dersOgrenciRepository;  //dependency injection

    public DersOgrenciServiceImpl(DersOgrenciRepository dersOgrenciRepository) {
        this.dersOgrenciRepository = dersOgrenciRepository;
    }

    @Override
    public List<DersOgrenciDto> getAll() {
List<DersOgrenciDto> dersOgrenciDtoList=new ArrayList<>();
dersOgrenciRepository.findAll().forEach(Dersogrenci->{
    DersOgrenciDto dersOgrenciDto=new DersOgrenciDto();
    entityToDto(Dersogrenci,dersOgrenciDto);
    dersOgrenciDtoList.add(dersOgrenciDto);

});
        return dersOgrenciDtoList;
    }

    @Override
    public Optional<DersOgrenciDto> getById(long id) {
        Optional<DersOgrenciDto> dersOgrenciDto=Optional.of(new DersOgrenciDto());
        Optional<Dersogrenci> dersogrenci=dersOgrenciRepository.findById(id);
        entityToDto(dersogrenci.orElseGet(Dersogrenci::new),dersOgrenciDto.get());
        return dersOgrenciDto;
    }

    @Override
    public DersOgrenciDto add(DersOgrenciDto dersOgrenciDto) {
        Dersogrenci dersogrenci=new Dersogrenci();
        dtoToEntity(dersOgrenciDto,dersogrenci);
        dersogrenci=dersOgrenciRepository.save(dersogrenci);
        entityToDto(dersogrenci,dersOgrenciDto);
        return dersOgrenciDto;
    }

    @Override
    public void delete(DersOgrenciDto dersOgrenciDto) {
     Dersogrenci dersogrenci=new Dersogrenci();
     dtoToEntity(dersOgrenciDto,dersogrenci);
     dersOgrenciRepository.delete(dersogrenci);
    }

    @Override
    public DersOgrenciDto update(DersOgrenciDto dersOgrenciDto) {
        Optional<DersOgrenciDto> optionalDersOgrenciDto=Optional.of(getById(dersOgrenciDto.getId()).orElseThrow());
        Dersogrenci dersogrenci=new Dersogrenci();
        dtoToEntity(optionalDersOgrenciDto.get(),dersogrenci);
        entityToDto(dersOgrenciRepository.save(dersogrenci),dersOgrenciDto);
        return dersOgrenciDto;
    }

    /**
     * Aktarım işlemi yapılmaktadır.Aynı alanlar farklı sınıflara aktarılıyor
     * Dto sınıfını Entity sınıfına aktarma işlemi yapılmaktadır.
     * @param dersOgrenciDto
     * @param dersogrenci
     */
    protected void dtoToEntity(DersOgrenciDto dersOgrenciDto,Dersogrenci dersogrenci){
        dersogrenci.setId(dersOgrenciDto.getId());
        dersogrenci.setDevamsizlik(dersOgrenciDto.getDevamsizlik());
        dersogrenci.setNot(dersOgrenciDto.getNot());

    }

    /**
     *  Aktarım işlemi yapılmaktadır.Aynı alanlar farklı sınıflara aktarılıyor
     *  Entity sınıfınıı Dto sınıfına aktarma işlemi yapılmaktadır.
     * @param dersogrenci
     * @param dersOgrenciDto
     */
    protected void entityToDto(Dersogrenci dersogrenci,DersOgrenciDto dersOgrenciDto){
        dersOgrenciDto.setId(dersogrenci.getId());
        dersOgrenciDto.setDevamsizlik(dersogrenci.getDevamsizlik());
        dersOgrenciDto.setNot(dersOgrenciDto.getNot());
    }
}
