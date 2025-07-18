@echo off
echo Starting Altrevo Tech Solutions Backend...
echo.

echo Checking prerequisites...
where java >nul 2>nul
if %errorlevel% neq 0 (
    echo ERROR: Java is not installed or not in PATH
    pause
    exit /b 1
)

where mvn >nul 2>nul
if %errorlevel% neq 0 (
    echo ERROR: Maven is not installed or not in PATH
    pause
    exit /b 1
)

echo Prerequisites check passed!
echo.

echo Building the application...
call mvn clean package -DskipTests

if %errorlevel% neq 0 (
    echo ERROR: Build failed!
    pause
    exit /b 1
)

echo.
echo Starting the application...
echo The application will be available at: http://localhost:8080
echo API Documentation will be available at: http://localhost:8080/swagger-ui.html
echo.

java -jar target\consultancy-backend-1.0.0.jar

pause
