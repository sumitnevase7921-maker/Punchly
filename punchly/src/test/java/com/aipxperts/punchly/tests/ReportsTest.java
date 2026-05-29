package com.aipxperts.punchly.tests;

import com.aipxperts.punchly.pages.LoginPage;
import com.aipxperts.punchly.pages.ReportsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class ReportsTest {

    WebDriver driver;
    LoginPage loginPage;
    ReportsPage reportsPage;

    // ─── Setup ───────────────────────────────────────────────────────────────────

    @BeforeMethod
    public void setup() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://dev-app.punchly.work/en/dashboard");

        loginPage   = new LoginPage(driver);
        reportsPage = new ReportsPage(driver);

        // Step 1: Enter email and click Continue
        loginPage.login("sumit.nevase@aipxperts.com");

        // Step 2: Enter OTP (000000)
        loginPage.enterOTP();

        // Step 3: Navigate to Reports
        reportsPage.goToReports();

        System.out.println("✅ Setup complete - logged in and on Reports page");
    }

    // ─── TC_REP_001 : Click Time Report Tab ──────────────────────────────────────

    @Test(priority = 1)
    public void TC_REP_001_verifyTimeReportTab() throws InterruptedException {
        reportsPage.clickTimeReport();
        Thread.sleep(2000);
        System.out.println("✅ TC_REP_001 PASSED - Time Report tab clicked");
    }

    // ─── TC_REP_002 : Time Report – Summary Sub Tab ───────────────────────────────

    @Test(priority = 2)
    public void TC_REP_002_verifyTimeReportSummary() throws InterruptedException {
        reportsPage.clickTimeReport();
        Thread.sleep(1500);
        reportsPage.clickSummary();
        Thread.sleep(2000);
        System.out.println("✅ TC_REP_002 PASSED - Time Report > Summary clicked");
    }

    // ─── TC_REP_003 : Time Report – Summary Export ───────────────────────────────

    @Test(priority = 3)
    public void TC_REP_003_verifyTimeReportSummaryExport() throws InterruptedException {
        reportsPage.clickTimeReport();
        Thread.sleep(1500);
        reportsPage.clickSummary();
        Thread.sleep(1500);
        reportsPage.clickExport();
        Thread.sleep(3000);
        System.out.println("✅ TC_REP_003 PASSED - Time Report > Summary > Export clicked");
    }

    // ─── TC_REP_004 : Time Report – Detailed Sub Tab ─────────────────────────────

    @Test(priority = 4)
    public void TC_REP_004_verifyTimeReportDetailed() throws InterruptedException {
        reportsPage.clickTimeReport();
        Thread.sleep(1500);
        reportsPage.clickDetailed();
        Thread.sleep(2000);
        System.out.println("✅ TC_REP_004 PASSED - Time Report > Detailed clicked");
    }

    // ─── TC_REP_005 : Click Team Report Tab ──────────────────────────────────────

    @Test(priority = 5)
    public void TC_REP_005_verifyTeamReportTab() throws InterruptedException {
        reportsPage.clickTeamReport();
        Thread.sleep(2000);
        System.out.println("✅ TC_REP_005 PASSED - Team Report tab clicked");
    }

    // ─── TC_REP_006 : Team Report – Attendance Sub Tab ───────────────────────────

    @Test(priority = 6)
    public void TC_REP_006_verifyTeamReportAttendance() throws InterruptedException {
        reportsPage.clickTeamReport();
        Thread.sleep(1500);
        reportsPage.clickAttendance();
        Thread.sleep(2000);
        System.out.println("✅ TC_REP_006 PASSED - Team Report > Attendance clicked");
    }

    // ─── TC_REP_007 : Team Report – Assignments Sub Tab ──────────────────────────

    @Test(priority = 7)
    public void TC_REP_007_verifyTeamReportAssignments() throws InterruptedException {
        reportsPage.clickTeamReport();
        Thread.sleep(1500);
        reportsPage.clickAssignments();
        Thread.sleep(2000);
        System.out.println("✅ TC_REP_007 PASSED - Team Report > Assignments clicked");
    }

    // ─── TC_REP_008 : Click Expense Report Tab ───────────────────────────────────

    @Test(priority = 8)
    public void TC_REP_008_verifyExpenseReportTab() throws InterruptedException {
        reportsPage.clickExpenseReport();
        Thread.sleep(2000);
        System.out.println("✅ TC_REP_008 PASSED - Expense Report tab clicked");
    }

    // ─── TC_REP_009 : Expense Report – Export ────────────────────────────────────

    @Test(priority = 9)
    public void TC_REP_009_verifyExpenseReportExport() throws InterruptedException {
        reportsPage.clickExpenseReport();
        Thread.sleep(1500);
        reportsPage.clickExport();
        Thread.sleep(3000);
        System.out.println("✅ TC_REP_009 PASSED - Expense Report > Export clicked");
    }

    // ─── Teardown ─────────────────────────────────────────────────────────────────

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        System.out.println("✅ Browser closed");
    }
}