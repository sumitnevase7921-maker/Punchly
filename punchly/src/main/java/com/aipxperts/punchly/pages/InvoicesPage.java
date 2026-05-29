package com.aipxperts.punchly.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InvoicesPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final JavascriptExecutor js;
    private final Actions actions;

    public InvoicesPage(WebDriver driver) {
        this.driver  = driver;
        this.wait    = new WebDriverWait(driver, Duration.ofSeconds(25));
        this.js      = (JavascriptExecutor) driver;
        this.actions = new Actions(driver);
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // HELPERS
    // ─────────────────────────────────────────────────────────────────────────────

    private void safeClick(By locator, String label) {
        try {
            WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", el);
            pause(300);
            js.executeScript("arguments[0].click();", el);
            System.out.println("✅ Clicked: " + label);
        } catch (Exception e) {
            System.out.println("⚠ safeClick failed [" + label + "]: " + e.getMessage());
            throw new RuntimeException("❌ Could not click: " + label, e);
        }
    }

    private void jsClick(By locator, String label) {
        try {
            WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", el);
            pause(300);
            js.executeScript("arguments[0].click();", el);
            System.out.println("✅ Clicked: " + label);
        } catch (Exception e) {
            System.out.println("⚠ jsClick failed [" + label + "]: " + e.getMessage());
            throw new RuntimeException("❌ Could not click: " + label, e);
        }
    }

    private void fillField(By locator, String value, String label) {
        try {
            WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", el);
            pause(200);
            js.executeScript("arguments[0].value='';", el);
            el.click();
            pause(200);
            el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            el.sendKeys(Keys.DELETE);
            actions.moveToElement(el).click().sendKeys(value).perform();
            pause(400);
            System.out.println("✅ Filled [" + label + "]: " + value);
        } catch (Exception e) {
            System.out.println("⚠ fillField failed [" + label + "]: " + e.getMessage());
            throw new RuntimeException("❌ Could not fill field: " + label, e);
        }
    }

    private void menuClick(By locator, String label) {
        try {
            WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            pause(400);
            js.executeScript("arguments[0].click();", el);
            pause(800);
            System.out.println("✅ Menu clicked: " + label);
        } catch (Exception e) {
            System.out.println("⚠ menuClick failed [" + label + "]: " + e.getMessage());
            throw new RuntimeException("❌ Could not click: " + label, e);
        }
    }

    private void pause(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // NAVIGATION
    // ─────────────────────────────────────────────────────────────────────────────

    public void goToInvoices() {
        By sidebarInvoices = By.xpath(
            "//span[normalize-space()='INVOICES'] | //span[normalize-space()='Invoices']");
        try {
            WebElement link = wait.until(ExpectedConditions.elementToBeClickable(sidebarInvoices));
            js.executeScript("arguments[0].click();", link);
            pause(1500);
            System.out.println("✅ Navigated to Invoices page");
        } catch (Exception e) {
            throw new RuntimeException("❌ Invoices sidebar link not found.", e);
        }
    }

    public void clickBreadcrumbInvoices() {
        By loc = By.xpath(
            "//a[normalize-space()='Invoices'] | " +
            "//button[normalize-space()='Invoices'] | " +
            "//*[contains(@class,'breadcrumb') and normalize-space()='Invoices'] | " +
            "(//*[normalize-space()='Invoices'])[1]");
        jsClick(loc, "Breadcrumb Invoices");
        pause(1500);
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // INVOICE LIST
    // ─────────────────────────────────────────────────────────────────────────────

    public void clickFirstInvoiceId() {
        // FIX: original had broken XPath with mismatched quotes
        By loc = By.xpath(
            "//td[contains(@class,'p-2') and contains(@class,'align-middle') and contains(@class,'whitespace-nowrap')] | " +
            "(//tbody//tr[1]//td)[1]");
        safeClick(loc, "First Invoice ID");
        pause(1500);
    }

    public void searchInvoiceById(String invoiceId) {
        By loc = By.xpath("//input[@placeholder='Invoice ID']");
        fillField(loc, invoiceId, "Invoice ID Search");
        pause(1000);
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // INVOICE DETAIL PAGE
    // ─────────────────────────────────────────────────────────────────────────────

    public void clickDownloadInvoice() {
        By loc = By.xpath(
            "//button[contains(.,'DOWNLOAD INVOICE') or contains(.,'Download Invoice')]");
        safeClick(loc, "Download Invoice");
        pause(1000);
    }

    public void clickSendInvoice() {
        By loc = By.xpath(
            "//button[contains(.,'SEND INVOICE') or contains(.,'Send Invoice')]");
        safeClick(loc, "Send Invoice");
        pause(1000);
    }

    public void clickRecurringSettings() {
        By loc = By.xpath(
            "//button[contains(.,'Recurring settings') or contains(.,'Recurring Settings')]");
        safeClick(loc, "Recurring Settings");
        pause(1000);
    }

    // FIX TC_INV_006: hover row first then click 3-dot
    public void clickInvoiceDetailThreeDot() {
        By rowLoc = By.xpath("(//tbody//tr)[1]");
        By dotLoc = By.xpath("(//tbody//tr)[1]//button[last()]");
        try {
            WebElement row = wait.until(ExpectedConditions.visibilityOfElementLocated(rowLoc));
            actions.moveToElement(row).perform();
            pause(1000);
            WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(dotLoc));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
            pause(300);
            js.executeScript("arguments[0].click();", btn);
            pause(800);
            System.out.println("✅ Clicked: Invoice Detail 3-Dot Menu");
        } catch (Exception e) {
            System.out.println("⚠ safeClick failed [Invoice Detail 3-Dot Menu]: " + e.getMessage());
            throw new RuntimeException("❌ Could not click: Invoice Detail 3-Dot Menu", e);
        }
    }

    public void clickImportTimeAndExpenses() {
        By loc = By.xpath("//button[normalize-space()='IMPORT TIME AND EXPENSES']");
        safeClick(loc, "Import Time and Expenses");
        pause(1000);
    }

    public void clickAddNewItem() {
        By loc = By.xpath("//button[normalize-space()='Add new item']");
        safeClick(loc, "Add New Item");
        pause(1000);
    }

    public void clickAddDiscount() {
        By loc = By.xpath("//*[contains(.,'Add discount')]");
        safeClick(loc, "Add Discount");
        pause(800);
    }

    public void clickAddTax() {
        By loc = By.xpath("//*[contains(.,'Add tax')]");
        safeClick(loc, "Add Tax");
        pause(800);
    }

    public void enterInvoiceNote(String note) {
        By loc = By.xpath(
            "//textarea[@placeholder='My note'] | //textarea[contains(@placeholder,'note')]");
        fillField(loc, note, "Invoice Notes");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // CREATE INVOICE MODAL
    // ─────────────────────────────────────────────────────────────────────────────

    public void clickCreateInvoiceButton() {
        By loc = By.xpath(
            "//button[contains(.,'CREATE INVOICE') or contains(.,'Create Invoice')]");
        safeClick(loc, "Create Invoice Button");
        pause(1000);
    }

    public void clickClientDropdown() {
        By loc = By.xpath("//button[contains(.,'Select Client')]");
        safeClick(loc, "Client Dropdown");
        pause(800);
    }

    // FIX TC_INV_015: use presenceOfElementLocated + JS click for portal dropdown
    public void searchAndSelectClient(String clientName) {
        By dropdown = By.xpath("//button[contains(.,'Select Client')]");
        By search   = By.xpath("//input[@placeholder='Search clients...']");
        By option   = By.xpath("(//*[normalize-space()='" + clientName + "'])[1]");
        try {
            safeClick(dropdown, "Client Dropdown");
            pause(800);
            WebElement searchEl = wait.until(ExpectedConditions.presenceOfElementLocated(search));
            js.executeScript("arguments[0].click();", searchEl);
            pause(200);
            searchEl.sendKeys(clientName);
            pause(800);
            WebElement opt = wait.until(ExpectedConditions.presenceOfElementLocated(option));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", opt);
            js.executeScript("arguments[0].click();", opt);
            pause(500);
            System.out.println("✅ Selected client: " + clientName);
        } catch (Exception e) {
            throw new RuntimeException("❌ Could not select client: " + clientName, e);
        }
    }

    public void clickCurrencyDropdown() {
        By loc = By.xpath(
            "//button[@role='combobox' and contains(.,'USD')] | " +
            "//*[@role='combobox' and contains(.,'Dollar')]");
        safeClick(loc, "Currency Dropdown");
        pause(800);
    }

    public void searchAndSelectCurrency(String currency) {
        By dropdown = By.xpath(
            "//button[@role='combobox' and contains(.,'USD')] | " +
            "//*[@role='combobox' and contains(.,'Dollar')]");
        By search   = By.xpath("//input[@placeholder='Search currencies...']");
        By option   = By.xpath("(//*[contains(normalize-space(),'" + currency + "')])[last()]");
        try {
            safeClick(dropdown, "Currency Dropdown");
            pause(800);
            WebElement searchEl = wait.until(ExpectedConditions.elementToBeClickable(search));
            actions.moveToElement(searchEl).click().sendKeys(currency).perform();
            pause(800);
            safeClick(option, "Currency: " + currency);
            pause(500);
            System.out.println("✅ Selected currency: " + currency);
        } catch (Exception e) {
            throw new RuntimeException("❌ Could not select currency: " + currency, e);
        }
    }

    public void enterInvoiceId(String invoiceId) {
        By loc = By.xpath(
            "//input[contains(@value,'INV-')] | " +
            "//label[normalize-space()='Invoice ID']/following::input[1]");
        fillField(loc, invoiceId, "Invoice ID Field");
    }

    public void enterIssueDate(String date) {
        By loc = By.xpath(
            "//input[@type='date'][1] | " +
            "//label[contains(.,'Issue date')]/following::input[1]");
        fillField(loc, date, "Issue Date");
    }

    public void clickDueDateDropdown() {
        By loc = By.xpath(
            "//button[contains(.,'days after issue') or contains(.,'Upon receipt') or contains(.,'Custom date')]");
        safeClick(loc, "Due Date Dropdown");
        pause(800);
    }

    public void selectDueDateOption(String option) {
        By loc = By.xpath("//*[normalize-space()='" + option + "']");
        safeClick(loc, "Due Date Option: " + option);
        pause(500);
    }

    // FIX TC_INV_019: button text may be CREATE INVOICE not CREATE
    public void clickCreateButton() {
        By loc = By.xpath(
            "//button[normalize-space()='CREATE'] | " +
            "//button[normalize-space()='CREATE INVOICE'] | " +
            "//button[normalize-space()='Create Invoice']");
        try {
            WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(loc));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", el);
            pause(300);
            js.executeScript("arguments[0].click();", el);
            pause(2000);
            System.out.println("✅ Clicked: CREATE Button");
        } catch (Exception e) {
            System.out.println("⚠ safeClick failed [CREATE Button]: " + e.getMessage());
            throw new RuntimeException("❌ Could not click: CREATE Button", e);
        }
    }

    public void clickCancelButton() {
        By loc = By.xpath("//button[normalize-space()='Cancel']");
        safeClick(loc, "Cancel Button");
        pause(1000);
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // FILTER
    // ─────────────────────────────────────────────────────────────────────────────

    public void clickFilterButton() {
        By loc = By.xpath("//button[contains(.,'FILTER') or contains(.,'Filter')]");
        safeClick(loc, "Filter Button");
        pause(1000);
    }

    public void clickFilterDateRange() {
        By loc = By.xpath("(//button[contains(.,'2026') or contains(.,'Jan')])[1]");
        safeClick(loc, "Filter Date Range");
        pause(800);
    }

    public void clickFilterClientDropdown() {
        By loc = By.xpath("//*[contains(.,'All Clients') and (@role='combobox' or self::button)]");
        safeClick(loc, "Filter Client Dropdown");
        pause(800);
    }

    public void searchFilterClient(String clientName) {
        By search = By.xpath("//input[@placeholder='Search...']");
        By option = By.xpath("(//*[normalize-space()='" + clientName + "'])[1]");
        try {
            WebElement searchEl = wait.until(ExpectedConditions.elementToBeClickable(search));
            actions.moveToElement(searchEl).click().sendKeys(clientName).perform();
            pause(800);
            safeClick(option, "Filter Client: " + clientName);
            pause(500);
        } catch (Exception e) {
            throw new RuntimeException("❌ Could not search filter client: " + clientName, e);
        }
    }

    public void clickFilterStatusDropdown() {
        By loc = By.xpath("//*[contains(.,'All Statuses') and (@role='combobox' or self::button)]");
        safeClick(loc, "Filter Status Dropdown");
        pause(800);
    }

    public void selectFilterStatus(String status) {
        By loc = By.xpath("//*[normalize-space()='" + status + "']");
        safeClick(loc, "Filter Status: " + status);
        pause(500);
    }

    public void enterFilterAmountMin(String value) {
        By loc = By.xpath("(//input[@placeholder='Min'])[1]");
        fillField(loc, value, "Filter Amount Min");
    }

    public void enterFilterAmountMax(String value) {
        By loc = By.xpath("(//input[@placeholder='Max'])[1]");
        fillField(loc, value, "Filter Amount Max");
    }

    public void enterFilterBalanceMin(String value) {
        By loc = By.xpath("(//input[@placeholder='Min'])[2]");
        fillField(loc, value, "Filter Balance Min");
    }

    public void enterFilterBalanceMax(String value) {
        By loc = By.xpath("(//input[@placeholder='Max'])[2]");
        fillField(loc, value, "Filter Balance Max");
    }

    // FIX TC_INV_025: use presenceOfElementLocated + JS click
    public void clickApplyFilter() {
        By loc = By.xpath(
            "//button[normalize-space()='APPLY FILTER'] | " +
            "//button[normalize-space()='Apply Filter']");
        try {
            WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(loc));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", el);
            pause(500);
            js.executeScript("arguments[0].click();", el);
            pause(1500);
            System.out.println("✅ Clicked: Apply Filter");
        } catch (Exception e) {
            System.out.println("⚠ safeClick failed [Apply Filter]: " + e.getMessage());
            throw new RuntimeException("❌ Could not click: Apply Filter", e);
        }
    }

    public void clickFilterCancel() {
        By loc = By.xpath("//button[normalize-space()='Cancel']");
        safeClick(loc, "Filter Cancel");
        pause(1000);
    }

    public void clickFilterClearAll() {
        By loc = By.xpath(
            "//*[contains(.,'Clear All') and (self::button or self::span or self::div)]");
        safeClick(loc, "Filter Clear All");
        pause(1000);
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // SETTINGS
    // ─────────────────────────────────────────────────────────────────────────────

    public void clickSettingsButton() {
        By loc = By.xpath("//button[contains(.,'Settings')]");
        safeClick(loc, "Settings Button");
        pause(2500);
    }

    public void clickSettingsDefaultsTab() {
        By loc = By.xpath("//button[normalize-space()='DEFAULTS']");
        safeClick(loc, "DEFAULTS Tab");
        pause(1500);
    }

    public void clickSettingsAppearanceTab() {
        By loc = By.xpath("(//button[normalize-space()='APPEARANCE'])[1]");
        safeClick(loc, "APPEARANCE Tab");
        pause(1500);
    }

    public void clickSettingsEmailsTab() {
        By loc = By.xpath("(//button[normalize-space()='EMAILS'])[1]");
        safeClick(loc, "EMAILS Tab");
        pause(1500);
    }

    public void clickSettingsTranslationsTab() {
        By loc = By.xpath("(//button[normalize-space()='TRANSLATIONS'])[1]");
        safeClick(loc, "TRANSLATIONS Tab");
        pause(1500);
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // EXPORT
    // FIX TC_INV_034-037: Export is plain text button — not SVG-based dropdown
    // ─────────────────────────────────────────────────────────────────────────────

    public void clickExportButton() {
        By loc = By.xpath(
            "//button[normalize-space()='Export'] | " +
            "//button[contains(.,'Export') and not(contains(.,'CSV')) " +
            "and not(contains(.,'PDF')) and not(contains(.,'Excel'))]");
        try {
            WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(loc));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", el);
            pause(500);
            js.executeScript("arguments[0].click();", el);
            pause(1000);
            System.out.println("✅ Clicked: Export Button");
        } catch (Exception e) {
            System.out.println("⚠ safeClick failed [Export Button]: " + e.getMessage());
            throw new RuntimeException("❌ Could not click: Export Button", e);
        }
    }

    // FIX TC_INV_035-037: use safeClick instead of menuClick for export items
    public void clickExportToCsv() {
        By loc = By.xpath(
            "//*[normalize-space()='Export to CSV'] | " +
            "//*[normalize-space()='CSV']");
        jsClick(loc, "Export to CSV");
        pause(500);
    }

    public void clickExportToPdf() {
        By loc = By.xpath(
            "//*[normalize-space()='Export to PDF'] | " +
            "//*[normalize-space()='PDF']");
        jsClick(loc, "Export to PDF");
        pause(500);
    }

    public void clickExportToExcel() {
        By loc = By.xpath(
            "//*[normalize-space()='Export to Excel'] | " +
            "//*[normalize-space()='Excel']");
        jsClick(loc, "Export to Excel");
        pause(500);
    }
}





















































/*package com.aipxperts.punchly.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InvoicesPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final JavascriptExecutor js;
    private final Actions actions;

    public InvoicesPage(WebDriver driver) {
        this.driver  = driver;
        this.wait    = new WebDriverWait(driver, Duration.ofSeconds(25));
        this.js      = (JavascriptExecutor) driver;
        this.actions = new Actions(driver);
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // HELPERS
    // ─────────────────────────────────────────────────────────────────────────────

    private void safeClick(By locator, String label) {
        try {
            WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", el);
            pause(300);
            js.executeScript("arguments[0].click();", el);
            System.out.println("✅ Clicked: " + label);
        } catch (Exception e) {
            System.out.println("⚠ safeClick failed [" + label + "]: " + e.getMessage());
            throw new RuntimeException("❌ Could not click: " + label, e);
        }
    }

    private void fillField(By locator, String value, String label) {
        try {
            WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", el);
            pause(200);
            js.executeScript("arguments[0].value='';", el);
            el.click();
            pause(200);
            el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            el.sendKeys(Keys.DELETE);
            actions.moveToElement(el).click().sendKeys(value).perform();
            pause(400);
            System.out.println("✅ Filled [" + label + "]: " + value);
        } catch (Exception e) {
            System.out.println("⚠ fillField failed [" + label + "]: " + e.getMessage());
            throw new RuntimeException("❌ Could not fill field: " + label, e);
        }
    }

    private void menuClick(By locator, String label) {
        try {
            WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            pause(400);
            js.executeScript("arguments[0].click();", el);
            pause(800);
            System.out.println("✅ Menu clicked: " + label);
        } catch (Exception e) {
            System.out.println("⚠ menuClick failed [" + label + "]: " + e.getMessage());
            throw new RuntimeException("❌ Could not click: " + label, e);
        }
    }

    private void pause(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // NAVIGATION
    // ─────────────────────────────────────────────────────────────────────────────

    public void goToInvoices() {
        By sidebarInvoices = By.xpath(
            "//span[normalize-space()='INVOICES'] | //span[normalize-space()='Invoices']");
        try {
            WebElement link = wait.until(ExpectedConditions.elementToBeClickable(sidebarInvoices));
            js.executeScript("arguments[0].click();", link);
            pause(1500);
            System.out.println("✅ Navigated to Invoices page");
        } catch (Exception e) {
            throw new RuntimeException("❌ Invoices sidebar link not found.", e);
        }
    }

    public void clickBreadcrumbInvoices() {
        // FIX: breadcrumb may be <a>, <span> or <button> — use text-based fallback
        By loc = By.xpath(
            "//a[normalize-space()='Invoices'] | " +
            "//button[normalize-space()='Invoices'] | " +
            "//*[contains(@class,'breadcrumb') and normalize-space()='Invoices'] | " +
            "//*[@aria-label='Invoices'] | " +
            "(//*[normalize-space()='Invoices'])[1]");
        safeClick(loc, "Breadcrumb Invoices");
        pause(1500);
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // INVOICE LIST
    // ─────────────────────────────────────────────────────────────────────────────

    public void clickFirstInvoiceId() {
        // FIX: original XPath had broken quotes — //td[@class='p-2 align-middle whitespace-nowrap]
        // Use contains() with double quotes to avoid quote conflict
        By loc = By.xpath(
            "(//tbody//tr[1]//td)[1] | " +
            "//td[contains(@class,'p-2') and contains(@class,'align-middle') and contains(@class,'whitespace-nowrap')]");
        safeClick(loc, "First Invoice ID");
        pause(1500);
    }

    public void searchInvoiceById(String invoiceId) {
        By loc = By.xpath("//input[@placeholder='Invoice ID']");
        fillField(loc, invoiceId, "Invoice ID Search");
        pause(1000);
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // INVOICE DETAIL PAGE
    // ─────────────────────────────────────────────────────────────────────────────

    public void clickDownloadInvoice() {
        By loc = By.xpath(
            "//button[contains(.,'DOWNLOAD INVOICE') or contains(.,'Download Invoice')]");
        safeClick(loc, "Download Invoice");
        pause(1000);
    }

    public void clickSendInvoice() {
        By loc = By.xpath(
            "//button[contains(.,'SEND INVOICE') or contains(.,'Send Invoice')]");
        safeClick(loc, "Send Invoice");
        pause(1000);
    }

    public void clickRecurringSettings() {
        By loc = By.xpath(
            "//button[contains(.,'Recurring settings') or contains(.,'Recurring Settings')]");
        safeClick(loc, "Recurring Settings");
        pause(1000);
    }

    public void clickInvoiceDetailThreeDot() {
        By loc = By.xpath(
            "(//button[@data-slot='dropdown-menu-trigger' and @aria-haspopup='menu'])[1]");
        safeClick(loc, "Invoice Detail 3-Dot Menu");
        pause(800);
    }

    public void clickImportTimeAndExpenses() {
        By loc = By.xpath(
            "//button[normalize-space()='IMPORT TIME AND EXPENSES']");
        safeClick(loc, "Import Time and Expenses");
        pause(1000);
    }

    public void clickAddNewItem() {
        By loc = By.xpath(
            "//button[normalize-space()='Add new item']");
        safeClick(loc, "Add New Item");
        pause(1000);
    }

    public void clickAddDiscount() {
        By loc = By.xpath("//*[contains(.,'Add discount')]");
        safeClick(loc, "Add Discount");
        pause(800);
    }

    public void clickAddTax() {
        By loc = By.xpath("//*[contains(.,'Add tax')]");
        safeClick(loc, "Add Tax");
        pause(800);
    }

    public void enterInvoiceNote(String note) {
        By loc = By.xpath(
            "//textarea[@placeholder='My note'] | //textarea[contains(@placeholder,'note')]");
        fillField(loc, note, "Invoice Notes");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // CREATE INVOICE MODAL
    // ─────────────────────────────────────────────────────────────────────────────

    public void clickCreateInvoiceButton() {
        By loc = By.xpath(
            "//button[contains(.,'CREATE INVOICE') or contains(.,'Create Invoice')]");
        safeClick(loc, "Create Invoice Button");
        pause(1000);
    }

    public void clickClientDropdown() {
        By loc = By.xpath("//button[contains(.,'Select Client')]");
        safeClick(loc, "Client Dropdown");
        pause(800);
    }

    public void searchAndSelectClient(String clientName) {
        By dropdown = By.xpath("//button[contains(.,'Select Client')]");
        By search   = By.xpath("//input[@placeholder='Search clients...']");
        By option   = By.xpath("(//*[normalize-space()='" + clientName + "'])[1]");
        try {
            safeClick(dropdown, "Client Dropdown");
            pause(800);
            // FIX: use presenceOfElementLocated + JS click — same pattern as assignee fix
            WebElement searchEl = wait.until(ExpectedConditions.presenceOfElementLocated(search));
            js.executeScript("arguments[0].click();", searchEl);
            pause(200);
            searchEl.sendKeys(clientName);
            pause(800);
            WebElement opt = wait.until(ExpectedConditions.presenceOfElementLocated(option));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", opt);
            js.executeScript("arguments[0].click();", opt);
            pause(500);
            System.out.println("✅ Selected client: " + clientName);
        } catch (Exception e) {
            throw new RuntimeException("❌ Could not select client: " + clientName, e);
        }
    }

    public void clickCurrencyDropdown() {
        By loc = By.xpath(
            "//button[@role='combobox' and contains(.,'USD')] | " +
            "//*[@role='combobox' and contains(.,'Dollar')]");
        safeClick(loc, "Currency Dropdown");
        pause(800);
    }

    public void searchAndSelectCurrency(String currency) {
        By dropdown = By.xpath(
            "//button[@role='combobox' and contains(.,'USD')] | " +
            "//*[@role='combobox' and contains(.,'Dollar')]");
        By search   = By.xpath("//input[@placeholder='Search currencies...']");
        By option   = By.xpath("(//*[contains(normalize-space(),'" + currency + "')])[last()]");
        try {
            safeClick(dropdown, "Currency Dropdown");
            pause(800);
            WebElement searchEl = wait.until(ExpectedConditions.elementToBeClickable(search));
            actions.moveToElement(searchEl).click().sendKeys(currency).perform();
            pause(800);
            safeClick(option, "Currency: " + currency);
            pause(500);
            System.out.println("✅ Selected currency: " + currency);
        } catch (Exception e) {
            throw new RuntimeException("❌ Could not select currency: " + currency, e);
        }
    }

    public void enterInvoiceId(String invoiceId) {
        By loc = By.xpath(
            "//input[contains(@value,'INV-')] | " +
            "//label[normalize-space()='Invoice ID']/following::input[1]");
        fillField(loc, invoiceId, "Invoice ID Field");
    }

    public void enterIssueDate(String date) {
        By loc = By.xpath(
            "//input[@type='date'][1] | " +
            "//label[contains(.,'Issue date')]/following::input[1]");
        fillField(loc, date, "Issue Date");
    }

    public void clickDueDateDropdown() {
        By loc = By.xpath(
            "//button[contains(.,'days after issue') or contains(.,'Upon receipt') or contains(.,'Custom date')]");
        safeClick(loc, "Due Date Dropdown");
        pause(800);
    }

    public void selectDueDateOption(String option) {
        By loc = By.xpath("//*[normalize-space()='" + option + "']");
        safeClick(loc, "Due Date Option: " + option);
        pause(500);
    }

    public void clickCreateButton() {
        By loc = By.xpath("//button[normalize-space()='CREATE']");
        safeClick(loc, "CREATE Button");
        pause(2000);
    }

    public void clickCancelButton() {
        By loc = By.xpath("//button[normalize-space()='Cancel']");
        safeClick(loc, "Cancel Button");
        pause(1000);
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // FILTER
    // ─────────────────────────────────────────────────────────────────────────────

    public void clickFilterButton() {
        By loc = By.xpath("//button[contains(.,'FILTER') or contains(.,'Filter')]");
        safeClick(loc, "Filter Button");
        pause(1000);
    }

    public void clickFilterDateRange() {
        By loc = By.xpath("(//button[contains(.,'2026') or contains(.,'Jan')])[1]");
        safeClick(loc, "Filter Date Range");
        pause(800);
    }

    public void clickFilterClientDropdown() {
        By loc = By.xpath("//*[contains(.,'All Clients') and (@role='combobox' or self::button)]");
        safeClick(loc, "Filter Client Dropdown");
        pause(800);
    }

    public void searchFilterClient(String clientName) {
        By search = By.xpath("//input[@placeholder='Search...']");
        By option = By.xpath("(//*[normalize-space()='" + clientName + "'])[1]");
        try {
            WebElement searchEl = wait.until(ExpectedConditions.elementToBeClickable(search));
            actions.moveToElement(searchEl).click().sendKeys(clientName).perform();
            pause(800);
            safeClick(option, "Filter Client: " + clientName);
            pause(500);
        } catch (Exception e) {
            throw new RuntimeException("❌ Could not search filter client: " + clientName, e);
        }
    }

    public void clickFilterStatusDropdown() {
        By loc = By.xpath("//*[contains(.,'All Statuses') and (@role='combobox' or self::button)]");
        safeClick(loc, "Filter Status Dropdown");
        pause(800);
    }

    public void selectFilterStatus(String status) {
        By loc = By.xpath("//*[normalize-space()='" + status + "']");
        safeClick(loc, "Filter Status: " + status);
        pause(500);
    }

    public void enterFilterAmountMin(String value) {
        By loc = By.xpath("(//input[@placeholder='Min'])[1]");
        fillField(loc, value, "Filter Amount Min");
    }

    public void enterFilterAmountMax(String value) {
        By loc = By.xpath("(//input[@placeholder='Max'])[1]");
        fillField(loc, value, "Filter Amount Max");
    }

    public void enterFilterBalanceMin(String value) {
        By loc = By.xpath("(//input[@placeholder='Min'])[2]");
        fillField(loc, value, "Filter Balance Min");
    }

    public void enterFilterBalanceMax(String value) {
        By loc = By.xpath("(//input[@placeholder='Max'])[2]");
        fillField(loc, value, "Filter Balance Max");
    }

    public void clickApplyFilter() {
        // FIX: button text may be "Apply Filter" or "APPLY FILTER" — handle both
        By loc = By.xpath(
            "//button[normalize-space()='APPLY FILTER'] | " +
            "//button[normalize-space()='Apply Filter'] | " +
            "//button[contains(translate(.,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'APPLY')]");
        safeClick(loc, "Apply Filter");
        pause(1500);
    }

    public void clickFilterCancel() {
        By loc = By.xpath("//button[normalize-space()='Cancel']");
        safeClick(loc, "Filter Cancel");
        pause(1000);
    }

    public void clickFilterClearAll() {
        By loc = By.xpath("//*[contains(.,'Clear All') and (self::button or self::span or self::div)]");
        safeClick(loc, "Filter Clear All");
        pause(1000);
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // SETTINGS
    // ─────────────────────────────────────────────────────────────────────────────

    public void clickSettingsButton() {
        By loc = By.xpath("//button[contains(.,'Settings')]");
        safeClick(loc, "Settings Button");
        pause(2500);
    }

    public void clickSettingsDefaultsTab() {
        By loc = By.xpath("//button[normalize-space()='DEFAULTS']");
        safeClick(loc, "DEFAULTS Tab");
        pause(1500);
    }

    public void clickSettingsAppearanceTab() {
        By loc = By.xpath("(//button[normalize-space()='APPEARANCE'])[1]");
        safeClick(loc, "APPEARANCE Tab");
        pause(1500);
    }

    public void clickSettingsEmailsTab() {
        By loc = By.xpath("(//button[normalize-space()='EMAILS'])[1]");
        safeClick(loc, "EMAILS Tab");
        pause(1500);
    }

    public void clickSettingsTranslationsTab() {
        By loc = By.xpath("(//button[normalize-space()='TRANSLATIONS'])[1]");
        safeClick(loc, "TRANSLATIONS Tab");
        pause(1500);
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // EXPORT
    // ─────────────────────────────────────────────────────────────────────────────

    public void clickExportButton() {
        // FIX: SVG class matching doesn't work reliably — use button text or aria-label
        By loc = By.xpath(
            "//button[@aria-label='Export'] | " +
            "//button[contains(.,'Export')] | " +
            "//button[@data-slot='dropdown-menu-trigger'][.//svg] | " +
            "(//button[contains(@class,'dropdown')])[last()]");
        try {
            WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(loc));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", el);
            pause(500);
            js.executeScript("arguments[0].click();", el);
            pause(800);
            System.out.println("✅ Clicked: Export Button");
        } catch (Exception e) {
            System.out.println("⚠ safeClick failed [Export Button]: " + e.getMessage());
            throw new RuntimeException("❌ Could not click: Export Button", e);
        }
    }

    public void clickExportToCsv() {
        By loc = By.xpath("//*[contains(.,'Export to CSV')]");
        menuClick(loc, "Export to CSV");
    }

    public void clickExportToPdf() {
        By loc = By.xpath("//*[contains(.,'Export to PDF')]");
        menuClick(loc, "Export to PDF");
    }

    public void clickExportToExcel() {
        By loc = By.xpath("//*[contains(.,'Export to Excel')]");
        menuClick(loc, "Export to Excel");
    }
}*/