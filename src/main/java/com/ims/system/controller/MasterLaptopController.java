package com.ims.system.controller;

import com.ims.system.entity.MasterLaptop;
import com.ims.system.service.MasterLaptopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/laptops")
@CrossOrigin(origins = "*")
public class MasterLaptopController {

    private final MasterLaptopService masterLaptopService;

    public MasterLaptopController(MasterLaptopService masterLaptopService) {
        this.masterLaptopService = masterLaptopService;
    }

    @GetMapping
    public List<MasterLaptop> getAllLaptops() {
        return masterLaptopService.getAllLaptops();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MasterLaptop> getLaptopById(@PathVariable Long id) {
        return ResponseEntity.ok(masterLaptopService.getLaptopById(id));
    }

    @PostMapping
    public ResponseEntity<MasterLaptop> createLaptop(@RequestBody MasterLaptop laptop) {
        return ResponseEntity.ok(masterLaptopService.createLaptop(laptop));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MasterLaptop> updateLaptop(@PathVariable Long id, @RequestBody MasterLaptop laptopDetails) {
        return ResponseEntity.ok(masterLaptopService.updateLaptop(id, laptopDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLaptop(@PathVariable Long id) {
        masterLaptopService.deleteLaptop(id);
        return ResponseEntity.noContent().build();
    }
}

