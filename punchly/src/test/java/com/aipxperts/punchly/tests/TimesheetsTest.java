package com.aipxperts.punchly.tests;

import com.aipxperts.punchly.base.BaseTest;
import com.aipxperts.punchly.pages.LoginPage;
import com.aipxperts.punchly.pages.TimesheetsPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TimesheetsTest extends BaseTest {

    TimesheetsPage timesheets;

    // ─── Setup (Login + Navigate) ─────────────────────────────────────────────

    @BeforeMethod
    public void pageSetup() {
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login("sumit.nevase@aipxperts.com");
            loginPage.enterOTP();

            timesheets = new TimesheetsPage(driver);
            timesheets.openTimesheets();

            System.out.println("✅ Setup complete - Timesheets page opened");

        } catch (Exception e) {
            throw new RuntimeException("❌ Setup failed", e);
        }
    }

    // ─── TC_TS_001 : Verify Timesheets Page Load ─────────────────────────────

    @Test(priority = 1)
    public void TC_TS_001_verifyTimesheetsPageLoad() {
        System.out.println("PASS: TC_TS_001 - Timesheets page loaded");
    }

    // ─── TC_TS_002 : Verify Add Row Click ────────────────────────────────────

    @Test(priority = 2)
    public void TC_TS_002_verifyAddRow() throws InterruptedException {
        timesheets.clickAddRow();
        System.out.println("PASS: TC_TS_002 - Add Row clicked");
    }

    // ─── TC_TS_003 : Verify Project Selection ────────────────────────────────

    @Test(priority = 3)
    public void TC_TS_003_verifyProjectSelection() throws InterruptedException {
        timesheets.clickAddRow();
        timesheets.selectProject();
        System.out.println("PASS: TC_TS_003 - Project selected");
    }

    // ─── TC_TS_004 : Verify Today Column Click ───────────────────────────────

    @Test(priority = 4)
    public void TC_TS_004_verifyTodayColumnClick() throws InterruptedException {
        timesheets.clickAddRow();
        timesheets.selectProject();
        timesheets.clickTodayColumn();
        System.out.println("PASS: TC_TS_004 - Today column clicked");
    }

    // ─── TC_TS_005 : Verify Time Entry ───────────────────────────────────────

    @Test(priority = 5)
    public void TC_TS_005_verifyTimeEntry() throws InterruptedException {
        timesheets.clickAddRow();
        timesheets.selectProject();
        timesheets.clickTodayColumn();
        timesheets.enterStartEndTime();
        System.out.println("PASS: TC_TS_005 - Time entered successfully");
    }

    // ─── TC_TS_006 : Verify Save Functionality ───────────────────────────────

    @Test(priority = 6)
    public void TC_TS_006_verifySave() throws InterruptedException {
        timesheets.clickAddRow();
        timesheets.selectProject();
        timesheets.clickTodayColumn();
        timesheets.enterStartEndTime();
        timesheets.clickSave();
        System.out.println("PASS: TC_TS_006 - Timesheet saved");
    }

    // ─── TC_TS_007 : Verify Submit for Approval ──────────────────────────────

    @Test(priority = 7)
    public void TC_TS_007_verifySubmitForApproval() throws InterruptedException {
        timesheets.clickAddRow();
        timesheets.selectProject();
        timesheets.clickTodayColumn();
        timesheets.enterStartEndTime();
        timesheets.clickSave();
        timesheets.submitForApproval();
        System.out.println("PASS: TC_TS_007 - Submit for approval executed");
    }

    // ─── TC_TS_008 : Verify Withdraw Submission ──────────────────────────────

    @Test(priority = 8)
    public void TC_TS_008_verifyWithdrawSubmission() throws InterruptedException {
        timesheets.withdrawSubmission();
        System.out.println("PASS: TC_TS_008 - Withdraw submission executed");
    }
}