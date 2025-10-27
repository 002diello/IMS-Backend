-- Remove username column from app_users table
-- Run this SQL script in your MariaDB database

USE ims;

-- Drop the username column
ALTER TABLE app_users DROP COLUMN username;

-- Verify the change
DESCRIBE app_users;
