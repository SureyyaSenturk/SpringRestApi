package controller;

import dto.DersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.DersService;

import java.util.List;
import java.util.Optional;

@RestController   //Controlller sınıfı olduğunu bu anatasyon ile belirttim.
@RequestMapping("/api/ders")
public class DersController {
    @Autowired
    DersService dersService;
    @GetMapping("/getall")
    public List<DersDto> getAll(){
        return dersService.getAll();
    }
    @GetMapping("/getbyid/{id}")
    public Optional<DersDto> getById(@PathVariable long id){
        return dersService.getById(id);
    }
    @PostMapping("/add")
    public void add(@RequestBody DersDto dersDto){
        dersService.add(dersDto);
    }
    @DeleteMapping("/delete")
    public void delete(@RequestBody DersDto dersDto){
        dersService.delete(dersDto);
    }
@PutMapping("/update")
    public void update(@RequestBody DersDto dersDto){
        dersService.update(dersDto);
    }
}

