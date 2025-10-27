package com.ims.system.service;

import com.ims.system.entity.ReturnLeasing;
import com.ims.system.exception.ResourceNotFoundException;
import com.ims.system.repository.ReturnLeasingRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReturnLeasingService {

    private final ReturnLeasingRepository returnLeasingRepository;

    public ReturnLeasingService(ReturnLeasingRepository returnLeasingRepository) {
        this.returnLeasingRepository = returnLeasingRepository;
    }

    public List<ReturnLeasing> getAllReturns() {
        return returnLeasingRepository.findAll();
    }

    public ReturnLeasing getReturnById(Long id) {
        return returnLeasingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Return leasing not found with id " + id));
    }

    public ReturnLeasing createReturn(ReturnLeasing returnLeasing) {
        return returnLeasingRepository.save(returnLeasing);
    }

    public ReturnLeasing updateReturn(Long id, ReturnLeasing details) {
        ReturnLeasing existing = getReturnById(id);
        existing.setNumber(details.getNumber());
        existing.setCsiAgreement(details.getCsiAgreement());
        existing.setStartExecutionDate(details.getStartExecutionDate());
        existing.setEndDate(details.getEndDate());
        existing.setEntity(details.getEntity());
        existing.setModel(details.getModel());
        existing.setSerialNumber(details.getSerialNumber());
        existing.setPcId(details.getPcId());
        existing.setTempAndrewCheckingRouting(details.getTempAndrewCheckingRouting());
        existing.setResourceId(details.getResourceId());
        return returnLeasingRepository.save(existing);
    }

    public void deleteReturn(Long id) {
        returnLeasingRepository.deleteById(id);
    }
}
