package com.aipxperts.punchly.tests;

import com.aipxperts.punchly.base.BaseTest;
import com.aipxperts.punchly.pages.LoginPage;
import com.aipxperts.punchly.pages.TimeOffPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TimeOffTest extends BaseTest {

    LoginPage loginPage;
    TimeOffPage timeOff;

    // ─── Login Before Each Test ───────────────────────────────────────────────────

    @BeforeMethod
    public void loginAndNavigate() throws InterruptedException {
        loginPage = new LoginPage(driver);
        timeOff   = new TimeOffPage(driver);

        // Step 1: Enter email and click Continue
        loginPage.login("sumit.nevase@aipxperts.com");

        // Step 2: Enter OTP (000000)
        loginPage.enterOTP();

        System.out.println("✅ Logged in successfully");
    }

    // ─── TC_TOF_001 : Open Time Off Page ─────────────────────────────────────────

    @Test(priority = 1)
    public void TC_TOF_001_verifyOpenTimeOff() throws InterruptedException {
        timeOff.openTimeOff();
        Thread.sleep(2000);
        System.out.println("✅ TC_TOF_001 PASSED - Time Off page opened");
    }

    // ─── TC_TOF_002 : Add Balance ─────────────────────────────────────────────────

    @Test(priority = 2)
    public void TC_TOF_002_verifyAddBalance() throws InterruptedException {
        timeOff.openTimeOff();
        Thread.sleep(2000);
        timeOff.addBalance("2");
        Thread.sleep(2000);
        System.out.println("✅ TC_TOF_002 PASSED - Balance added successfully");
    }

    // ─── TC_TOF_003 : Click Holidays Tab ─────────────────────────────────────────

    @Test(priority = 3)
    public void TC_TOF_003_verifyHolidaysTab() throws InterruptedException {
        timeOff.openTimeOff();
        Thread.sleep(2000);
        timeOff.clickHolidays();
        Thread.sleep(1000);
        System.out.println("✅ TC_TOF_003 PASSED - Holidays tab clicked");
    }

    // ─── TC_TOF_004 : Click Request Time Off Button ───────────────────────────────

    @Test(priority = 4)
    public void TC_TOF_004_verifyRequestTimeOffButton() throws InterruptedException {
        timeOff.openTimeOff();
        Thread.sleep(2000);
        timeOff.clickRequestTimeOff();
        Thread.sleep(1000);
        System.out.println("✅ TC_TOF_004 PASSED - Request Time Off button clicked");
    }

    // ─── TC_TOF_005 : Select Policy ───────────────────────────────────────────────

    @Test(priority = 5)
    public void TC_TOF_005_verifySelectPolicy() throws InterruptedException {
        timeOff.openTimeOff();
        Thread.sleep(2000);
        timeOff.clickRequestTimeOff();
        Thread.sleep(1000);
        timeOff.selectPolicy();
        Thread.sleep(2000);
        System.out.println("✅ TC_TOF_005 PASSED - Policy selected");
    }

    // ─── TC_TOF_006 : Select Start Date ──────────────────────────────────────────

    @Test(priority = 6)
    public void TC_TOF_006_verifySelectStartDate() throws InterruptedException {
        timeOff.openTimeOff();
        Thread.sleep(2000);
        timeOff.clickRequestTimeOff();
        Thread.sleep(1000);
        timeOff.selectPolicy();
        Thread.sleep(2000);
        timeOff.selectStartDate();
        Thread.sleep(2000);
        System.out.println("✅ TC_TOF_006 PASSED - Start date selected");
    }

    // ─── TC_TOF_007 : Select End Date ────────────────────────────────────────────

    @Test(priority = 7)
    public void TC_TOF_007_verifySelectEndDate() throws InterruptedException {
        timeOff.openTimeOff();
        Thread.sleep(2000);
        timeOff.clickRequestTimeOff();
        Thread.sleep(1000);
        timeOff.selectPolicy();
        Thread.sleep(2000);
        timeOff.selectStartDate();
        Thread.sleep(2000);
        timeOff.selectEndDate();
        Thread.sleep(2000);
        System.out.println("✅ TC_TOF_007 PASSED - End date selected");
    }

    // ─── TC_TOF_008 : Enter Note ──────────────────────────────────────────────────

    @Test(priority = 8)
    public void TC_TOF_008_verifyEnterNote() throws InterruptedException {
        timeOff.openTimeOff();
        Thread.sleep(2000);
        timeOff.clickRequestTimeOff();
        Thread.sleep(1000);
        timeOff.selectPolicy();
        Thread.sleep(2000);
        timeOff.selectStartDate();
        Thread.sleep(2000);
        timeOff.selectEndDate();
        Thread.sleep(2000);
        timeOff.enterNote("I am Going for a vaccation");
        Thread.sleep(1000);
        System.out.println("✅ TC_TOF_008 PASSED - Note entered");
    }

    // ─── TC_TOF_009 : Full Flow – Submit Time Off Request ────────────────────────

    @Test(priority = 9)
    public void TC_TOF_009_verifyFullTimeOffFlow() throws InterruptedException {
        timeOff.openTimeOff();
        Thread.sleep(2000);
        timeOff.clickRequestTimeOff();
        Thread.sleep(1000);
        timeOff.selectPolicy();
        Thread.sleep(2000);
        timeOff.selectStartDate();
        Thread.sleep(2000);
        timeOff.selectEndDate();
        Thread.sleep(2000);
        timeOff.enterNote("I am Going for a vaccation");
        Thread.sleep(1000);
        timeOff.clickSubmit();
        Thread.sleep(3000);
        System.out.println("✅ TC_TOF_009 PASSED - Full Time Off flow completed");
    }
}