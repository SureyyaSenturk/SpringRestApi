package controller;

import dto.OgrenciDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.OgrenciService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ogrenci")
public class OgrenciController {
   @Autowired
  private final  OgrenciService ogrenciService;

    public OgrenciController(OgrenciService ogrenciService) {
        this.ogrenciService = ogrenciService;
    }
@GetMapping("/getall")
    public List<OgrenciDto> getAll(){
        return ogrenciService.getAll();
    }
@GetMapping("/getbyid/{id}")
    public Optional<OgrenciDto> getById(@PathVariable long id){
        return ogrenciService.getById(id);
    }
    @PostMapping("/add")
    public void add(@RequestBody OgrenciDto ogrenciDto){
        ogrenciService.add(ogrenciDto);
    }
    @DeleteMapping("/delete")
    public void delete(@RequestBody OgrenciDto ogrenciDto){
        ogrenciService.delete(ogrenciDto);
    }
    @PutMapping("/update")
    public void update(@RequestBody OgrenciDto ogrenciDto){
        ogrenciService.update(ogrenciDto);
    }
}
