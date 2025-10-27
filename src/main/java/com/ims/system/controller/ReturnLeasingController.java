package com.ims.system.controller;

import com.ims.system.entity.ReturnLeasing;
import com.ims.system.service.ReturnLeasingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/return-leasing")
@CrossOrigin(origins = "*")
public class ReturnLeasingController {

    private final ReturnLeasingService returnLeasingService;

    public ReturnLeasingController(ReturnLeasingService returnLeasingService) {
        this.returnLeasingService = returnLeasingService;
    }

    @GetMapping
    public List<ReturnLeasing> getAllReturns() {
        return returnLeasingService.getAllReturns();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReturnLeasing> getReturnById(@PathVariable Long id) {
        return ResponseEntity.ok(returnLeasingService.getReturnById(id));
    }

    @PostMapping
    public ResponseEntity<ReturnLeasing> createReturn(@RequestBody ReturnLeasing returnLeasing) {
        return ResponseEntity.ok(returnLeasingService.createReturn(returnLeasing));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReturnLeasing> updateReturn(@PathVariable Long id, @RequestBody ReturnLeasing details) {
        return ResponseEntity.ok(returnLeasingService.updateReturn(id, details));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReturn(@PathVariable Long id) {
        returnLeasingService.deleteReturn(id);
        return ResponseEntity.noContent().build();
    }
}

