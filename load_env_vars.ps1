Get-Content .env | ForEach-Object {
    $line = $_.Trim()
    if (-not [string]::IsNullOrWhiteSpace($line) -and -not $line.StartsWith("#")) {
        $keyValue = $line -split '=', 2
        [System.Environment]::SetEnvironmentVariable($keyValue[0], $keyValue[1], [System.EnvironmentVariableTarget]::Process)
    }
}