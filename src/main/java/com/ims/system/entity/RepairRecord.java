package com.ims.system.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;


@Entity
@Table(name = "repair_record")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RepairRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String entity;
    private LocalDate date;
    private String model;
    private String serialNumber;
    private String pcId;
    private String partReplaced;
    private String po;
    private String status;  // Pending, In Progress, Completed
    @Column(length = 1000)
    private String remarks;
}
