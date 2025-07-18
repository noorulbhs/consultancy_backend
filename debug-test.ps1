Write-Host "Testing debug endpoints..." -ForegroundColor Yellow

try {
    Write-Host "1. Checking if users exist..." -ForegroundColor Cyan
    $users = Invoke-RestMethod -Uri "http://localhost:8080/api/v1/debug/users" -Method Get
    Write-Host "Users found: $($users.Count)" -ForegroundColor Green
    $users | ForEach-Object { Write-Host "  - $($_.email) (role: $($_.role))" }
    
    Write-Host "`n2. Testing password for admin@altrevo.com with 'hello'..." -ForegroundColor Cyan
    $passwordTest = Invoke-RestMethod -Uri "http://localhost:8080/api/v1/debug/check-password/admin@altrevo.com/hello" -Method Get
    Write-Host "Password check result: $passwordTest" -ForegroundColor Green
    
    Write-Host "`n3. Testing login..." -ForegroundColor Cyan
    $headers = @{ "Content-Type" = "application/json" }
    $body = @{ email = "admin@altrevo.com"; password = "hello" } | ConvertTo-Json
    
    $loginResult = Invoke-RestMethod -Uri "http://localhost:8080/api/v1/admin/auth/login" -Method Post -Body $body -Headers $headers
    Write-Host "Login successful!" -ForegroundColor Green
    Write-Host "Token: $($loginResult.data.token)" -ForegroundColor Green
    
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
    if ($_.Exception.Response) {
        $reader = New-Object System.IO.StreamReader($_.Exception.Response.GetResponseStream())
        $responseBody = $reader.ReadToEnd()
        Write-Host "Response: $responseBody" -ForegroundColor Red
    }
}
