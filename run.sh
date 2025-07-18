#!/bin/bash

echo "Starting Altrevo Tech Solutions Backend..."
echo

echo "Checking prerequisites..."
if ! command -v java &> /dev/null; then
    echo "ERROR: Java is not installed or not in PATH"
    exit 1
fi

if ! command -v mvn &> /dev/null; then
    echo "ERROR: Maven is not installed or not in PATH"
    exit 1
fi

echo "Prerequisites check passed!"
echo

echo "Building the application..."
mvn clean package -DskipTests

if [ $? -ne 0 ]; then
    echo "ERROR: Build failed!"
    exit 1
fi

echo
echo "Starting the application..."
echo "The application will be available at: http://localhost:8080"
echo "API Documentation will be available at: http://localhost:8080/swagger-ui.html"
echo

java -jar target/consultancy-backend-1.0.0.jar
