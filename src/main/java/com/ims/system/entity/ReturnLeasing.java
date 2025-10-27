package com.ims.system.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;


@Entity
@Table(name = "return_leasing")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReturnLeasing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String number;
    private String csiAgreement;
    private LocalDate startExecutionDate;
    private LocalDate endDate;
    private String entity; // "entity" is reserved sometimes
    private String model;
    private String serialNumber;
    private String pcId;
    private String tempAndrewCheckingRouting;
    private String resourceId;
}
