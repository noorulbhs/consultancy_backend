-- Direct SQL commands to update passwords
-- This script contains UPDATE statements that can be run directly in MySQL

-- First, let's see current users
SELECT email, password, role FROM users WHERE email IN ('admin@altrevo.com', 'editor@altrevo.com');

-- Update admin password with known BCrypt hash for 'password123'
UPDATE users 
SET password = '$2a$10$N.kmcuVajPNlHO9pJIxgpe/OAuOSQCEk8rGkKDT3TJsGUKKnGPgN2' 
WHERE email = 'admin@altrevo.com';

-- Update editor password with same hash
UPDATE users 
SET password = '$2a$10$N.kmcuVajPNlHO9pJIxgpe/OAuOSQCEk8rGkKDT3TJsGUKKnGPgN2' 
WHERE email = 'editor@altrevo.com';

-- Verify the updates
SELECT email, password, role FROM users WHERE email IN ('admin@altrevo.com', 'editor@altrevo.com');
