package controller;

import dto.OgretmenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.OgretmenService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ogretmen")
public class OgretmenController {
    @Autowired
    private final OgretmenService ogretmenService;

    public OgretmenController(OgretmenService ogretmenService) {
        this.ogretmenService = ogretmenService;
    }
    @GetMapping("/getall")
    public List<OgretmenDto> getAll(){
        return ogretmenService.getAll();
    }
    @GetMapping("getbyid/{id}")
    public Optional<OgretmenDto>getById(@PathVariable long id){
        return ogretmenService.getById(id);
    }
    @PostMapping("/add")
    public OgretmenDto add(@RequestBody OgretmenDto ogretmenDto){
        return ogretmenService.add(ogretmenDto);
    }
    @DeleteMapping("/delete")
    public void delete(@RequestBody OgretmenDto ogretmenDto){
        ogretmenService.delete(ogretmenDto);
    }
    @PutMapping("/update")
    public OgretmenDto update(@RequestBody OgretmenDto ogretmenDto){
        return ogretmenService.update(ogretmenDto);
    }
}
