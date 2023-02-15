package service.impl;

import dto.DersOgrenciDto;
import dto.OgrenciDto;
import entity.Dersogrenci;
import entity.Ogrenci;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import repository.OgrenciRepository;
import service.OgrenciService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OgrenciServiceImpl implements OgrenciService {
@Autowired
    private final OgrenciRepository ogrenciRepository;
private final DersOgrenciServiceImpl dersOgrenciService;

    public OgrenciServiceImpl(OgrenciRepository ogrenciRepository, DersOgrenciServiceImpl dersOgrenciService) {
        this.ogrenciRepository = ogrenciRepository;
        this.dersOgrenciService = dersOgrenciService;
    }

    @Override
    public List<OgrenciDto> getAll() {
        List<OgrenciDto> ogrenciDtoList=new ArrayList<>();
        List<OgrenciDto> finalOgrenciDtoList=ogrenciDtoList;
        ogrenciRepository.findAll().forEach(ogrenci -> {
            OgrenciDto ogrenciDto=new OgrenciDto();
            entityToDto(ogrenci,ogrenciDto);
            finalOgrenciDtoList.add(ogrenciDto);
        });
        /*
         *Burada  filtreleme işlemi yapılmaktadır.
         * Listemizde numarası 1  olan öğrencilerden birinci sıradaki kaydı getirmektedir.
         * Bulamadığı zaman run time exception fırlatmaktadır.
         * */
        ogrenciDtoList.stream().filter(ogrenciDto -> ogrenciDto.getOkulNo()==1).findFirst().orElseThrow(()->new RuntimeException("ÖĞRENCİ BULUNAMADI!"));
        /**
         * Öğrenci listesinde adı Ali olan kayıtlara göre filtreleme işlemi yapılmaktadır.
         * Dönen Ali ismindeki kişileri tekrar filtreleme yaparak Alilerin içerisinde dersOgrenci listesinde devamsızlığı
         * 0'dan büyük olan kayıtlar tekrar listelenmiş oldu.
         * Bu koşullar ile eşleşen bütün kayıtları collect ile listeye dönüştürüldü.
         */
        ogrenciDtoList=finalOgrenciDtoList.stream()
                .filter(ogrenciDto -> ogrenciDto.getOgrenciAd().equalsIgnoreCase("Ali"))
                //   .filter(ogrenciDto -> ogrenciDto.getYas()==25 // Burada adı Ali olan 25 yaşındaki vatandaşlar filtrelenmektedir..
                .filter(ogrenciDto -> ogrenciDto.getDersOgrenciDtos().stream()
                        .filter(dersOgrenciDto -> dersOgrenciDto.getDevamsizlik()>0)
                        .allMatch(dersOgrenciDto -> dersOgrenciDto.getDevamsizlik()>0))
                .collect(Collectors.toList());
        return ogrenciDtoList;
    }

    @Override
    public Optional<OgrenciDto> getById(long id) {
        OgrenciDto ogrenciDto=new OgrenciDto();
        entityToDto(ogrenciRepository.findById(id).orElseThrow(()-> new RuntimeException("ÖĞRENCİ BULUNAMADI.")),ogrenciDto);
        return Optional.of(ogrenciDto);
    }

    @Override
    public OgrenciDto add(OgrenciDto ogrenciDto) {
        Ogrenci ogrenci=new Ogrenci();
      //  BeanUtils.copyProperties(ogrenciDto,ogrenci);
        dtoToEntity(ogrenciDto,ogrenci);
        ogrenci=ogrenciRepository.save(ogrenci);
        entityToDto(ogrenci,ogrenciDto);
        return ogrenciDto;
    }

    @Override
    public void delete(OgrenciDto ogrenciDto) {
        Ogrenci ogrenci=new Ogrenci();
        dtoToEntity(ogrenciDto,ogrenci);
        ogrenciRepository.delete(ogrenci);

    }

    @Override
    public OgrenciDto update(OgrenciDto ogrenciDto) {
        Optional<OgrenciDto> ogrenciDtoOptional=Optional.of(getById(ogrenciDto.getId()).orElseThrow());
        ogrenciDto.setId(ogrenciDtoOptional.get().getId());
        Ogrenci ogrenci=new Ogrenci();
        entityToDto(ogrenciRepository.save(ogrenci),ogrenciDto);
        return ogrenciDto;
    }

    /**
     * Aktarım işlemi yapılmaktadır.Aynı alanlar farklı sınıflara aktarılıyor
     * Dto sınıfını Entity sınıfına aktarma işlemi yapılmaktadır.
     * @param ogrenciDto
     * @param ogrenci
     */

    public void dtoToEntity(OgrenciDto ogrenciDto, Ogrenci ogrenci){
        ogrenci.setId(ogrenciDto.getId());
        ogrenci.setOgrenciAd(ogrenciDto.getOgrenciAd());
        ogrenci.setSoyad(ogrenciDto.getSoyad());
        ogrenci.setOkulNo( ogrenciDto.getOkulNo());
        ogrenci.setYas(ogrenci.getYas());

        if (!CollectionUtils.isEmpty(ogrenciDto.getDersOgrenciDtos())){
            ogrenciDto.getDersOgrenciDtos().forEach(dersOgrenciDto -> {
                Dersogrenci dersogrenci=new Dersogrenci();
                dersOgrenciService.dtoToEntity(dersOgrenciDto,dersogrenci);

                if(CollectionUtils.isEmpty(ogrenci.getDersOgrenciler()))
                    ogrenci.setDersOgrenciler(new HashSet<>());;
                    dersogrenci.setOgrenci(ogrenci);
                    ogrenci.getDersOgrenciler().add(dersogrenci);
            });
        }

    }

    /**
     * Aktarım işlemi yapılmaktadır.Aynı alanlar farklı sınıflara aktarılıyor
     * Dto sınıfını Entity sınıfına aktarma işlemi yapılmaktadır.
     * @param ogrenci
     * @param ogrenciDto
     */

    public  void entityToDto(Ogrenci ogrenci,OgrenciDto ogrenciDto){
ogrenciDto.setId(ogrenci.getId());
ogrenciDto.setOgrenciAd(ogrenci.getOgrenciAd());
ogrenciDto.setSoyad(ogrenci.getSoyad());
ogrenciDto.setOkulNo(ogrenci.getOkulNo());
ogrenciDto.setYas(ogrenci.getYas());
       if (!CollectionUtils.isEmpty(ogrenci.getDersOgrenciler()))
           ogrenci.getDersOgrenciler().forEach(dersogrenci -> {
               DersOgrenciDto dersOgrenciDto= new DersOgrenciDto();
               dersOgrenciService.entityToDto(dersogrenci,dersOgrenciDto);

               if (CollectionUtils.isEmpty(ogrenciDto.getDersOgrenciDtos()))
                   ogrenciDto.getDersOgrenciDtos().add(dersOgrenciDto);
           });

}

    }

