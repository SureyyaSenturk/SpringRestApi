package service.impl;

import dto.DersDto;
import dto.OgretmenDto;
import entity.Ders;
import entity.Ogretmen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import repository.OgrenciRepository;
import repository.OgretmenRepository;
import service.OgretmenService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
@Service
public class OgretmenServiceImpl implements OgretmenService {
    @Autowired
    private final OgretmenRepository ogretmenRepository;
    private final DersServiceImpl dersService;
    private final OgrenciRepository ogrenciRepository;

    public OgretmenServiceImpl(OgretmenRepository ogretmenRepository, DersServiceImpl dersService,
                               OgrenciRepository ogrenciRepository) {
        this.ogretmenRepository = ogretmenRepository;
        this.dersService = dersService;
        this.ogrenciRepository = ogrenciRepository;
    }

    @Override
    public List<OgretmenDto> getAll() {
        List<OgretmenDto>ogretmenDtoList=new ArrayList<>();
        ogretmenRepository.findAll().forEach(ogretmen -> {
            OgretmenDto ogretmenDto=new OgretmenDto();
            entityToDto(ogretmen,ogretmenDto);
            ogretmenDtoList.add(ogretmenDto);
        });
        return ogretmenDtoList;
    }

    @Override
    public Optional<OgretmenDto> getById(long id) {
        Optional<OgretmenDto> ogretmenDto=Optional.of(new OgretmenDto());
        Ogretmen ogretmen=ogretmenRepository.findById(id).orElseThrow(()->new RuntimeException("ÖĞRETMEN BULUNAMADI!"));
        entityToDto(ogretmen,ogretmenDto.get());
        return ogretmenDto;
    }

    @Override
    public OgretmenDto add(OgretmenDto ogretmenDto) {
        Ogretmen ogretmen=new Ogretmen();
        dtoToEntity(ogretmenDto,ogretmen);
        ogretmen=ogretmenRepository.save(ogretmen);
        entityToDto(ogretmen,ogretmenDto);
        return ogretmenDto;
    }

    @Override
    public void delete(OgretmenDto ogretmenDto) {
    Ogretmen ogretmen= new Ogretmen();
    dtoToEntity(ogretmenDto,ogretmen);
    dtoToEntity(ogretmenDto,ogretmen);
    ogretmenRepository.delete(ogretmen);
    }

    @Override
    public OgretmenDto update(OgretmenDto ogretmenDto){
        Optional<OgretmenDto>optionalOgretmenDto=Optional.of(getById(ogretmenDto.getId()).orElseThrow());
        optionalOgretmenDto.get().setOgretmenAd(ogretmenDto.getOgretmenAd());
        Ogretmen ogretmen=new Ogretmen();
        dtoToEntity(ogretmenDto,ogretmen);
        entityToDto(ogretmenRepository.save(ogretmen),ogretmenDto);

        return ogretmenDto;
    }
    public void dtoToEntity(OgretmenDto ogretmenDto, Ogretmen ogretmen){
        ogretmen.setId(ogretmenDto.getId());
        ogretmen.setOgretmenAd(ogretmenDto.getOgretmenAd());
        ogretmen.setBrans(ogretmenDto.getBrans());
        ogretmen.setYas(ogretmenDto.getYas());
        ogretmen.setIdareciMi(ogretmen.isIdareciMi());

        if (!CollectionUtils.isEmpty(ogretmenDto.getDersDtos())){
            ogretmenDto.getDersDtos().forEach(dersDto -> {
                Ders ders=new Ders();
                dersService.dtoToEntity(dersDto,ders);

                if (CollectionUtils.isEmpty(ogretmen.getDersler()))
                    ogretmen.setDersler(new HashSet<>());
                ders.setOgretmen(ogretmen);
                ogretmen.getDersler().add(ders);
            });
        }

    }
    public void entityToDto(Ogretmen ogretmen,OgretmenDto ogretmenDto){
     ogretmenDto.setId(ogretmen.getId());
     ogretmenDto.setOgretmenAd(ogretmen.getOgretmenAd());
     ogretmenDto.setBrans(ogretmen.getBrans());
     ogretmenDto.setYas(ogretmen.getYas());
     ogretmenDto.setIdareciMi(ogretmen.isIdareciMi());

     if (!CollectionUtils.isEmpty(ogretmen.getDersler()))
         ogretmen.getDersler().forEach(ders -> {
             DersDto dersDto=new DersDto();
             dersService.entityToDto(ders,dersDto);

             if (CollectionUtils.isEmpty(ogretmenDto.getDersDtos()))
                 ogretmenDto.setDersDtos(new HashSet<>());
             ogretmenDto.getDersDtos().add(dersDto);

         });
    }
}
