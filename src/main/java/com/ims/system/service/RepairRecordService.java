package com.ims.system.service;

import com.ims.system.entity.RepairRecord;
import com.ims.system.exception.ResourceNotFoundException;
import com.ims.system.repository.RepairRecordRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RepairRecordService {

    private final RepairRecordRepository repairRecordRepository;

    public RepairRecordService(RepairRecordRepository repairRecordRepository) {
        this.repairRecordRepository = repairRecordRepository;
    }

    public List<RepairRecord> getAllRepairs() {
        return repairRecordRepository.findAll();
    }

    public RepairRecord getRepairById(Long id) {
        return repairRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Repair record not found with id " + id));
    }

    public RepairRecord createRepair(RepairRecord record) {
        return repairRecordRepository.save(record);
    }

    public RepairRecord updateRepair(Long id, RepairRecord details) {
        RepairRecord record = getRepairById(id);
        record.setEntity(details.getEntity());
        record.setDate(details.getDate());
        record.setModel(details.getModel());
        record.setSerialNumber(details.getSerialNumber());
        record.setPcId(details.getPcId());
        record.setPartReplaced(details.getPartReplaced());
        record.setPo(details.getPo());
        record.setStatus(details.getStatus());  // Add status update
        record.setRemarks(details.getRemarks());
        return repairRecordRepository.save(record);
    }

    public void deleteRepair(Long id) {
        repairRecordRepository.deleteById(id);
    }
}

