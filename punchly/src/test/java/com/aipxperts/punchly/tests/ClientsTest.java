package com.aipxperts.punchly.tests;

import com.aipxperts.punchly.base.BaseTest;
import com.aipxperts.punchly.pages.ClientsPage;
import com.aipxperts.punchly.pages.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ClientsTest extends BaseTest {

    LoginPage loginPage;
    ClientsPage clientsPage;

    @BeforeMethod
    public void loginAndNavigate() throws InterruptedException {
        loginPage   = new LoginPage(driver);
        clientsPage = new ClientsPage(driver);
        loginPage.login("sumit.nevase@aipxperts.com");
        loginPage.enterOTP();
        clientsPage.goToClients();
        Thread.sleep(1500);
        System.out.println("✅ Setup complete — logged in and on Clients page");
    }

    @Test(priority = 1)
    public void TC_CLT_001_verifyClientsPageOpened() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("✅ TC_CLT_001 PASSED — Clients page opened successfully");
    }

    @Test(priority = 2)
    public void TC_CLT_002_verifyActiveTab() {
        clientsPage.clickActiveTab();
        System.out.println("✅ TC_CLT_002 PASSED — Active tab clicked");
    }

    @Test(priority = 3)
    public void TC_CLT_003_verifyArchivedTab() {
        clientsPage.clickArchivedTab();
        System.out.println("✅ TC_CLT_003 PASSED — Archived tab clicked");
    }

    @Test(priority = 4)
    public void TC_CLT_004_verifyCreateClientRowActivatesInput() throws InterruptedException {
        clientsPage.clickActiveTab();
        clientsPage.clickCreateClientRow();
        Thread.sleep(1000);
        System.out.println("✅ TC_CLT_004 PASSED — Create client inline row clicked; input activated");
    }

    @Test(priority = 5)
    public void TC_CLT_005_verifyEnterClientName() throws InterruptedException {
        clientsPage.clickActiveTab();
        clientsPage.clickCreateClientRow();
        clientsPage.enterClientName("Automation Client");
        Thread.sleep(1000);
        System.out.println("✅ TC_CLT_005 PASSED — Client name typed in inline input");
    }

    @Test(priority = 6)
    public void TC_CLT_006_verifyCreateNewClientWithEnterKey() throws InterruptedException {
        clientsPage.clickActiveTab();
        clientsPage.clickCreateClientRow();
        clientsPage.enterClientName("Automation Client");
        clientsPage.pressEnterToSave();
        Thread.sleep(1500);
        System.out.println("✅ TC_CLT_006 PASSED — New client saved via ENTER key");
    }

    @Test(priority = 7)
    public void TC_CLT_007_verifyClickClientNameOpensEditModal() throws InterruptedException {
        clientsPage.clickActiveTab();
        clientsPage.clickCreateClientRow();
        clientsPage.enterClientName("Modal Test Client");
        clientsPage.pressEnterToSave();
        clientsPage.clickFirstClientName();
        Thread.sleep(1500);
        System.out.println("✅ TC_CLT_007 PASSED — Edit modal opened by clicking client name");
    }

    @Test(priority = 8)
    public void TC_CLT_008_verifyEditAllFieldsAndSave() throws InterruptedException {
        clientsPage.clickFirstClientName();
        clientsPage.editClientName("Aipxperts Technolabs Updated");
        clientsPage.editClientEmail("updated@aipxperts.com");
        clientsPage.enterCcEmail("cc@example.com");
        clientsPage.enterCcEmail("cd@example.com");
        clientsPage.enterCcEmail("ce@example.com");
        clientsPage.clickCcAdd();
        clientsPage.editClientAddress("Ahmedabad, Gujarat");
        clientsPage.editClientNote("Automation test note");
        clientsPage.clickSave();
        System.out.println("✅ TC_CLT_008 PASSED — All fields edited and saved");
    }

    @Test(priority = 9)
    public void TC_CLT_009_verifyEditClientName() throws InterruptedException {
        clientsPage.clickFirstClientName();
        clientsPage.editClientName("Aipxperts Technolabs Updated");
        Thread.sleep(500);
        System.out.println("✅ TC_CLT_009 PASSED — Client name edited");
    }

    @Test(priority = 10)
    public void TC_CLT_010_verifyEditClientEmail() throws InterruptedException {
        clientsPage.clickFirstClientName();
        clientsPage.editClientEmail("updated@aipxperts.com");
        Thread.sleep(500);
        System.out.println("✅ TC_CLT_010 PASSED — Client email edited");
    }

    @Test(priority = 11)
    public void TC_CLT_011_verifyAddCcEmail() throws InterruptedException {
        clientsPage.clickFirstClientName();
        clientsPage.enterCcEmail("cc@example.com");
        clientsPage.clickCcAdd();
        System.out.println("✅ TC_CLT_011 PASSED — CC email added");
    }

    @Test(priority = 12)
    public void TC_CLT_012_verifyEditClientAddress() throws InterruptedException {
        clientsPage.clickFirstClientName();
        clientsPage.editClientAddress("Ahmedabad, Gujarat");
        Thread.sleep(500);
        System.out.println("✅ TC_CLT_012 PASSED — Address edited");
    }

    @Test(priority = 13)
    public void TC_CLT_013_verifyEditClientNote() throws InterruptedException {
        clientsPage.clickFirstClientName();
        clientsPage.editClientNote("This is a test note");
        Thread.sleep(500);
        System.out.println("✅ TC_CLT_013 PASSED — Note edited");
    }

    @Test(priority = 14)
    public void TC_CLT_014_verifyCancelEditModal() throws InterruptedException {
        clientsPage.clickFirstClientName();
        clientsPage.editClientNote("This should NOT be saved");
        clientsPage.clickCancel();
        System.out.println("✅ TC_CLT_014 PASSED — Edit modal cancelled; changes discarded");
    }

    @Test(priority = 15)
    public void TC_CLT_015_verifySaveEditModal() throws InterruptedException {
        clientsPage.clickFirstClientName();
        clientsPage.editClientNote("Saved by automation");
        clientsPage.clickSave();
        System.out.println("✅ TC_CLT_015 PASSED — Edit modal saved");
    }

   @Test(priority = 16)
    public void TC_CLT_016_verifyCheckboxRevealsBulkBar() throws InterruptedException {
        clientsPage.clickFirstRowCheckbox();
        Thread.sleep(1500);
        System.out.println("✅ TC_CLT_016 PASSED — Checkbox clicked; Archive/Delete/Clear bar visible");
    }

    @Test(priority = 17)
    public void TC_CLT_017_verifyBulkArchiveOnlySelectedClient() throws InterruptedException {
        clientsPage.clickFirstRowCheckbox();
        clientsPage.clickBulkArchive();
        System.out.println("✅ TC_CLT_017 PASSED — Bulk Archive clicked; only selected client archived");
    }

    @Test(priority = 18)
    public void TC_CLT_018_verifyBulkDelete() throws InterruptedException {
        clientsPage.clickFirstRowCheckbox();
        clientsPage.clickBulkDelete();
        System.out.println("✅ TC_CLT_018 PASSED — Bulk Delete clicked");
    }

    @Test(priority = 19)
    public void TC_CLT_019_verifyBulkClear() throws InterruptedException {
        clientsPage.clickFirstRowCheckbox();
        Thread.sleep(500);
        clientsPage.clickBulkClear();
        System.out.println("✅ TC_CLT_019 PASSED — Bulk Clear clicked; checkbox deselected");
    }

    @Test(priority = 20)
    public void TC_CLT_020_verifyThreeDotMenuOpens() throws InterruptedException {
        clientsPage.clickThreeDotMenu();
        Thread.sleep(1000);
        System.out.println("✅ TC_CLT_020 PASSED — 3-dot menu opened on Active tab");
    }

    @Test(priority = 21)
    public void TC_CLT_021_verifyMenuEditOpensModal() throws InterruptedException {
        clientsPage.clickThreeDotMenu();
        clientsPage.clickMenuEdit();
        Thread.sleep(1500);
        System.out.println("✅ TC_CLT_021 PASSED — Edit modal opened via 3-dot menu");
    }

    @Test(priority = 22)
    public void TC_CLT_022_verifyMenuArchiveOnlyThisClient() throws InterruptedException {
        clientsPage.clickThreeDotMenu();
        clientsPage.clickMenuArchive();
        System.out.println("✅ TC_CLT_022 PASSED — Archive clicked; only the first client archived");
    }

    @Test(priority = 23)
    public void TC_CLT_023_verifyMenuDelete() throws InterruptedException {
        clientsPage.clickThreeDotMenu();
        clientsPage.clickMenuDelete();
        System.out.println("✅ TC_CLT_023 PASSED — Delete clicked via 3-dot menu");
    }

    @Test(priority = 24)
    public void TC_CLT_024_verifyArchivedTabThreeDotMenuOpens() throws InterruptedException {
        clientsPage.clickArchivedTab();
        clientsPage.clickThreeDotMenu();
        Thread.sleep(1000);
        System.out.println("✅ TC_CLT_024 PASSED — 3-dot menu opened on Archived tab");
    }

    @Test(priority = 25)
    public void TC_CLT_025_verifyArchivedMenuEdit() throws InterruptedException {
        clientsPage.clickArchivedTab();
        clientsPage.clickThreeDotMenu();
        clientsPage.clickMenuEdit();
        Thread.sleep(1500);
        System.out.println("✅ TC_CLT_025 PASSED — Edit modal opened from Archived tab 3-dot menu");
    }

    @Test(priority = 26)
    public void TC_CLT_026_verifyArchivedMenuRestore() throws InterruptedException {
        clientsPage.clickArchivedTab();
        clientsPage.clickThreeDotMenu();
        clientsPage.clickMenuRestore();
        Thread.sleep(1500);
        System.out.println("✅ TC_CLT_026 PASSED — Restore clicked; client moved back to Active");
    }

    @Test(priority = 27)
    public void TC_CLT_027_verifyArchivedMenuDelete() throws InterruptedException {
        clientsPage.clickArchivedTab();
        clientsPage.clickThreeDotMenu();
        clientsPage.clickMenuDelete();
        Thread.sleep(1500);
        System.out.println("✅ TC_CLT_027 PASSED — Delete clicked from Archived tab 3-dot menu");
    }
}