-- Migration to align assign_record columns and order
-- Target order:
-- id, assigned_at, email, employee_id, master_laptop_id, model, pc_id, remark, returned_at, serial_number, staff_entity, status, user_name

USE ims;

-- Ensure table exists
CREATE TABLE IF NOT EXISTS assign_record (
  id BIGINT AUTO_INCREMENT PRIMARY KEY
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Add missing columns (no-op if exists)
ALTER TABLE assign_record
  ADD COLUMN IF NOT EXISTS assigned_at DATETIME NULL,
  ADD COLUMN IF NOT EXISTS email VARCHAR(255) NULL,
  ADD COLUMN IF NOT EXISTS employee_id VARCHAR(100) NULL,
  ADD COLUMN IF NOT EXISTS master_laptop_id BIGINT NULL,
  ADD COLUMN IF NOT EXISTS model VARCHAR(255) NULL,
  ADD COLUMN IF NOT EXISTS pc_id VARCHAR(100) NULL,
  ADD COLUMN IF NOT EXISTS remark TEXT NULL,
  ADD COLUMN IF NOT EXISTS returned_at DATETIME NULL,
  ADD COLUMN IF NOT EXISTS serial_number VARCHAR(255) NULL,
  ADD COLUMN IF NOT EXISTS staff_entity VARCHAR(255) NULL,
  ADD COLUMN IF NOT EXISTS status VARCHAR(50) NULL,
  ADD COLUMN IF NOT EXISTS user_name VARCHAR(255) NULL,
  ADD INDEX IF NOT EXISTS idx_master_laptop_id (master_laptop_id);

-- Optional: keep legacy booleans for compatibility (comment out to drop)
-- ALTER TABLE assign_record DROP COLUMN collect_laptop, DROP COLUMN return_laptop;
-- If you prefer to drop them now, uncomment the above line.

-- Reorder columns to requested order (MariaDB supports AFTER)
ALTER TABLE assign_record
  MODIFY COLUMN id BIGINT AUTO_INCREMENT PRIMARY KEY FIRST,
  MODIFY COLUMN assigned_at DATETIME NULL AFTER id,
  MODIFY COLUMN email VARCHAR(255) NULL AFTER assigned_at,
  MODIFY COLUMN employee_id VARCHAR(100) NULL AFTER email,
  MODIFY COLUMN master_laptop_id BIGINT NULL AFTER employee_id,
  MODIFY COLUMN model VARCHAR(255) NULL AFTER master_laptop_id,
  MODIFY COLUMN pc_id VARCHAR(100) NULL AFTER model,
  MODIFY COLUMN remark TEXT NULL AFTER pc_id,
  MODIFY COLUMN returned_at DATETIME NULL AFTER remark,
  MODIFY COLUMN serial_number VARCHAR(255) NULL AFTER returned_at,
  MODIFY COLUMN staff_entity VARCHAR(255) NULL AFTER serial_number,
  MODIFY COLUMN status VARCHAR(50) NULL AFTER staff_entity,
  MODIFY COLUMN user_name VARCHAR(255) NULL AFTER status;

-- Foreign key to master_laptop (create if not exists)
-- Drop existing FK if necessary and recreate; adjust constraint name as needed
SET @fk_exists := (
  SELECT COUNT(*) FROM information_schema.KEY_COLUMN_USAGE
  WHERE TABLE_SCHEMA = DATABASE()
    AND TABLE_NAME = 'assign_record'
    AND REFERENCED_TABLE_NAME = 'master_laptop'
);

SET @sql := IF(@fk_exists = 0,
  'ALTER TABLE assign_record ADD CONSTRAINT fk_assign_record_master_laptop FOREIGN KEY (master_laptop_id) REFERENCES master_laptop(id) ON DELETE SET NULL ON UPDATE CASCADE;',
  'SELECT 1');

PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- Preview final structure
DESCRIBE assign_record;
