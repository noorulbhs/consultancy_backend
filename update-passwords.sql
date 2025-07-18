-- Update admin password with a new BCrypt hash for 'password123'
-- This hash was generated using BCrypt with strength 10
UPDATE users 
SET password = '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.' 
WHERE email = 'admin@altrevo.com';

-- Update editor password with the same hash
UPDATE users 
SET password = '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.' 
WHERE email = 'editor@altrevo.com';

-- Verify the update
SELECT email, password, role FROM users WHERE email IN ('admin@altrevo.com', 'editor@altrevo.com');
