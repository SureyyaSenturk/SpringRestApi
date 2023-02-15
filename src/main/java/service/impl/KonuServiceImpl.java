package service.impl;

import dto.KonuDto;
import entity.Konu;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.KonuRepository;
import service.KonuService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class KonuServiceImpl implements KonuService {
@Autowired
    private final KonuRepository konuRepository;

    public KonuServiceImpl(KonuRepository konuRepository) {
        this.konuRepository = konuRepository;
    }

    @Override
    public List<KonuDto> getAll() {
        List<KonuDto> konuDtoList=new ArrayList<>();
        konuRepository.findAll().forEach(konu -> {
            KonuDto konuDto=new KonuDto();
            BeanUtils.copyProperties(konu,konuDto);
            entityToDto(konu,konuDto);
            konuDtoList.add(konuDto);

        });
        return konuDtoList;
    }

    @Override
    public Optional<KonuDto> getById(long id) {
        Optional<KonuDto> konuDto=Optional.of(new KonuDto());
        Konu konu=konuRepository.findById(id).orElseThrow(()->new RuntimeException("Konu Bulunamadı!"));
        BeanUtils.copyProperties(konu,konuDto);
        entityToDto(konu,konuDto.get());
        return konuDto;
    }

    @Override
    public KonuDto add(KonuDto konuDto) {
        Konu konu = new Konu();
        BeanUtils.copyProperties(konuDto,konu);
        dtoToEntity(konuDto,konu);
        konuRepository.save(konu);
        entityToDto(konu,konuDto);
        return konuDto;
    }

    @Override
    public void delete(KonuDto konuDto) {
Konu konu=new Konu();
dtoToEntity(konuDto,konu);
konuRepository.delete(konu);
    }

    @Override
    public KonuDto update(KonuDto konuDto) {
        Optional<KonuDto>optionalKonuDto=Optional.of(getById(konuDto.getId()).orElseThrow());
        optionalKonuDto.get().setKonuAd(konuDto.getKonuAd());
        Konu konu=new Konu();
        dtoToEntity(konuDto,konu);
        konu=konuRepository.save(konu);
        entityToDto(konu,konuDto);
        return konuDto;
    }

    /**
     * Aktarım işlemi yapılmaktadır.Aynı alanlar farklı sınıflara aktarılıyor
     * Dto sınıfını Entity sınıfına aktarma işlemi yapılmaktadır.
     * @param konuDto
     * @param konu
     */
    protected void dtoToEntity(KonuDto konuDto, Konu konu){
        konu.setId(konuDto.getId());
        konu.setKonuAd(konuDto.getKonuAd());
    }

    /**
     * Aktarım işlemi yapılmaktadır.Aynı alanlar farklı sınıflara aktarılıyor
     * Dto sınıfını Entity sınıfına aktarma işlemi yapılmaktadır.
     * @param konu
     * @param konuDto
     */
    protected void entityToDto(Konu konu,KonuDto konuDto){
        konuDto.setId(konu.getId());
        konuDto.setKonuAd(konu.getKonuAd());
    }

}
