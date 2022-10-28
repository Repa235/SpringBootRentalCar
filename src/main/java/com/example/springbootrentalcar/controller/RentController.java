package com.example.springbootrentalcar.controller;

import com.example.springbootrentalcar.dto.RentDto;
import com.example.springbootrentalcar.service.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/rent")
public class RentController {
    private final RentService rentService;

    @GetMapping
    public ResponseEntity<RentDto> totalRents() {
        List<RentDto> allRents = rentService.getAllRents();
        return new ResponseEntity(allRents, HttpStatus.OK);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<RentDto> getRent(@PathVariable("id") int id) {
        RentDto rentDto = rentService.getRentById(id);
        if (rentDto == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(rentDto, HttpStatus.OK);
        }
    }

    @GetMapping("/rentsOf/{id}")
    public ResponseEntity<RentDto> getRentsOf(@PathVariable("id") int id) {
        List<RentDto> rentDto = rentService.getRentsByUserId(id);
        if (rentDto == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(rentDto, HttpStatus.OK);
        }
    }


    @RequestMapping(value = "/addOrUpdate", method = {RequestMethod.POST, RequestMethod.PUT})
    public void addOrUpdateUser(@Valid @RequestBody RentDto rentDto) {
        rentService.saveOrUpdateRent(rentDto);
    }

    @DeleteMapping("/remove/{id}")
    public void removeUser(@PathVariable("id") int id) {
        rentService.deleteRent(id);
    }


}
