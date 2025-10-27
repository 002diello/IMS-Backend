package com.ims.system.service;

import com.ims.system.entity.AssignRecord;
import com.ims.system.entity.MasterLaptop;
import com.ims.system.exception.ResourceNotFoundException;
import com.ims.system.repository.AssignRecordRepository;
import com.ims.system.repository.MasterLaptopRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class AssignRecordService {

    private final AssignRecordRepository assignRecordRepository;
    private final MasterLaptopRepository masterLaptopRepository;

    public AssignRecordService(AssignRecordRepository assignRecordRepository,
                              MasterLaptopRepository masterLaptopRepository) {
        this.assignRecordRepository = assignRecordRepository;
        this.masterLaptopRepository = masterLaptopRepository;
    }

    public List<AssignRecord> getAllAssignments() {
        return assignRecordRepository.findAll();
    }

    public AssignRecord getAssignmentById(Long id) {
        return assignRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found with id " + id));
    }

    @Transactional
    public AssignRecord createAssignment(AssignRecord record) {
        // Set assignment timestamp and status
        record.setAssignedAt(LocalDateTime.now());
        record.setCollectLaptop(true);
        record.setStatus("Assigned");

        // If master laptop present, ensure snapshot fields are set
        if (record.getMasterLaptop() != null) {
            MasterLaptop laptop = record.getMasterLaptop();
            if (record.getPcId() == null || record.getPcId().isEmpty()) {
                record.setPcId(laptop.getPcId());
            }
            if (record.getSerialNumber() == null || record.getSerialNumber().isEmpty()) {
                record.setSerialNumber(laptop.getSerialNumber());
            }
            if (record.getModel() == null || record.getModel().isEmpty()) {
                record.setModel(laptop.getModel());
            }

            // Update master laptop with current staff
            laptop.setCurrentRoutineStatus(record.getUserName());
            laptop.setEmployeeNo(record.getEmployeeId());
            masterLaptopRepository.save(laptop);
        }

        return assignRecordRepository.save(record);
    }

    public AssignRecord updateAssignment(Long id, AssignRecord details) {
        AssignRecord record = getAssignmentById(id);
        record.setStaffEntity(details.getStaffEntity());
        record.setPcId(details.getPcId());
        record.setCollectLaptop(details.isCollectLaptop());
        record.setReturnLaptop(details.isReturnLaptop());
        record.setSerialNumber(details.getSerialNumber());
        record.setUserName(details.getUserName());
        record.setRemark(details.getRemark());
        record.setEmployeeId(details.getEmployeeId());
        record.setEmail(details.getEmail());
        record.setModel(details.getModel());
        record.setStatus(details.getStatus());
        return assignRecordRepository.save(record);
    }

    @Transactional
    public AssignRecord returnLaptop(Long id) {
        AssignRecord record = getAssignmentById(id);

        // Set return timestamp and status
        record.setReturnedAt(LocalDateTime.now());
        record.setReturnLaptop(true);
        record.setStatus("Returned");

        // Update master laptop - clear current staff
        if (record.getMasterLaptop() != null) {
            MasterLaptop laptop = record.getMasterLaptop();
            laptop.setLastRoutineStatus(laptop.getCurrentRoutineStatus());
            laptop.setCurrentRoutineStatus(null);
            laptop.setLastWorkingDay(LocalDate.now());
            masterLaptopRepository.save(laptop);
        }

        return assignRecordRepository.save(record);
    }

    public void deleteAssignment(Long id) {
        assignRecordRepository.deleteById(id);
    }
}
