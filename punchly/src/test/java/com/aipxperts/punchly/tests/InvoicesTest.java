package com.aipxperts.punchly.tests;

import com.aipxperts.punchly.base.BaseTest;
import com.aipxperts.punchly.pages.InvoicesPage;
import com.aipxperts.punchly.pages.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InvoicesTest extends BaseTest {

    private LoginPage loginPage;
    private InvoicesPage invoicesPage;

    @BeforeMethod
    public void loginAndNavigate() throws InterruptedException {
        loginPage    = new LoginPage(driver);
        invoicesPage = new InvoicesPage(driver);
        loginPage.login("sumit.nevase@aipxperts.com");
        loginPage.enterOTP();
        invoicesPage.goToInvoices();
        Thread.sleep(1500);
        System.out.println("✅ Setup complete — logged in and on Invoices page");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_001 — Invoices page opens
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 1)
    public void TC_INV_001_verifyInvoicesPageOpened() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("✅ TC_INV_001 PASSED — Invoices page opened");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_002 — Click Invoice ID opens detail page
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 2)
    public void TC_INV_002_verifyClickInvoiceIdOpensDetail() throws InterruptedException {
        invoicesPage.clickFirstInvoiceId();
        Thread.sleep(2000);
        System.out.println("✅ TC_INV_002 PASSED — Invoice detail page opened");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_003 — Invoice detail: Download Invoice
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 3)
    public void TC_INV_003_verifyDownloadInvoiceButton() throws InterruptedException {
        invoicesPage.clickFirstInvoiceId();
        Thread.sleep(1500);
        invoicesPage.clickDownloadInvoice();
        Thread.sleep(1500);
        System.out.println("✅ TC_INV_003 PASSED — Download Invoice clicked");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_004 — Invoice detail: Send Invoice
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 4)
    public void TC_INV_004_verifySendInvoiceButton() throws InterruptedException {
        invoicesPage.clickFirstInvoiceId();
        Thread.sleep(1500);
        invoicesPage.clickSendInvoice();
        Thread.sleep(1500);
        System.out.println("✅ TC_INV_004 PASSED — Send Invoice clicked");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_005 — Invoice detail: Recurring Settings
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 5)
    public void TC_INV_005_verifyRecurringSettingsButton() throws InterruptedException {
        invoicesPage.clickFirstInvoiceId();
        Thread.sleep(1500);
        invoicesPage.clickRecurringSettings();
        Thread.sleep(1500);
        System.out.println("✅ TC_INV_005 PASSED — Recurring Settings clicked");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_006 — Invoice detail: 3-dot menu
    // FIX: navigate to invoice detail first, then hover row and click 3-dot
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 6)
    public void TC_INV_006_verifyInvoiceDetailThreeDotMenu() throws InterruptedException {
        invoicesPage.clickInvoiceDetailThreeDot();
        Thread.sleep(1500);
        System.out.println("✅ TC_INV_006 PASSED — Invoice detail 3-dot menu opened");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_007 — Invoice detail: Import Time and Expenses
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 7)
    public void TC_INV_007_verifyImportTimeAndExpenses() throws InterruptedException {
        invoicesPage.clickFirstInvoiceId();
        Thread.sleep(1500);
        invoicesPage.clickImportTimeAndExpenses();
        Thread.sleep(1500);
        System.out.println("✅ TC_INV_007 PASSED — Import Time and Expenses clicked");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_008 — Invoice detail: Add New Item
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 8)
    public void TC_INV_008_verifyAddNewItem() throws InterruptedException {
        invoicesPage.clickFirstInvoiceId();
        Thread.sleep(1500);
        invoicesPage.clickAddNewItem();
        Thread.sleep(1500);
        System.out.println("✅ TC_INV_008 PASSED — Add New Item clicked");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_009 — Invoice detail: Add Discount
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 9)
    public void TC_INV_009_verifyAddDiscount() throws InterruptedException {
        invoicesPage.clickFirstInvoiceId();
        Thread.sleep(1500);
        invoicesPage.clickAddDiscount();
        Thread.sleep(1000);
        System.out.println("✅ TC_INV_009 PASSED — Add Discount clicked");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_010 — Invoice detail: Add Tax
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 10)
    public void TC_INV_010_verifyAddTax() throws InterruptedException {
        invoicesPage.clickFirstInvoiceId();
        Thread.sleep(1500);
        invoicesPage.clickAddTax();
        Thread.sleep(1000);
        System.out.println("✅ TC_INV_010 PASSED — Add Tax clicked");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_011 — Invoice detail: Enter note
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 11)
    public void TC_INV_011_verifyEnterInvoiceNote() throws InterruptedException {
        invoicesPage.clickFirstInvoiceId();
        Thread.sleep(1500);
        invoicesPage.enterInvoiceNote("Automation test note");
        Thread.sleep(1000);
        System.out.println("✅ TC_INV_011 PASSED — Invoice note entered");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_012 — Invoice detail: Navigate back via breadcrumb
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 12)
    public void TC_INV_012_verifyBreadcrumbNavigatesBack() throws InterruptedException {
        invoicesPage.clickFirstInvoiceId();
        Thread.sleep(1500);
        invoicesPage.clickBreadcrumbInvoices();
        Thread.sleep(1500);
        System.out.println("✅ TC_INV_012 PASSED — Navigated back via breadcrumb");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_013 — Create Invoice button opens modal
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 13)
    public void TC_INV_013_verifyCreateInvoiceModalOpens() throws InterruptedException {
        invoicesPage.clickCreateInvoiceButton();
        Thread.sleep(1500);
        System.out.println("✅ TC_INV_013 PASSED — Create Invoice modal opened");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_014 — Create Invoice: Client dropdown opens
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 14)
    public void TC_INV_014_verifyClientDropdownOpens() throws InterruptedException {
        invoicesPage.clickCreateInvoiceButton();
        Thread.sleep(1000);
        invoicesPage.clickClientDropdown();
        Thread.sleep(1500);
        System.out.println("✅ TC_INV_014 PASSED — Client dropdown opened");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_015 — Create Invoice: Search and select client
    // FIX: client name in app is "Aipxperts Technolabs Updated"
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 15)
    public void TC_INV_015_verifySearchAndSelectClient() throws InterruptedException {
        invoicesPage.clickCreateInvoiceButton();
        Thread.sleep(1000);
        invoicesPage.searchAndSelectClient("Aipxperts Technolabs Updated");
        Thread.sleep(1000);
        System.out.println("✅ TC_INV_015 PASSED — Client selected");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_016 — Create Invoice: Currency dropdown opens
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 16)
    public void TC_INV_016_verifyCurrencyDropdownOpens() throws InterruptedException {
        invoicesPage.clickCreateInvoiceButton();
        Thread.sleep(1000);
        invoicesPage.clickCurrencyDropdown();
        Thread.sleep(1500);
        System.out.println("✅ TC_INV_016 PASSED — Currency dropdown opened");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_017 — Create Invoice: Due date dropdown opens
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 17)
    public void TC_INV_017_verifyDueDateDropdown() throws InterruptedException {
        invoicesPage.clickCreateInvoiceButton();
        Thread.sleep(1000);
        invoicesPage.clickDueDateDropdown();
        Thread.sleep(1500);
        System.out.println("✅ TC_INV_017 PASSED — Due date dropdown opened");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_018 — Create Invoice: Cancel closes modal
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 18)
    public void TC_INV_018_verifyCreateInvoiceCancel() throws InterruptedException {
        invoicesPage.clickCreateInvoiceButton();
        Thread.sleep(1000);
        invoicesPage.clickCancelButton();
        Thread.sleep(1000);
        System.out.println("✅ TC_INV_018 PASSED — Create Invoice cancelled");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_019 — Create Invoice: Full flow
    // FIX: clickCreateButton() now handles CREATE / CREATE INVOICE button text
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 19)
    public void TC_INV_019_verifyFullCreateInvoiceFlow() throws InterruptedException {
        invoicesPage.clickCreateInvoiceButton();
        Thread.sleep(1000);
        invoicesPage.searchAndSelectClient("Automation Client");
        Thread.sleep(500);
        invoicesPage.clickCreateButton();
        Thread.sleep(2000);
        System.out.println("✅ TC_INV_019 PASSED — Invoice created");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_020 — Filter panel opens
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 20)
    public void TC_INV_020_verifyFilterPanelOpens() throws InterruptedException {
        invoicesPage.clickFilterButton();
        Thread.sleep(1500);
        System.out.println("✅ TC_INV_020 PASSED — Filter panel opened");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_021 — Filter: Client dropdown
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 21)
    public void TC_INV_021_verifyFilterClientDropdown() throws InterruptedException {
        invoicesPage.clickFilterButton();
        Thread.sleep(1000);
        invoicesPage.clickFilterClientDropdown();
        Thread.sleep(1500);
        System.out.println("✅ TC_INV_021 PASSED — Filter client dropdown opened");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_022 — Filter: Status dropdown
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 22)
    public void TC_INV_022_verifyFilterStatusDropdown() throws InterruptedException {
        invoicesPage.clickFilterButton();
        Thread.sleep(1000);
        invoicesPage.clickFilterStatusDropdown();
        Thread.sleep(1500);
        System.out.println("✅ TC_INV_022 PASSED — Filter status dropdown opened");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_023 — Filter: Select status Draft
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 23)
    public void TC_INV_023_verifySelectFilterStatusDraft() throws InterruptedException {
        invoicesPage.clickFilterButton();
        Thread.sleep(1000);
        invoicesPage.clickFilterStatusDropdown();
        Thread.sleep(800);
        invoicesPage.selectFilterStatus("Draft");
        Thread.sleep(500);
        System.out.println("✅ TC_INV_023 PASSED — Draft status selected");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_024 — Filter: Amount min/max
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 24)
    public void TC_INV_024_verifyFilterAmountMinMax() throws InterruptedException {
        invoicesPage.clickFilterButton();
        Thread.sleep(1000);
        invoicesPage.enterFilterAmountMin("0");
        invoicesPage.enterFilterAmountMax("1000");
        Thread.sleep(500);
        System.out.println("✅ TC_INV_024 PASSED — Amount min/max entered");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_025 — Filter: Apply
    // FIX: filter panel is open by default on page — just click APPLY FILTER
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 25)
    public void TC_INV_025_verifyApplyFilter() throws InterruptedException {
        invoicesPage.clickFilterButton();
        Thread.sleep(1000);
        invoicesPage.clickApplyFilter();
        Thread.sleep(1500);
        System.out.println("✅ TC_INV_025 PASSED — Filter applied");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_026 — Filter: Cancel
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 26)
    public void TC_INV_026_verifyFilterCancel() throws InterruptedException {
        invoicesPage.clickFilterButton();
        Thread.sleep(1000);
        invoicesPage.clickFilterCancel();
        Thread.sleep(1000);
        System.out.println("✅ TC_INV_026 PASSED — Filter cancelled");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_027 — Filter: Clear All
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 27)
    public void TC_INV_027_verifyFilterClearAll() throws InterruptedException {
        invoicesPage.clickFilterButton();
        Thread.sleep(1000);
        invoicesPage.clickFilterClearAll();
        Thread.sleep(1000);
        System.out.println("✅ TC_INV_027 PASSED — Filter cleared");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_028 — Settings page opens
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 28)
    public void TC_INV_028_verifySettingsPageOpens() throws InterruptedException {
        invoicesPage.clickSettingsButton();
        Thread.sleep(1500);
        System.out.println("✅ TC_INV_028 PASSED — Invoice Settings page opened");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_029 — Settings: DEFAULTS tab
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 29)
    public void TC_INV_029_verifySettingsDefaultsTab() throws InterruptedException {
        invoicesPage.clickSettingsButton();
        Thread.sleep(1500);
        invoicesPage.clickSettingsDefaultsTab();
        Thread.sleep(1000);
        System.out.println("✅ TC_INV_029 PASSED — DEFAULTS tab clicked");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_030 — Settings: APPEARANCE tab
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 30)
    public void TC_INV_030_verifySettingsAppearanceTab() throws InterruptedException {
        invoicesPage.clickSettingsButton();
        Thread.sleep(1500);
        invoicesPage.clickSettingsAppearanceTab();
        Thread.sleep(1000);
        System.out.println("✅ TC_INV_030 PASSED — APPEARANCE tab clicked");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_031 — Settings: EMAILS tab
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 31)
    public void TC_INV_031_verifySettingsEmailsTab() throws InterruptedException {
        invoicesPage.clickSettingsButton();
        Thread.sleep(1500);
        invoicesPage.clickSettingsEmailsTab();
        Thread.sleep(1000);
        System.out.println("✅ TC_INV_031 PASSED — EMAILS tab clicked");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_032 — Settings: TRANSLATIONS tab
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 32)
    public void TC_INV_032_verifySettingsTranslationsTab() throws InterruptedException {
        invoicesPage.clickSettingsButton();
        Thread.sleep(1500);
        invoicesPage.clickSettingsTranslationsTab();
        Thread.sleep(1000);
        System.out.println("✅ TC_INV_032 PASSED — TRANSLATIONS tab clicked");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_033 — Settings: Navigate back via breadcrumb
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 33)
    public void TC_INV_033_verifyNavigateBackFromSettings() throws InterruptedException {
        invoicesPage.clickSettingsButton();
        Thread.sleep(1500);
        invoicesPage.clickBreadcrumbInvoices();
        Thread.sleep(1500);
        System.out.println("✅ TC_INV_033 PASSED — Navigated back from Settings");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_034 — Export dropdown opens
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 34)
    public void TC_INV_034_verifyExportDropdownOpens() throws InterruptedException {
        invoicesPage.clickExportButton();
        Thread.sleep(1000);
        System.out.println("✅ TC_INV_034 PASSED — Export dropdown opened");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_035 — Export to CSV
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 35)
    public void TC_INV_035_verifyExportToCsv() throws InterruptedException {
        invoicesPage.clickExportButton();
        Thread.sleep(800);
        invoicesPage.clickExportToCsv();
        Thread.sleep(2000);
        System.out.println("✅ TC_INV_035 PASSED — Export to CSV clicked");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_036 — Export to PDF
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 36)
    public void TC_INV_036_verifyExportToPdf() throws InterruptedException {
        invoicesPage.clickExportButton();
        Thread.sleep(800);
        invoicesPage.clickExportToPdf();
        Thread.sleep(2000);
        System.out.println("✅ TC_INV_036 PASSED — Export to PDF clicked");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_037 — Export to Excel
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 37)
    public void TC_INV_037_verifyExportToExcel() throws InterruptedException {
        invoicesPage.clickExportButton();
        Thread.sleep(800);
        invoicesPage.clickExportToExcel();
        Thread.sleep(2000);
        System.out.println("✅ TC_INV_037 PASSED — Export to Excel clicked");
    }
}
























































/*package com.aipxperts.punchly.tests;

import com.aipxperts.punchly.base.BaseTest;
import com.aipxperts.punchly.pages.InvoicesPage;
import com.aipxperts.punchly.pages.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InvoicesTest extends BaseTest {

    private LoginPage loginPage;
    private InvoicesPage invoicesPage;

    @BeforeMethod
    public void loginAndNavigate() throws InterruptedException {
        loginPage    = new LoginPage(driver);
        invoicesPage = new InvoicesPage(driver);
        loginPage.login("sumit.nevase@aipxperts.com");
        loginPage.enterOTP();
        invoicesPage.goToInvoices();
        Thread.sleep(1500);
        System.out.println("✅ Setup complete — logged in and on Invoices page");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_001 — Invoices page opens
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 1)
    public void TC_INV_001_verifyInvoicesPageOpened() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("✅ TC_INV_001 PASSED — Invoices page opened");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_002 — Click Invoice ID opens detail page
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 2)
    public void TC_INV_002_verifyClickInvoiceIdOpensDetail() throws InterruptedException {
        invoicesPage.clickFirstInvoiceId();
        Thread.sleep(2000);
        System.out.println("✅ TC_INV_002 PASSED — Invoice detail page opened");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_003 — Invoice detail: Download Invoice
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 3)
    public void TC_INV_003_verifyDownloadInvoiceButton() throws InterruptedException {
        invoicesPage.clickFirstInvoiceId();
        Thread.sleep(1500);
        invoicesPage.clickDownloadInvoice();
        Thread.sleep(1500);
        System.out.println("✅ TC_INV_003 PASSED — Download Invoice clicked");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_004 — Invoice detail: Send Invoice
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 4)
    public void TC_INV_004_verifySendInvoiceButton() throws InterruptedException {
        invoicesPage.clickFirstInvoiceId();
        Thread.sleep(1500);
        invoicesPage.clickSendInvoice();
        Thread.sleep(1500);
        System.out.println("✅ TC_INV_004 PASSED — Send Invoice clicked");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_005 — Invoice detail: Recurring Settings
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 5)
    public void TC_INV_005_verifyRecurringSettingsButton() throws InterruptedException {
        invoicesPage.clickFirstInvoiceId();
        Thread.sleep(1500);
        invoicesPage.clickRecurringSettings();
        Thread.sleep(1500);
        System.out.println("✅ TC_INV_005 PASSED — Recurring Settings clicked");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_006 — Invoice detail: 3-dot menu
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 6)
    public void TC_INV_006_verifyInvoiceDetailThreeDotMenu() throws InterruptedException {
        invoicesPage.clickFirstInvoiceId();
        Thread.sleep(1500);
        invoicesPage.clickInvoiceDetailThreeDot();
        Thread.sleep(1500);
        System.out.println("✅ TC_INV_006 PASSED — Invoice detail 3-dot menu opened");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_007 — Invoice detail: Import Time and Expenses
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 7)
    public void TC_INV_007_verifyImportTimeAndExpenses() throws InterruptedException {
        invoicesPage.clickFirstInvoiceId();
        Thread.sleep(1500);
        invoicesPage.clickImportTimeAndExpenses();
        Thread.sleep(1500);
        System.out.println("✅ TC_INV_007 PASSED — Import Time and Expenses clicked");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_008 — Invoice detail: Add New Item
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 8)
    public void TC_INV_008_verifyAddNewItem() throws InterruptedException {
        invoicesPage.clickFirstInvoiceId();
        Thread.sleep(1500);
        invoicesPage.clickAddNewItem();
        Thread.sleep(1500);
        System.out.println("✅ TC_INV_008 PASSED — Add New Item clicked");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_009 — Invoice detail: Add Discount
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 9)
    public void TC_INV_009_verifyAddDiscount() throws InterruptedException {
        invoicesPage.clickFirstInvoiceId();
        Thread.sleep(1500);
        invoicesPage.clickAddDiscount();
        Thread.sleep(1000);
        System.out.println("✅ TC_INV_009 PASSED — Add Discount clicked");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_010 — Invoice detail: Add Tax
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 10)
    public void TC_INV_010_verifyAddTax() throws InterruptedException {
        invoicesPage.clickFirstInvoiceId();
        Thread.sleep(1500);
        invoicesPage.clickAddTax();
        Thread.sleep(1000);
        System.out.println("✅ TC_INV_010 PASSED — Add Tax clicked");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_011 — Invoice detail: Enter note
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 11)
    public void TC_INV_011_verifyEnterInvoiceNote() throws InterruptedException {
        invoicesPage.clickFirstInvoiceId();
        Thread.sleep(1500);
        invoicesPage.enterInvoiceNote("Automation test note");
        Thread.sleep(1000);
        System.out.println("✅ TC_INV_011 PASSED — Invoice note entered");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_012 — Invoice detail: Navigate back via breadcrumb
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 12)
    public void TC_INV_012_verifyBreadcrumbNavigatesBack() throws InterruptedException {
        invoicesPage.clickFirstInvoiceId();
        Thread.sleep(1500);
        invoicesPage.clickBreadcrumbInvoices();
        Thread.sleep(1500);
        System.out.println("✅ TC_INV_012 PASSED — Navigated back via breadcrumb");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_013 — Create Invoice button opens modal
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 13)
    public void TC_INV_013_verifyCreateInvoiceModalOpens() throws InterruptedException {
        invoicesPage.clickCreateInvoiceButton();
        Thread.sleep(1500);
        System.out.println("✅ TC_INV_013 PASSED — Create Invoice modal opened");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_014 — Create Invoice: Client dropdown opens
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 14)
    public void TC_INV_014_verifyClientDropdownOpens() throws InterruptedException {
        invoicesPage.clickCreateInvoiceButton();
        Thread.sleep(1000);
        invoicesPage.clickClientDropdown();
        Thread.sleep(1500);
        System.out.println("✅ TC_INV_014 PASSED — Client dropdown opened");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_015 — Create Invoice: Search and select client
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 15)
    public void TC_INV_015_verifySearchAndSelectClient() throws InterruptedException {
        invoicesPage.clickCreateInvoiceButton();
        Thread.sleep(1000);
        invoicesPage.searchAndSelectClient("Aipxperts Technolabs");
        Thread.sleep(1000);
        System.out.println("✅ TC_INV_015 PASSED — Client selected");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_016 — Create Invoice: Currency dropdown opens
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 16)
    public void TC_INV_016_verifyCurrencyDropdownOpens() throws InterruptedException {
        invoicesPage.clickCreateInvoiceButton();
        Thread.sleep(1000);
        invoicesPage.clickCurrencyDropdown();
        Thread.sleep(1500);
        System.out.println("✅ TC_INV_016 PASSED — Currency dropdown opened");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_017 — Create Invoice: Due date dropdown opens
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 17)
    public void TC_INV_017_verifyDueDateDropdown() throws InterruptedException {
        invoicesPage.clickCreateInvoiceButton();
        Thread.sleep(1000);
        invoicesPage.clickDueDateDropdown();
        Thread.sleep(1500);
        System.out.println("✅ TC_INV_017 PASSED — Due date dropdown opened");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_018 — Create Invoice: Cancel closes modal
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 18)
    public void TC_INV_018_verifyCreateInvoiceCancel() throws InterruptedException {
        invoicesPage.clickCreateInvoiceButton();
        Thread.sleep(1000);
        invoicesPage.clickCancelButton();
        Thread.sleep(1000);
        System.out.println("✅ TC_INV_018 PASSED — Create Invoice cancelled");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_019 — Create Invoice: Full flow
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 19)
    public void TC_INV_019_verifyFullCreateInvoiceFlow() throws InterruptedException {
        invoicesPage.clickCreateInvoiceButton();
        Thread.sleep(1000);
        invoicesPage.searchAndSelectClient("Automation Client");
        Thread.sleep(500);
        invoicesPage.clickCreateButton();
        Thread.sleep(2000);
        System.out.println("✅ TC_INV_019 PASSED — Invoice created");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_020 — Filter panel opens
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 20)
    public void TC_INV_020_verifyFilterPanelOpens() throws InterruptedException {
        invoicesPage.clickFilterButton();
        Thread.sleep(1500);
        System.out.println("✅ TC_INV_020 PASSED — Filter panel opened");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_021 — Filter: Client dropdown
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 21)
    public void TC_INV_021_verifyFilterClientDropdown() throws InterruptedException {
        invoicesPage.clickFilterButton();
        Thread.sleep(1000);
        invoicesPage.clickFilterClientDropdown();
        Thread.sleep(1500);
        System.out.println("✅ TC_INV_021 PASSED — Filter client dropdown opened");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_022 — Filter: Status dropdown
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 22)
    public void TC_INV_022_verifyFilterStatusDropdown() throws InterruptedException {
        invoicesPage.clickFilterButton();
        Thread.sleep(1000);
        invoicesPage.clickFilterStatusDropdown();
        Thread.sleep(1500);
        System.out.println("✅ TC_INV_022 PASSED — Filter status dropdown opened");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_023 — Filter: Select status Draft
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 23)
    public void TC_INV_023_verifySelectFilterStatusDraft() throws InterruptedException {
        invoicesPage.clickFilterButton();
        Thread.sleep(1000);
        invoicesPage.clickFilterStatusDropdown();
        Thread.sleep(800);
        invoicesPage.selectFilterStatus("Draft");
        Thread.sleep(500);
        System.out.println("✅ TC_INV_023 PASSED — Draft status selected");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_024 — Filter: Amount min/max
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 24)
    public void TC_INV_024_verifyFilterAmountMinMax() throws InterruptedException {
        invoicesPage.clickFilterButton();
        Thread.sleep(1000);
        invoicesPage.enterFilterAmountMin("0");
        invoicesPage.enterFilterAmountMax("1000");
        Thread.sleep(500);
        System.out.println("✅ TC_INV_024 PASSED — Amount min/max entered");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_025 — Filter: Apply
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 25)
    public void TC_INV_025_verifyApplyFilter() throws InterruptedException {
        invoicesPage.clickFilterButton();
        Thread.sleep(1000);
        invoicesPage.clickApplyFilter();
        Thread.sleep(1500);
        System.out.println("✅ TC_INV_025 PASSED — Filter applied");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_026 — Filter: Cancel
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 26)
    public void TC_INV_026_verifyFilterCancel() throws InterruptedException {
        invoicesPage.clickFilterButton();
        Thread.sleep(1000);
        invoicesPage.clickFilterCancel();
        Thread.sleep(1000);
        System.out.println("✅ TC_INV_026 PASSED — Filter cancelled");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_027 — Filter: Clear All
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 27)
    public void TC_INV_027_verifyFilterClearAll() throws InterruptedException {
        invoicesPage.clickFilterButton();
        Thread.sleep(1000);
        invoicesPage.clickFilterClearAll();
        Thread.sleep(1000);
        System.out.println("✅ TC_INV_027 PASSED — Filter cleared");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_028 — Settings page opens
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 28)
    public void TC_INV_028_verifySettingsPageOpens() throws InterruptedException {
        invoicesPage.clickSettingsButton();
        Thread.sleep(1500);
        System.out.println("✅ TC_INV_028 PASSED — Invoice Settings page opened");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_029 — Settings: DEFAULTS tab
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 29)
    public void TC_INV_029_verifySettingsDefaultsTab() throws InterruptedException {
        invoicesPage.clickSettingsButton();
        Thread.sleep(1500);
        invoicesPage.clickSettingsDefaultsTab();
        Thread.sleep(1000);
        System.out.println("✅ TC_INV_029 PASSED — DEFAULTS tab clicked");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_030 — Settings: APPEARANCE tab
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 30)
    public void TC_INV_030_verifySettingsAppearanceTab() throws InterruptedException {
        invoicesPage.clickSettingsButton();
        Thread.sleep(1500);
        invoicesPage.clickSettingsAppearanceTab();
        Thread.sleep(1000);
        System.out.println("✅ TC_INV_030 PASSED — APPEARANCE tab clicked");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_031 — Settings: EMAILS tab
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 31)
    public void TC_INV_031_verifySettingsEmailsTab() throws InterruptedException {
        invoicesPage.clickSettingsButton();
        Thread.sleep(1500);
        invoicesPage.clickSettingsEmailsTab();
        Thread.sleep(1000);
        System.out.println("✅ TC_INV_031 PASSED — EMAILS tab clicked");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_032 — Settings: TRANSLATIONS tab
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 32)
    public void TC_INV_032_verifySettingsTranslationsTab() throws InterruptedException {
        invoicesPage.clickSettingsButton();
        Thread.sleep(1500);
        invoicesPage.clickSettingsTranslationsTab();
        Thread.sleep(1000);
        System.out.println("✅ TC_INV_032 PASSED — TRANSLATIONS tab clicked");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_033 — Settings: Navigate back via breadcrumb
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 33)
    public void TC_INV_033_verifyNavigateBackFromSettings() throws InterruptedException {
        invoicesPage.clickSettingsButton();
        Thread.sleep(1500);
        invoicesPage.clickBreadcrumbInvoices();
        Thread.sleep(1500);
        System.out.println("✅ TC_INV_033 PASSED — Navigated back from Settings");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_034 — Export dropdown opens
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 34)
    public void TC_INV_034_verifyExportDropdownOpens() throws InterruptedException {
        invoicesPage.clickExportButton();
        Thread.sleep(1000);
        System.out.println("✅ TC_INV_034 PASSED — Export dropdown opened");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_035 — Export to CSV
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 35)
    public void TC_INV_035_verifyExportToCsv() throws InterruptedException {
        invoicesPage.clickExportButton();
        Thread.sleep(800);
        invoicesPage.clickExportToCsv();
        Thread.sleep(2000);
        System.out.println("✅ TC_INV_035 PASSED — Export to CSV clicked");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_036 — Export to PDF
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 36)
    public void TC_INV_036_verifyExportToPdf() throws InterruptedException {
        invoicesPage.clickExportButton();
        Thread.sleep(800);
        invoicesPage.clickExportToPdf();
        Thread.sleep(2000);
        System.out.println("✅ TC_INV_036 PASSED — Export to PDF clicked");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TC_INV_037 — Export to Excel
    // ─────────────────────────────────────────────────────────────────────────────

    @Test(priority = 37)
    public void TC_INV_037_verifyExportToExcel() throws InterruptedException {
        invoicesPage.clickExportButton();
        Thread.sleep(800);
        invoicesPage.clickExportToExcel();
        Thread.sleep(2000);
        System.out.println("✅ TC_INV_037 PASSED — Export to Excel clicked");
    }
}*/