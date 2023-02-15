package service.impl;

import dto.DersDto;
import entity.Ders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.DersRepository;
import service.DersService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class DersServiceImpl implements DersService {
    @Autowired
     private final DersRepository dersRepository;
    private final KonuServiceImpl konuService;
    private final DersOgrenciServiceImpl dersOgrenciService;

    public DersServiceImpl(DersRepository dersRepository, KonuServiceImpl konuService, DersOgrenciServiceImpl dersOgrenciService) {
        this.dersRepository = dersRepository;
        this.konuService = konuService;
        this.dersOgrenciService = dersOgrenciService;
    }


    @Override
    public List<DersDto> getAll() {
        List<DersDto> dersDtoList=new ArrayList<>();
        dersRepository.findAll().forEach(ders -> {
            DersDto dersDto=new DersDto();
            entityToDto(ders,dersDto);
            dersDtoList.add(dersDto);
        });
        return dersDtoList;
    }

    @Override
    public Optional<DersDto> getById(long id) {
        Optional<DersDto> dersDto=Optional.of(new DersDto());
        Optional<Ders> ders=dersRepository.findById(id);
        entityToDto(ders.orElseGet(Ders::new),dersDto.get());
        return dersDto;
    }

    @Override
    public DersDto add(DersDto dersDto) {
        Ders ders =new Ders();
        dtoToEntity(dersDto,ders);
        dersRepository.save(ders);
        entityToDto(ders,dersDto);
        return dersDto;
    }

    @Override
    public void delete(DersDto dersDto) {
        Ders ders=new Ders();
        dtoToEntity(dersDto,ders);
        dersRepository.delete(ders);
    }

    @Override
    public DersDto update(DersDto dersDto) {
        Optional<DersDto> optionalDersDto=Optional.of(getById(dersDto.getId()).orElseThrow());
        Ders ders=new Ders();
        dtoToEntity(optionalDersDto.get(),ders);
        entityToDto(dersRepository.save(ders),dersDto);
        return dersDto;
    }

    /**
     * Aktarım işlemi yapılmaktadır.Aynı alanlar farklı sınıflara aktarılıyor
     * Dto sınıfını Entity sınıfına aktarma işlemi yapılmaktadır.
     * @param dersDto
     * @param ders
     */
    protected void dtoToEntity(DersDto dersDto, Ders ders){
        ders.setId(dersDto.getId());
        ders.setDersAdi(dersDto.getDersAdi());
        ders.setDersKodu(dersDto.getDersKodu());

    }

    /**
     *  Aktarım işlemi yapılmaktadır.Aynı alanlar farklı sınıflara aktarılıyor
     *  Entity sınıfınıı Dto sınıfına aktarma işlemi yapılmaktadır.
     * @param ders
     * @param dersDto
     */
    protected void entityToDto(Ders ders,DersDto dersDto){
        dersDto.setId(ders.getId());
        dersDto.setDersAdi(ders.getDersAdi());
        dersDto.setDersKodu(ders.getDersKodu());
    }
}
