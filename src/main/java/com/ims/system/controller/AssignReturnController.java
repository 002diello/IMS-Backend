package com.ims.system.controller;

import com.ims.system.entity.AssignRecord;
import com.ims.system.service.AssignRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
@CrossOrigin(origins = "*")
public class AssignReturnController {

    private final AssignRecordService assignRecordService;

    public AssignReturnController(AssignRecordService assignRecordService) {
        this.assignRecordService = assignRecordService;
    }

    @GetMapping
    public List<AssignRecord> getAllAssignments() {
        return assignRecordService.getAllAssignments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssignRecord> getAssignmentById(@PathVariable Long id) {
        return ResponseEntity.ok(assignRecordService.getAssignmentById(id));
    }

    @PostMapping
    public ResponseEntity<AssignRecord> createAssignment(@RequestBody AssignRecord record) {
        return ResponseEntity.ok(assignRecordService.createAssignment(record));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssignRecord> updateAssignment(@PathVariable Long id, @RequestBody AssignRecord details) {
        return ResponseEntity.ok(assignRecordService.updateAssignment(id, details));
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<AssignRecord> returnLaptop(@PathVariable Long id) {
        return ResponseEntity.ok(assignRecordService.returnLaptop(id));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long id) {
        assignRecordService.deleteAssignment(id);
        return ResponseEntity.noContent().build();
    }
}
