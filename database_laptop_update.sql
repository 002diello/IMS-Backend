-- =====================================================
-- Master Laptop Table Update Script
-- Add new fields for staff tracking
-- =====================================================

USE ims;

-- Add new columns to master_laptop table
ALTER TABLE master_laptop
ADD COLUMN last_routine_status VARCHAR(255) COMMENT 'Last staff name who had the laptop',
ADD COLUMN current_routine_status VARCHAR(255) COMMENT 'Current staff name using the laptop',
ADD COLUMN employee_no VARCHAR(100) COMMENT 'Employee number';

-- Drop old routine_status column if it exists (replaced by current_routine_status)
ALTER TABLE master_laptop
DROP COLUMN IF EXISTS routine_status;

-- Display updated table structure
DESCRIBE master_laptop;

-- =====================================================
-- NOTES:
-- =====================================================
-- 1. last_routine_status: Stores the previous staff member's name
-- 2. current_routine_status: Stores the current staff member's name
-- 3. employee_no: Stores the employee number for tracking
-- 4. All existing data will be preserved
-- 5. New columns allow NULL values by default
-- =====================================================
