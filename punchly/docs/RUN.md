# How to run Punchly automation

## Prerequisites
- **JDK 17+** (`java -version`)
- **Maven 3.8+** (`mvn -version`)
- **Google Chrome** installed (WebDriverManager downloads matching ChromeDriver)

## Configure (optional)
Override defaults with JVM system properties:

```bash
mvn test -DbaseUrl=https://dev-app.punchly.work -DlocalePath=/en -DloginEmail=you@company.com -DotpCode=000000
```

Or set the same keys in your IDE run configuration.

## Run (pick one)

### Important: PowerShell vs CMD

| Shell | Change folder | Run batch file |
|--------|----------------|----------------|
| **PowerShell** | `Set-Location "c:\QA Automation\punchly"` or `cd "c:\QA Automation\punchly"` | `.\run-tests.bat` — **do not use** `cd /d` (that is CMD only) |
| **CMD** | `cd /d "c:\QA Automation\punchly"` | `run-tests.bat` |

### Windows — PowerShell (recommended)
```powershell
Set-Location "c:\QA Automation\punchly"
mvn test
```
Or:
```powershell
Set-Location "c:\QA Automation\punchly"
.\run-tests.ps1
```
To run the `.bat` from PowerShell:
```powershell
Set-Location "c:\QA Automation\punchly"
.\run-tests.bat
```

### Windows — CMD
```cmd
cd /d "c:\QA Automation\punchly"
run-tests.bat
```

### Smoke only (TC-01 … TC-07)
```powershell
.\run-tests.ps1 -SmokeOnly
```

### Single test class
```powershell
.\run-tests.ps1 -TestClass PunchlyCoreScenariosTest
```

### Plain Maven
From project folder (use quotes if path has spaces):
```powershell
Set-Location "c:\QA Automation\punchly"
mvn test
```

### Re-run tests when you change code (watch)
Polls `src\` for `.java` changes and runs **`mvn -B test`** (runs once on start, then after each change).

```powershell
Set-Location "c:\QA Automation\punchly"
.\watch-and-test.ps1
```

Optional: poll every **10** seconds only (no first run):
```powershell
.\watch-and-test.ps1 -IntervalSeconds 10 -SkipInitialRun
```

**Note:** Each run opens the browser and can take several minutes. For quick compile-only checks use `mvn -q test-compile` instead (you can edit the script if you prefer that).

## After a failure
- **HTML report:** `test-output\failure-report.html`
- **Screenshots:** `test-output\screenshots\`
- **Surefire XML:** `target\surefire-reports\`

## Test cases reference
See `docs/punchly-test-cases.md` — maps to `PunchlyCoreScenariosTest`, including a **summary table** and **testing type** (E2E, Smoke, Regression, Validation, CRUD, etc.) per TC.
