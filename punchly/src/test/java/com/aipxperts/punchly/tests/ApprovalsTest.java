package com.aipxperts.punchly.tests;

import com.aipxperts.punchly.base.BaseTest;
import com.aipxperts.punchly.pages.ApprovalsPage;
import com.aipxperts.punchly.pages.LoginPage;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ApprovalsTest extends BaseTest {

    ApprovalsPage approvalsPage;
    WebDriverWait wait;

    @BeforeMethod
    public void loginAndNavigate() throws InterruptedException {

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // ✅ Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("sumit.nevase@aipxperts.com");
        loginPage.enterOTP();

        // ✅ IMPORTANT: Wait until dashboard fully loads
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[normalize-space()='DASHBOARD']")
        ));

        // ✅ Navigate to Approvals
        approvalsPage = new ApprovalsPage(driver);
        approvalsPage.navigateToApprovalsPage();

        // ✅ Wait for Approvals page
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[normalize-space()='Approvals']")
        ));
    }

    // ================= TEST CASES =================

    // TC_APR_001
    @Test(priority = 1)
    public void TC_APR_001_verifyPageLoad() {
        Assert.assertTrue(approvalsPage.isApprovalsPageDisplayed(),
                "FAIL: Approvals page not loaded");
        System.out.println("PASS: TC_APR_001");
    }

    // TC_APR_002
    @Test(priority = 2)
    public void TC_APR_002_verifySearchBar() {
        Assert.assertTrue(approvalsPage.isSearchBarVisible(),
                "FAIL: Search bar not visible");
        System.out.println("PASS: TC_APR_002");
    }

    // TC_APR_003
    @Test(priority = 3)
    public void TC_APR_003_verifyTypeDropdown() {

        approvalsPage.openTypeDropdown();
        approvalsPage.selectAllTypes();

        approvalsPage.openTypeDropdown();
        approvalsPage.selectTimesheets();

        approvalsPage.openTypeDropdown();
        approvalsPage.selectExpenses();

        approvalsPage.openTypeDropdown();
        approvalsPage.selectTimeOff();

        System.out.println("PASS: TC_APR_003");
    }

    // TC_APR_004
    @Test(priority = 4)
    public void TC_APR_004_verifyStatusDropdown() {

        approvalsPage.openStatusDropdown();
        approvalsPage.selectPending();

        approvalsPage.openStatusDropdown();
        approvalsPage.selectApproved();

        approvalsPage.openStatusDropdown();
        approvalsPage.selectRejected();

        approvalsPage.openStatusDropdown();
        approvalsPage.selectAllStatus();

        System.out.println("PASS: TC_APR_004");
    }

    // TC_APR_005 (MAIN SCENARIO)
    @Test(priority = 5)
    public void TC_APR_005_verifyApprovedFlow() {

        approvalsPage.openStatusDropdown();
        approvalsPage.selectApproved();

        // ✅ Wait for result (IMPORTANT FIX)
        wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//span[normalize-space()='APPROVED']")
                ),
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//*[contains(text(),'No pending approvals')]")
                )
        ));

        Assert.assertTrue(
                approvalsPage.isApprovedDataVisible() ||
                approvalsPage.isNoDataVisible(),
                "FAIL: Approved filter not working"
        );

        System.out.println("PASS: TC_APR_005");
    }
}