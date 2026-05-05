param(
    [string]$XamppRoot = "C:\xampp",
    [string]$SitePath = "opencartsite"
)

$ErrorActionPreference = "Stop"

$mysqlBin = Join-Path $XamppRoot "mysql\bin"
$mysqlData = Join-Path $XamppRoot "mysql\data"
$apacheBin = Join-Path $XamppRoot "apache\bin"
$mysqld = Join-Path $mysqlBin "mysqld.exe"
$mysql = Join-Path $mysqlBin "mysql.exe"
$ariaChk = Join-Path $mysqlBin "aria_chk.exe"
$httpd = Join-Path $apacheBin "httpd.exe"
$myIni = Join-Path $mysqlBin "my.ini"

function Test-ProcessByPath {
    param([string]$Name, [string]$Path)

    Get-Process -Name $Name -ErrorAction SilentlyContinue |
        Where-Object { -not $_.Path -or $_.Path -eq $Path } |
        Select-Object -First 1
}

function Start-MySql {
    Start-Process -FilePath $mysqld -ArgumentList "--defaults-file=$myIni", "--standalone" -WindowStyle Hidden
    Start-Sleep -Seconds 5
    Test-ProcessByPath -Name "mysqld" -Path $mysqld
}

function Repair-AriaIfNeeded {
    $ariaLogs = Get-ChildItem -LiteralPath $mysqlData -Filter "aria_log*" -ErrorAction SilentlyContinue
    if (-not $ariaLogs) {
        return
    }

    $stamp = Get-Date -Format "yyyyMMdd-HHmmss"
    $backup = Join-Path $mysqlData "_codex_repair_backup_$stamp"
    New-Item -ItemType Directory -Path $backup -Force | Out-Null

    $filesToSave = @(
        Join-Path $mysqlData "aria_log.00000001"
        Join-Path $mysqlData "aria_log_control"
        Join-Path $mysqlData "mysql_error.log"
    )

    foreach ($file in $filesToSave) {
        if (Test-Path -LiteralPath $file) {
            Copy-Item -LiteralPath $file -Destination $backup -Force
        }
    }

    Push-Location $mysqlData
    try {
        Get-ChildItem -LiteralPath $mysqlData -Recurse -Filter "*.MAI" |
            ForEach-Object {
                & $ariaChk -r $_.FullName | Out-Host
            }
    }
    finally {
        Pop-Location
    }

    Get-ChildItem -LiteralPath $mysqlData -Filter "aria_log*" -ErrorAction SilentlyContinue |
        Remove-Item -Force

    Write-Host "Aria repair complete. Backup saved to $backup"
}

if (-not (Test-Path -LiteralPath $mysqld)) {
    throw "MySQL server was not found at $mysqld"
}

if (-not (Test-ProcessByPath -Name "httpd" -Path $httpd)) {
    Start-Process -FilePath $httpd -WorkingDirectory $apacheBin -WindowStyle Hidden
    Start-Sleep -Seconds 2
}

if (-not (Test-ProcessByPath -Name "mysqld" -Path $mysqld)) {
    $started = Start-MySql
    if (-not $started) {
        Repair-AriaIfNeeded
        $started = Start-MySql
    }

    if (-not $started) {
        throw "MySQL did not stay running. Check $mysqlData\mysql_error.log"
    }
}

& $mysql -uroot -D opencartsite -e "SELECT 1 AS mysql_ok;" | Out-Host

$storeUrl = "http://localhost/$SitePath/"
$adminUrl = "http://localhost/$SitePath/admin/"
$storeStatus = (Invoke-WebRequest -Uri $storeUrl -UseBasicParsing).StatusCode
$adminStatus = (Invoke-WebRequest -Uri $adminUrl -UseBasicParsing).StatusCode

Write-Host "OpenCart storefront: HTTP $storeStatus - $storeUrl"
Write-Host "OpenCart admin:      HTTP $adminStatus - $adminUrl"
