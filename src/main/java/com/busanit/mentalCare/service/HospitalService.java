package com.busanit.mentalCare.service;

import com.busanit.mentalCare.model.Hospital;
import com.busanit.mentalCare.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    public List<Hospital> getAllHospital() {
        return hospitalRepository.findAll();
    }
    public Hospital getHospitalById(String hospitalId) {
        return hospitalRepository.findById(hospitalId).orElse(null);
    }

}
