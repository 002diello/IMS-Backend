package com.ims.system.controller;

import com.ims.system.entity.RepairRecord;
import com.ims.system.service.RepairRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repairs")
@CrossOrigin(origins = "*")
public class RepairRecordController {

    private final RepairRecordService repairRecordService;

    public RepairRecordController(RepairRecordService repairRecordService) {
        this.repairRecordService = repairRecordService;
    }

    @GetMapping
    public List<RepairRecord> getAllRepairs() {
        return repairRecordService.getAllRepairs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepairRecord> getRepairById(@PathVariable Long id) {
        return ResponseEntity.ok(repairRecordService.getRepairById(id));
    }

    @PostMapping
    public ResponseEntity<RepairRecord> createRepair(@RequestBody RepairRecord record) {
        return ResponseEntity.ok(repairRecordService.createRepair(record));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RepairRecord> updateRepair(@PathVariable Long id, @RequestBody RepairRecord details) {
        return ResponseEntity.ok(repairRecordService.updateRepair(id, details));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRepair(@PathVariable Long id) {
        repairRecordService.deleteRepair(id);
        return ResponseEntity.noContent().build();
    }
}

