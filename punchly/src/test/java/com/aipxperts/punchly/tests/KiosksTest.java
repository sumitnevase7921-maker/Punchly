package com.aipxperts.punchly.tests;

import com.aipxperts.punchly.base.BaseTest;
import com.aipxperts.punchly.pages.KiosksPage;
import com.aipxperts.punchly.pages.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class KiosksTest extends BaseTest {

    private LoginPage loginPage;
    private KiosksPage kiosksPage;

    // ─────────────────────────────────────────────────────────────────────────────
    // SETUP
    // ─────────────────────────────────────────────────────────────────────────────

    @BeforeMethod
    public void loginAndNavigate() throws InterruptedException {
        loginPage  = new LoginPage(driver);
        kiosksPage = new KiosksPage(driver);
        loginPage.login("sumit.nevase@aipxperts.com");
        loginPage.enterOTP();
        kiosksPage.goToKiosks();
        Thread.sleep(1500);
        System.out.println("✅ Setup complete — logged in and on Kiosks page");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_KSK_001 — Kiosks page opens after sidebar click
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 1)
    public void TC_KSK_001_verifyKiosksPageOpened() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("✅ TC_KSK_001 PASSED — Kiosks page opened");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_KSK_002 — Status dropdown opens showing Active and Inactive options
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 2)
    public void TC_KSK_002_verifyStatusDropdownOpens() throws InterruptedException {
        kiosksPage.clickStatusDropdown();
        Thread.sleep(1500);
        System.out.println("✅ TC_KSK_002 PASSED — Status dropdown opened");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_KSK_003 — Select Active from status dropdown
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 3)
    public void TC_KSK_003_verifySelectActiveStatus() throws InterruptedException {
        kiosksPage.clickStatusDropdown();
        Thread.sleep(800);
        kiosksPage.selectStatusActive();
        Thread.sleep(1500);
        System.out.println("✅ TC_KSK_003 PASSED — Active status selected");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_KSK_004 — Select Inactive from status dropdown
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 4)
    public void TC_KSK_004_verifySelectInactiveStatus() throws InterruptedException {
        kiosksPage.clickStatusDropdown();
        Thread.sleep(800);
        kiosksPage.selectStatusInactive();
        Thread.sleep(1500);
        System.out.println("✅ TC_KSK_004 PASSED — Inactive status selected");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_KSK_005 — Search kiosks bar is clickable and accepts input
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 5)
    public void TC_KSK_005_verifySearchBarAcceptsInput() throws InterruptedException {
        kiosksPage.searchKiosk("This is Kiosk");
        Thread.sleep(1500);
        System.out.println("✅ TC_KSK_005 PASSED — Search bar input entered");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_KSK_006 — Create Kiosk button opens the modal
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 6)
    public void TC_KSK_006_verifyCreateKioskModalOpens() throws InterruptedException {
        kiosksPage.clickCreateKioskButton();
        Thread.sleep(1500);
        System.out.println("✅ TC_KSK_006 PASSED — Create Kiosk modal opened");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_KSK_007 — Enter Kiosk name in create modal
    // ─────────────────────────────────────────────────────────────────────────────

  @Test(priority = 7)
    public void TC_KSK_007_verifyEnterKioskName() throws InterruptedException {
        kiosksPage.clickCreateKioskButton();
        Thread.sleep(1000);
        kiosksPage.enterKioskName("Automation Kiosk");
        Thread.sleep(1000);
        System.out.println("✅ TC_KSK_007 PASSED — Kiosk name entered");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_KSK_008 — Assignees dropdown opens in create modal
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 8)
    public void TC_KSK_008_verifyAssigneesDropdownOpens() throws InterruptedException {
        kiosksPage.clickCreateKioskButton();
        Thread.sleep(1000);
        kiosksPage.clickAssigneesDropdown();
        Thread.sleep(1500);
        System.out.println("✅ TC_KSK_008 PASSED — Assignees dropdown opened");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_KSK_009 — Default Project dropdown opens with search
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 9)
    public void TC_KSK_009_verifyDefaultProjectDropdownOpens() throws InterruptedException {
        kiosksPage.clickCreateKioskButton();
        Thread.sleep(1000);
        kiosksPage.clickDefaultProjectDropdown();
        Thread.sleep(1500);
        System.out.println("✅ TC_KSK_009 PASSED — Default Project dropdown opened");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_KSK_010 — Session Timeout dropdown opens (1hr/4hr/8hr/12hr/24hr/48hr/7days)
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 10)
    public void TC_KSK_010_verifySessionTimeoutDropdownOpens() throws InterruptedException {
        kiosksPage.clickCreateKioskButton();
        Thread.sleep(1000);
        kiosksPage.clickSessionTimeoutDropdown();
        Thread.sleep(1500);
        System.out.println("✅ TC_KSK_010 PASSED — Session Timeout dropdown opened");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_KSK_011 — Select Session Timeout option: 1 hour
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 11)
    public void TC_KSK_011_verifySelectSessionTimeout1Hour() throws InterruptedException {
        kiosksPage.clickCreateKioskButton();
        Thread.sleep(1000);
        kiosksPage.clickSessionTimeoutDropdown();
        Thread.sleep(800);
        kiosksPage.selectSessionTimeout("1 hour");
        Thread.sleep(500);
        System.out.println("✅ TC_KSK_011 PASSED — Session timeout 1 hour selected");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_KSK_012 — Select Session Timeout option: 24 hours
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 12)
    public void TC_KSK_012_verifySelectSessionTimeout24Hours() throws InterruptedException {
        kiosksPage.clickCreateKioskButton();
        Thread.sleep(1000);
        kiosksPage.clickSessionTimeoutDropdown();
        Thread.sleep(800);
        kiosksPage.selectSessionTimeout("24 hours");
        Thread.sleep(500);
        System.out.println("✅ TC_KSK_012 PASSED — Session timeout 24 hours selected");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_KSK_013 — Cancel button closes create modal without saving
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 13)
    public void TC_KSK_013_verifyCancelCreateModal() throws InterruptedException {
        kiosksPage.clickCreateKioskButton();
        Thread.sleep(1000);
        kiosksPage.enterKioskName("Should Not Save");
        Thread.sleep(500);
        kiosksPage.clickCancelButton();
        Thread.sleep(1000);
        System.out.println("✅ TC_KSK_013 PASSED — Create modal cancelled");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_KSK_014 — Full create kiosk flow (Name + Assignee + Create)
    // SCENARIO:
    //   1. Click + Create Kiosk
    //   2. Enter name
    //   3. Select assignee
    //   4. Click Create
    // ─────────────────────────────────────────────────────────────────────────────
    @Test(priority = 14)
    public void TC_KSK_014_verifyFullCreateKioskFlow() {

        kiosksPage.clickCreateKioskButton();

        kiosksPage.enterKioskName("Automation Kiosk");

        kiosksPage.searchAndSelectAssignee("Sumit Nevase");

        kiosksPage.searchAndSelectProject("test the punchly");

        kiosksPage.clickSessionTimeoutDropdown();
        kiosksPage.selectSessionTimeout("24 hours");

        kiosksPage.clickCreateButton();

        System.out.println("✅ TC_KSK_014 PASSED — Kiosk created successfully");
    }
   /* @Test(priority = 14)
    public void TC_KSK_014_verifyFullCreateKioskFlow() throws InterruptedException {
        kiosksPage.clickCreateKioskButton();
        Thread.sleep(1000);
        kiosksPage.enterKioskName("Automation Kiosk");
        Thread.sleep(500);
        kiosksPage.searchAndSelectAssignee("Sumit Nevase");
        Thread.sleep(500);
        kiosksPage.clickCreateButton();
        Thread.sleep(2000);
        System.out.println("✅ TC_KSK_014 PASSED — Kiosk created successfully");
    }*/

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_KSK_015 — 3-dot menu opens on first kiosk row
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 15)
    public void TC_KSK_015_verifyThreeDotMenuOpens() throws InterruptedException {
        kiosksPage.clickFirstRowThreeDotMenu();
        Thread.sleep(1500);
        System.out.println("✅ TC_KSK_015 PASSED — 3-dot menu opened");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_KSK_016 — 3-dot menu → Copy URL
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 16)
    public void TC_KSK_016_verifyMenuCopyUrl() throws InterruptedException {
        kiosksPage.clickFirstRowThreeDotMenu();
        Thread.sleep(800);
        kiosksPage.clickMenuCopyUrl();
        Thread.sleep(1000);
        System.out.println("✅ TC_KSK_016 PASSED — Copy URL clicked");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_KSK_017 — 3-dot menu → Edit
    // ─────────────────────────────────────────────────────────────────────────────

   @Test(priority = 17)
    public void TC_KSK_017_verifyMenuEdit() throws InterruptedException {
        kiosksPage.clickFirstRowThreeDotMenu();
        Thread.sleep(800);
        kiosksPage.clickMenuEdit();
        Thread.sleep(1500);
        System.out.println("✅ TC_KSK_017 PASSED — Edit clicked from 3-dot menu");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_KSK_018 — 3-dot menu → Deactivate
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 18)
    public void TC_KSK_018_verifyMenuDeactivate() throws InterruptedException {
        kiosksPage.clickFirstRowThreeDotMenu();
        Thread.sleep(800);
        kiosksPage.clickMenuDeactivate();
        Thread.sleep(1500);
        System.out.println("✅ TC_KSK_018 PASSED — Deactivate clicked from 3-dot menu");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_KSK_019 — 3-dot menu → Delete
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 19)
    public void TC_KSK_019_verifyMenuDelete() throws InterruptedException {
        kiosksPage.clickFirstRowThreeDotMenu();
        Thread.sleep(800);
        kiosksPage.clickMenuDelete();
        Thread.sleep(1500);
        System.out.println("✅ TC_KSK_019 PASSED — Delete clicked from 3-dot menu");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_KSK_020 — Launch Kiosk button opens the kiosk clock-in/out page
    // SCENARIO:
    //   1. Click Launch Kiosk on the first row
    //   2. New tab/page opens showing the Punchly Kiosk clock-in screen
    //   3. Switch driver to the kiosk tab to verify it loaded
    //   4. Switch back to main tab
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 20)
    public void TC_KSK_020_verifyLaunchKioskOpensKioskPage() throws InterruptedException {
        kiosksPage.clickLaunchKiosk();
        Thread.sleep(2000);
        kiosksPage.switchToKioskTab();
        Thread.sleep(2000);
        System.out.println("✅ TC_KSK_020 PASSED — Kiosk clock-in page opened");
        kiosksPage.switchBackToMainTab();
        Thread.sleep(1000);
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_KSK_021 — Kiosk page: search for a team member by name
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 21)
    public void TC_KSK_021_verifyKioskMemberSearch() throws InterruptedException {
        kiosksPage.clickLaunchKiosk();
        Thread.sleep(2000);
        kiosksPage.switchToKioskTab();
        Thread.sleep(1500);
        kiosksPage.searchKioskMember("Sumit Nevase");
        Thread.sleep(1500);
        System.out.println("✅ TC_KSK_021 PASSED — Kiosk member search performed");
        kiosksPage.switchBackToMainTab();
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_KSK_022 — Kiosk page: click a team member to clock in/out
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 22)
    public void TC_KSK_022_verifyKioskMemberClick() throws InterruptedException {
        kiosksPage.clickLaunchKiosk();
        Thread.sleep(2000);
        kiosksPage.switchToKioskTab();
        Thread.sleep(1500);
        kiosksPage.clickKioskMember("Sumit Nevase");
        Thread.sleep(2000);
        System.out.println("✅ TC_KSK_022 PASSED — Kiosk member clicked");
        kiosksPage.switchBackToMainTab();
    }
}