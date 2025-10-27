package com.ims.system.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "assign_record")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String staffEntity;


    @ManyToOne
    @JoinColumn(name = "master_laptop_id")
    private MasterLaptop masterLaptop; // references by id and pcId inside


    private String pcId;
    private boolean collectLaptop; // assigned true/false
    private String serialNumber;
    private String userName;
    private String remark;
    private String employeeId;
    private String email;
    private boolean returnLaptop; // marked returned
    private LocalDateTime assignedAt;
    private LocalDateTime returnedAt;

    // New fields to align with desired schema
    private String model;   // laptop model snapshot at assignment time
    private String status;  // Assigned / Returned
}
