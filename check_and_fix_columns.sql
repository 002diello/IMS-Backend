-- =====================================================
-- Check and Fix Master Laptop Table Columns
-- =====================================================

USE ims;

-- First, let's see what columns currently exist
DESCRIBE master_laptop;

-- =====================================================
-- If you see columns that need to be dropped, uncomment and run:
-- =====================================================

-- Drop old routine_status if it exists
-- ALTER TABLE master_laptop DROP COLUMN IF EXISTS routine_status;

-- =====================================================
-- Only add columns if they DON'T exist
-- Run these one by one and ignore errors if column exists
-- =====================================================

-- Add last_routine_status (skip if already exists)
-- ALTER TABLE master_laptop ADD COLUMN last_routine_status VARCHAR(255);

-- Add current_routine_status (skip if already exists)
-- ALTER TABLE master_laptop ADD COLUMN current_routine_status VARCHAR(255);

-- Add employee_no (skip if already exists)
-- ALTER TABLE master_laptop ADD COLUMN employee_no VARCHAR(100);

-- =====================================================
-- Verify final structure
-- =====================================================
DESCRIBE master_laptop;

-- Check if all required columns exist
SELECT 
    COLUMN_NAME, 
    DATA_TYPE, 
    CHARACTER_MAXIMUM_LENGTH,
    IS_NULLABLE
FROM INFORMATION_SCHEMA.COLUMNS 
WHERE TABLE_SCHEMA = 'ims' 
  AND TABLE_NAME = 'master_laptop'
ORDER BY ORDINAL_POSITION;
