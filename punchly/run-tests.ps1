# Punchly automation — runs TestNG suite (TC-01 … TC-15)
# Usage: .\run-tests.ps1
#        .\run-tests.ps1 -SmokeOnly
#        .\run-tests.ps1 -TestClass PunchlyCoreScenariosTest

param(
    [switch] $SmokeOnly,
    [string] $TestClass = ""
)

$ErrorActionPreference = "Stop"
Set-Location $PSScriptRoot

Write-Host "Project: $PSScriptRoot" -ForegroundColor Cyan

if ($TestClass) {
    Write-Host "Running: mvn test -Dtest=$TestClass" -ForegroundColor Yellow
    mvn -B test "-Dtest=$TestClass"
} elseif ($SmokeOnly) {
    Write-Host "Running smoke group only..." -ForegroundColor Yellow
    mvn -B test "-Dgroups=smoke"
} else {
    Write-Host "Running full suite (testng.xml)..." -ForegroundColor Yellow
    mvn -B test
}

$code = $LASTEXITCODE
if ($code -ne 0) {
    Write-Host "`nFAILED (exit $code) — see target\surefire-reports and test-output\" -ForegroundColor Red
    exit $code
}
Write-Host "`nOK — tests finished." -ForegroundColor Green
exit 0
