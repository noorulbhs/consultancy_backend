$headers = @{
    "Content-Type" = "application/json"
}

$passwords = @("password123", "hello", "admin", "admin123", "password", "123456")

foreach ($password in $passwords) {
    $body = @{
        email = "admin@altrevo.com"
        password = $password
    } | ConvertTo-Json
    
    Write-Host "Testing password: $password" -ForegroundColor Yellow
    
    try {
        $response = Invoke-RestMethod -Uri "http://localhost:8080/api/v1/admin/auth/login" -Method Post -Body $body -Headers $headers
        Write-Host "SUCCESS with password: $password" -ForegroundColor Green
        Write-Host "Response:" -ForegroundColor Green
        $response | ConvertTo-Json -Depth 3
        break
    } catch {
        Write-Host "FAILED with password: $password" -ForegroundColor Red
    }
}
