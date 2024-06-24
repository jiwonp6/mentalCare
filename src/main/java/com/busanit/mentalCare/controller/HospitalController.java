package com.busanit.mentalCare.controller;

import com.busanit.mentalCare.model.Hospital;
import com.busanit.mentalCare.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/hospitals")
public class HospitalController {

    @Autowired
    private HospitalRepository hospitalRepository;

    @PostMapping
    public Hospital createHospital(@RequestBody Hospital hospital) {
        return hospitalRepository.save(hospital);
    }
    @GetMapping
    public List<Hospital> getAllHospital() { return  hospitalRepository.findAll(); }

    @GetMapping("/{id2}")
    public Hospital getHospitalById(@PathVariable String hospitalId) {
        return hospitalRepository.findById(hospitalId).orElse(null);
    }


}
