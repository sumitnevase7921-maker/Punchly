# Re-run "mvn test" when any .java file under src/ changes (polling — works reliably on Windows).
# Usage (from project root):
#   .\watch-and-test.ps1
#   .\watch-and-test.ps1 -IntervalSeconds 5 -SkipInitialRun

param(
    [int] $IntervalSeconds = 5,
    [switch] $SkipInitialRun
)

$ErrorActionPreference = "Stop"
$ProjectRoot = if ($PSScriptRoot) { $PSScriptRoot } else { Get-Location }
Set-Location $ProjectRoot

function Get-JavaSnapshot {
    $files = @(Get-ChildItem -Path (Join-Path $ProjectRoot "src") -Recurse -Filter "*.java" -ErrorAction SilentlyContinue)
    if ($files.Count -eq 0) { return "" }
    ($files | ForEach-Object { "{0}|{1}" -f $_.FullName, $_.LastWriteTimeUtc.Ticks }) -join "`n"
}

Write-Host "Project: $ProjectRoot" -ForegroundColor Cyan
Write-Host "Polling every ${IntervalSeconds}s for changes under src\ (*.java)" -ForegroundColor Green
Write-Host "Command: mvn -B test" -ForegroundColor Gray
Write-Host "Press Ctrl+C to stop.`n" -ForegroundColor Gray

$snapshot = Get-JavaSnapshot
if (-not $SkipInitialRun) {
    Write-Host "=== Initial run ===" -ForegroundColor Cyan
    mvn -B test
    Write-Host "=== Initial run exit: $LASTEXITCODE ===`n" -ForegroundColor $(if ($LASTEXITCODE -eq 0) { "Green" } else { "Red" })
}

while ($true) {
    Start-Sleep -Seconds $IntervalSeconds
    $next = Get-JavaSnapshot
    if ($next -eq $snapshot) { continue }
    $snapshot = $next
    Write-Host "`n=== $(Get-Date -Format 'yyyy-MM-dd HH:mm:ss') Change detected — mvn test ===" -ForegroundColor Cyan
    mvn -B test
    Write-Host "=== Finished (exit $LASTEXITCODE) ===`n" -ForegroundColor $(if ($LASTEXITCODE -eq 0) { "Green" } else { "Red" })
}
