# Altrevo Tech Solutions - IT Consultancy Backend

A comprehensive Spring Boot backend application for Altrevo Tech Solutions IT Consultancy Platform. This backend provides a complete REST API for managing consultancy services, team members, testimonials, blog posts, job postings, projects, file uploads, feature toggles, and admin dashboard analytics.

## Features

- **Authentication & Authorization** with JWT tokens
- **Role-based Access Control** (SUPER_ADMIN, ADMIN, EDITOR, VIEWER)
- **Public API** endpoints for website content
- **Admin API** endpoints for content management
- **MySQL Database** with JPA/Hibernate
- **Redis Caching** for performance optimization
- **File Upload** support
- **API Documentation** with Swagger/OpenAPI
- **Email Integration** for contact forms
- **Comprehensive Logging**
- **Health Checks** and monitoring

## API Endpoints

### Public Endpoints (No Authentication Required)
- `GET /public/settings` - Get site settings
- `GET /public/services` - Get all active services
- `GET /public/services/{id}` - Get service details
- `GET /public/testimonials` - Get all testimonials
- `POST /public/enquiries` - Submit contact form

### Admin Endpoints (Authentication Required)
- `POST /admin/auth/login` - Admin login
- `POST /admin/auth/refresh` - Refresh token
- `POST /admin/auth/logout` - Logout
- `PUT /admin/settings` - Update site settings
- `GET /admin/services` - Get all services (including drafts)
- `POST /admin/services` - Create service
- `PUT /admin/services/{id}` - Update service
- `DELETE /admin/services/{id}` - Delete service

## Prerequisites

- Java 17+
- Maven 3.6+
- MySQL 8.0+
- Redis 6.0+

## Installation & Setup

### 1. Clone the Repository
```bash
git clone <repository-url>
cd consultancy-backend
```

### 2. Database Setup

#### MySQL Database
Create a MySQL database:
```sql
CREATE DATABASE altrevo_consultancy;
CREATE USER 'altrevo_user'@'localhost' IDENTIFIED BY 'altrevo_password';
GRANT ALL PRIVILEGES ON altrevo_consultancy.* TO 'altrevo_user'@'localhost';
FLUSH PRIVILEGES;
```

#### Redis Setup
Install and start Redis:
```bash
# On Ubuntu/Debian
sudo apt-get install redis-server
sudo systemctl start redis-server

# On macOS
brew install redis
brew services start redis

# On Windows
# Download and install Redis for Windows
```

### 3. Configuration

Update `src/main/resources/application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/altrevo_consultancy?useSSL=false&serverTimezone=UTC&createDatabaseIfNotExist=true
spring.datasource.username=altrevo_user
spring.datasource.password=altrevo_password

# Redis Configuration
spring.data.redis.host=localhost
spring.data.redis.port=6379
spring.data.redis.password=

# JWT Configuration
jwt.secret=your-jwt-secret-key-here
jwt.expiration=86400000
jwt.refresh-expiration=604800000

# Mail Configuration
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password
```

### 4. Build and Run

```bash
# Build the application
mvn clean install

# Run the application
mvn spring-boot:run

# Or run the JAR file
java -jar target/consultancy-backend-1.0.0.jar
```

The application will start on `http://localhost:8080`

## API Documentation

Once the application is running, you can access:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/api-docs

## Testing the API

### 1. Create Admin User
First, you need to create an admin user directly in the database:

```sql
INSERT INTO users (email, name, password, role, is_active, is_email_verified, created_at, updated_at) 
-- ADMIN USER (created via API, July 2025):
-- email: adminuser@altrevo.com
-- password: adminpassword123
-- role: ADMIN
VALUES ('admin@altrevo.com', 'Admin User', '$2a$10$6K8V2VJfGCIV5VBJGgH8eOjLYxLLMoD7.5qV1qGYQOjJGxgOKLKMm', 'SUPER_ADMIN', true, true, NOW(), NOW());
```

The password is `password123` (BCrypt hashed).

### 2. Login
```bash
curl -X POST http://localhost:8080/api/v1/admin/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "adminuser@altrevo.com",  // password: adminpassword123
    "password": "password123"
  }'
```

### 3. Use the JWT Token
```bash
curl -X GET http://localhost:8080/api/v1/admin/services \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### 4. Test Public Endpoints
```bash
# Get site settings
curl -X GET http://localhost:8080/api/v1/public/settings

# Get services
curl -X GET http://localhost:8080/api/v1/public/services

# Submit enquiry
curl -X POST http://localhost:8080/api/v1/public/enquiries \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john@example.com",
    "phone": "+1234567890",
    "company": "Example Corp",
    "subject": "general",
    "service": "Cloud Migration",
    "message": "We need help with cloud migration"
  }'
```

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── altrevo/
│   │           └── consultancy/
│   │               ├── config/          # Configuration classes
│   │               ├── controller/      # REST Controllers
│   │               ├── dto/             # Data Transfer Objects
│   │               ├── entity/          # JPA Entities
│   │               ├── enums/           # Enums
│   │               ├── repository/      # JPA Repositories
│   │               ├── security/        # Security components
│   │               └── service/         # Business logic
│   └── resources/
│       ├── application.properties      # Application configuration
│       └── static/                     # Static resources
└── test/                              # Test classes
```

## Environment Variables

You can also configure the application using environment variables:

```bash
export SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/altrevo_consultancy
export SPRING_DATASOURCE_USERNAME=altrevo_user
export SPRING_DATASOURCE_PASSWORD=altrevo_password
export SPRING_DATA_REDIS_HOST=localhost
export SPRING_DATA_REDIS_PORT=6379
export JWT_SECRET=your-jwt-secret-key
export SPRING_MAIL_USERNAME=your-email@gmail.com
export SPRING_MAIL_PASSWORD=your-app-password
```

## Docker Support

Create a `docker-compose.yml` file for easy deployment:

```yaml
version: '3.8'
services:
  mysql:
    image: mysql:8.0
    environment:
      MYSQL_ROOT_PASSWORD: rootpassword
      MYSQL_DATABASE: altrevo_consultancy
      MYSQL_USER: altrevo_user
      MYSQL_PASSWORD: altrevo_password
    ports:
      - "3306:3306"
    volumes:
      - mysql_data:/var/lib/mysql

  redis:
    image: redis:6.0
    ports:
      - "6379:6379"

  app:
    image: openjdk:17-jdk-slim
    depends_on:
      - mysql
      - redis
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/altrevo_consultancy
      SPRING_DATASOURCE_USERNAME: altrevo_user
      SPRING_DATASOURCE_PASSWORD: altrevo_password
      SPRING_DATA_REDIS_HOST: redis
    volumes:
      - ./target/consultancy-backend-1.0.0.jar:/app/app.jar
    command: java -jar /app/app.jar

volumes:
  mysql_data:
```

## Production Deployment

For production deployment:

1. **Security**: Change JWT secret, database passwords
2. **SSL**: Configure HTTPS
3. **Monitoring**: Set up application monitoring
4. **Backup**: Configure database backups
5. **Logging**: Configure centralized logging
6. **Load Balancing**: Use nginx or similar

## Troubleshooting

### Common Issues

1. **Database Connection**
   - Check MySQL is running
   - Verify database credentials
   - Check firewall settings

2. **Redis Connection**
   - Verify Redis is running
   - Check Redis configuration

3. **JWT Token Issues**
   - Ensure JWT secret is properly configured
   - Check token expiration times

4. **Permission Errors**
   - Verify user roles are correctly set
   - Check @PreAuthorize annotations

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License.

## Support

For support, email support@altrevo.com or create an issue in the repository.
