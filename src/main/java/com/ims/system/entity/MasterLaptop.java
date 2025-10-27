package com.ims.system.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
@Entity
@Table(name = "master_laptop")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasterLaptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String invoiceNumber;
    private String csiAgreement;
    private LocalDate startDate;
    private LocalDate endDate;
    private String laptopEntity;
    private String model;
    private String serialNumber;
    private String pcId;
    private String lastRoutineStatus;      // Last ROUTINE STATUS (Staff Name)
    private String currentRoutineStatus;   // Current ROUTINE STATUS (Staff Name)
    private String staffCompany;
    private String employeeNo;
    private LocalDate joinDate;
    private LocalDate collectDate;          // Date when laptop was collected/assigned
    private LocalDate lastWorkingDay;
    
    @Column(length = 1000)
    private String remark;
}
