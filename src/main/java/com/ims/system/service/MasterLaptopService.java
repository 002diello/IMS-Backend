package com.ims.system.service;

import com.ims.system.entity.MasterLaptop;
import com.ims.system.entity.AssignRecord;
import com.ims.system.exception.ResourceNotFoundException;
import com.ims.system.repository.MasterLaptopRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MasterLaptopService {

    private final MasterLaptopRepository masterLaptopRepository;
    private final AssignRecordService assignRecordService;

    public MasterLaptopService(MasterLaptopRepository masterLaptopRepository, 
                              AssignRecordService assignRecordService) {
        this.masterLaptopRepository = masterLaptopRepository;
        this.assignRecordService = assignRecordService;
    }

    public List<MasterLaptop> getAllLaptops() {
        return masterLaptopRepository.findAll();
    }

    public MasterLaptop getLaptopById(Long id) {
        return masterLaptopRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Laptop not found with id " + id));
    }

    public MasterLaptop createLaptop(MasterLaptop laptop) {
        return masterLaptopRepository.save(laptop);
    }

    @Transactional
    public MasterLaptop updateLaptop(Long id, MasterLaptop laptopDetails) {
        MasterLaptop laptop = getLaptopById(id);
        
        // Store original values to detect changes
        String originalCurrentRoutineStatus = laptop.getCurrentRoutineStatus();
        String originalEmployeeNo = laptop.getEmployeeNo();
        
        laptop.setInvoiceNumber(laptopDetails.getInvoiceNumber());
        laptop.setCsiAgreement(laptopDetails.getCsiAgreement());
        laptop.setStartDate(laptopDetails.getStartDate());
        laptop.setEndDate(laptopDetails.getEndDate());
        laptop.setLaptopEntity(laptopDetails.getLaptopEntity());
        laptop.setModel(laptopDetails.getModel());
        laptop.setSerialNumber(laptopDetails.getSerialNumber());
        laptop.setPcId(laptopDetails.getPcId());
        
        // Staff assignment tracking
        laptop.setLastRoutineStatus(laptopDetails.getLastRoutineStatus());
        laptop.setCurrentRoutineStatus(laptopDetails.getCurrentRoutineStatus());
        laptop.setStaffCompany(laptopDetails.getStaffCompany());
        laptop.setEmployeeNo(laptopDetails.getEmployeeNo());
        
        // Staff dates
        laptop.setJoinDate(laptopDetails.getJoinDate());
        laptop.setCollectDate(laptopDetails.getCollectDate());
        laptop.setLastWorkingDay(laptopDetails.getLastWorkingDay());
        
        laptop.setRemark(laptopDetails.getRemark());
        
        MasterLaptop savedLaptop = masterLaptopRepository.save(laptop);
        
        // Check if laptop is being assigned (currentRoutineStatus set and was previously null/empty)
        if (laptopDetails.getCurrentRoutineStatus() != null && 
            !laptopDetails.getCurrentRoutineStatus().trim().isEmpty() &&
            (originalCurrentRoutineStatus == null || originalCurrentRoutineStatus.trim().isEmpty())) {
            
            // Set collect date to today when assigning
            if (laptop.getCollectDate() == null) {
                laptop.setCollectDate(java.time.LocalDate.now());
            }
            
            try {
                // Create assignment record automatically with more complete data
                AssignRecord assignment = new AssignRecord();
                assignment.setMasterLaptop(savedLaptop);
                assignment.setPcId(savedLaptop.getPcId());
                assignment.setModel(savedLaptop.getModel());
                assignment.setSerialNumber(savedLaptop.getSerialNumber());
                assignment.setUserName(laptopDetails.getCurrentRoutineStatus());
                
                // Set employee ID if provided, otherwise use a default format
                if (laptopDetails.getEmployeeNo() != null && !laptopDetails.getEmployeeNo().trim().isEmpty()) {
                    assignment.setEmployeeId(laptopDetails.getEmployeeNo());
                } else {
                    // Generate a temporary employee ID based on username
                    assignment.setEmployeeId("AUTO-" + laptopDetails.getCurrentRoutineStatus().replaceAll("\\s+", "").toUpperCase());
                }
                
                // Set defaults for required fields
                assignment.setStaffEntity(laptopDetails.getStaffCompany() != null ? laptopDetails.getStaffCompany() : "Auto-Assigned");
                assignment.setEmail(""); // Admin can fill this later via Assign & Return page
                assignment.setRemark("Automatically assigned via Master Laptop page. Please complete staff details in Assign & Return.");
                assignment.setStatus("Assigned");
                
                assignRecordService.createAssignment(assignment);
                
                // Log successful automatic assignment
                System.out.println("Automatically created assignment for laptop " + savedLaptop.getPcId() + " to user " + laptopDetails.getCurrentRoutineStatus());
                
            } catch (Exception e) {
                // Log error but don't fail the laptop update
                System.err.println("Failed to create automatic assignment for laptop " + savedLaptop.getPcId() + ": " + e.getMessage());
                e.printStackTrace();
            }
        } else if ((laptopDetails.getCurrentRoutineStatus() == null || laptopDetails.getCurrentRoutineStatus().trim().isEmpty()) &&
                   (originalCurrentRoutineStatus != null && !originalCurrentRoutineStatus.trim().isEmpty())) {
            // Laptop is being unassigned - clear collect date
            laptop.setCollectDate(null);
        }
        
        return savedLaptop;
    }

    public void deleteLaptop(Long id) {
        masterLaptopRepository.deleteById(id);
    }
}


