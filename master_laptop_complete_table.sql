-- =====================================================
-- Master Laptop - Complete Table Structure
-- =====================================================

USE ims;

-- Drop existing table if you want to recreate (CAUTION: This deletes all data!)
-- DROP TABLE IF EXISTS master_laptop;

-- Create master_laptop table with all fields
CREATE TABLE IF NOT EXISTS master_laptop (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    
    -- Invoice and Agreement Info
    invoice_number VARCHAR(255),
    csi_agreement VARCHAR(255),
    
    -- Date Range
    start_date DATE,
    end_date DATE,
    
    -- Laptop Details
    laptop_entity VARCHAR(255),
    model VARCHAR(255),
    serial_number VARCHAR(255),
    pc_id VARCHAR(255),
    
    -- Staff Assignment Tracking (NEW)
    last_routine_status VARCHAR(255) COMMENT 'Last staff name who had the laptop',
    current_routine_status VARCHAR(255) COMMENT 'Current staff name using the laptop',
    
    -- Staff Info
    staff_company VARCHAR(255),
    employee_no VARCHAR(100) COMMENT 'Employee number',
    
    -- Staff Dates
    join_date DATE,
    last_working_day DATE,
    
    -- Additional Info
    remark VARCHAR(1000),
    
    -- Metadata
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    -- Indexes for better performance
    INDEX idx_pc_id (pc_id),
    INDEX idx_serial_number (serial_number),
    INDEX idx_current_staff (current_routine_status),
    INDEX idx_employee_no (employee_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Display table structure
DESCRIBE master_laptop;

-- Sample data insert (optional)
INSERT INTO master_laptop (
    invoice_number, 
    csi_agreement, 
    start_date, 
    end_date, 
    laptop_entity, 
    model, 
    serial_number, 
    pc_id, 
    current_routine_status, 
    staff_company, 
    employee_no,
    remark
) VALUES (
    'INV-2024-001',
    'CSI-AGR-2024-001',
    '2024-01-01',
    '2025-12-31',
    'Company Laptop',
    'Dell Latitude 5420',
    'SN123456789',
    'PC-001',
    'John Doe',
    'ABC Corporation',
    'EMP001',
    'Assigned to IT Department'
);

-- Verify data
SELECT * FROM master_laptop;
