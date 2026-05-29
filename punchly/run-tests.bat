@echo off
REM Punchly automation — runs TestNG suite (TC-01 … TC-15)
cd /d "%~dp0"
echo Running Maven tests from: %CD%
echo.
mvn -B test
set EXIT=%ERRORLEVEL%
if %EXIT% neq 0 (
  echo.
  echo FAILED - see target\surefire-reports and test-output\failure-report.html
  exit /b %EXIT%
)
echo.
echo OK - all tests finished.
exit /b 0
