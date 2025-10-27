-- Migration to add status column to repair_record table

USE ims;

-- Add status column if it doesn't exist
ALTER TABLE repair_record
  ADD COLUMN IF NOT EXISTS status VARCHAR(20) DEFAULT 'Pending';

-- Verify the column was added
DESCRIBE repair_record;
