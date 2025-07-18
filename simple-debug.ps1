$uri = "http://localhost:8080/api/v1/debug/users"
$response = Invoke-WebRequest -Uri $uri -Method Get
Write-Host "Response: $($response.Content)"
