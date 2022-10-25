package com.example.springbootrentalcar.controller;

import com.example.springbootrentalcar.dto.VehicleDto;
import com.example.springbootrentalcar.service.VehicleService;
import com.example.springbootrentalcar.specifications.DateInterval;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/vehicle")
@CrossOrigin("http://localhost:4200")
@Log
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<VehicleDto> getVehicle() {
        List<VehicleDto> vehicleList = vehicleService.getAllVehicles();
        if(vehicleList == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(vehicleList, HttpStatus.OK);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<VehicleDto> getVehicle(@PathVariable("id") int id) {
        VehicleDto vehicleDto = vehicleService.getVehicleById(id);
        if(vehicleDto == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(vehicleDto, HttpStatus.OK);
        }
    }


    @RequestMapping(value = "/addOrUpdate", method = { RequestMethod.POST, RequestMethod.PUT })
    public void addOrUpdateUser(@Valid @RequestBody VehicleDto vehicleDto) {
        vehicleService.saveOrUpdateVehicle(vehicleDto);
    }

    @DeleteMapping("/remove/{id}")
    public void removeUser(@PathVariable("id") int id) {
        vehicleService.deleteVehicle(id);
    }

    @RequestMapping(value = "/free", method = { RequestMethod.POST, RequestMethod.PUT })
    public ResponseEntity<VehicleDto> getFreeVehicle(@RequestBody DateInterval dateInterval) {
        List<VehicleDto> freeVehicleInRange = vehicleService.getFreeVehicleInRange(dateInterval);
        if(freeVehicleInRange == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(freeVehicleInRange, HttpStatus.OK);
        }
    }

}
