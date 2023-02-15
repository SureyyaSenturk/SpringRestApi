package controller;

import dto.KonuDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.KonuService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/konu")
public class KonuController {
    @Autowired
    KonuService konuService;
@GetMapping("/getall")
    ResponseEntity<List<KonuDto>> getAll(){
        return new ResponseEntity<>(konuService.getAll(),HttpStatus.OK);
    }
    @GetMapping("/getbyid/{id}")
    ResponseEntity<Optional<KonuDto>> getById(@PathVariable long id){
        return new ResponseEntity<>(konuService.getById(id), HttpStatus.OK);
    }
    @PostMapping("/add")
    KonuDto add(@RequestBody KonuDto konuDto){
    return konuService.add(konuDto);
    }
    @DeleteMapping("delete")
    void delete(@RequestBody KonuDto konuDto){
    konuService.delete(konuDto);
    }
@PutMapping("/update")
    void update(@RequestBody KonuDto konuDto){
    konuService.update(konuDto);
    }




}
