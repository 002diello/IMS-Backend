-- Migration to add collect_date column to master_laptop table

USE ims;

-- Add collect_date column if it doesn't exist
ALTER TABLE master_laptop
  ADD COLUMN IF NOT EXISTS collect_date DATE NULL;

-- Verify the column was added
DESCRIBE master_laptop;
