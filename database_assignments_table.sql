-- =====================================================
-- Create Assignments Table
-- =====================================================

USE ims;

-- Create assignments table
CREATE TABLE IF NOT EXISTS assignments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    staff_entity VARCHAR(255) COMMENT 'Staff department or entity',
    pc_id VARCHAR(100) COMMENT 'PC ID from master laptop',
    model VARCHAR(255) COMMENT 'Laptop model',
    serial_number VARCHAR(255) COMMENT 'Serial number',
    user_name VARCHAR(255) COMMENT 'Staff name (same as current_routine_status)',
    remark TEXT COMMENT 'Additional notes',
    employee_id VARCHAR(100) COMMENT 'Employee ID',
    email VARCHAR(255) COMMENT 'Employee email',
    assigned_at DATE COMMENT 'Date laptop was assigned',
    returned_at DATE COMMENT 'Date laptop was returned',
    master_laptop_id BIGINT COMMENT 'Foreign key to master_laptop table',
    status VARCHAR(50) DEFAULT 'Assigned' COMMENT 'Assignment status: Assigned or Returned',
    
    -- Foreign key constraint
    FOREIGN KEY (master_laptop_id) REFERENCES master_laptop(id) ON DELETE SET NULL,
    
    -- Indexes for better performance
    INDEX idx_status (status),
    INDEX idx_master_laptop_id (master_laptop_id),
    INDEX idx_employee_id (employee_id),
    INDEX idx_pc_id (pc_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Display table structure
DESCRIBE assignments;

-- Show all tables
SHOW TABLES;
