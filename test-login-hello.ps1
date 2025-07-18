$headers = @{
    "Content-Type" = "application/json"
}

$body = @{
    email = "admin@altrevo.com"
    password = "hello"
} | ConvertTo-Json

try {
    $response = Invoke-RestMethod -Uri "http://localhost:8080/api/v1/admin/auth/login" -Method Post -Body $body -Headers $headers
    Write-Host "SUCCESS WITH 'hello'!" -ForegroundColor Green
    Write-Host "Response:" -ForegroundColor Green
    $response | ConvertTo-Json -Depth 3
} catch {
    Write-Host "FAILED with 'hello'!" -ForegroundColor Red
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}
