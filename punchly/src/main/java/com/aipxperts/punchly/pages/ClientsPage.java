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

public class ClientsPage {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    Actions actions;

    public ClientsPage(WebDriver driver) {
       
        this.wait    = new WebDriverWait(driver, Duration.ofSeconds(25));
        this.js      = (JavascriptExecutor) driver;
        this.actions = new Actions(driver);
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // LOCATORS
    // ─────────────────────────────────────────────────────────────────────────────

    By sidebarClients = By.xpath(
        "//span[normalize-space()='CLIENTS'] | //span[normalize-space()='Clients']");

    By activeTab   = By.xpath("//button[normalize-space()='Active']");
    By archivedTab = By.xpath("//button[normalize-space()='Archived']");

    By createClientRow = By.xpath("//button[normalize-space()='Create Client']");
    By clientNameInput = By.xpath("//input[@id='client-create-input']");

    By firstClientNameLink = By.xpath(
        "//span[@class='text-sm font-medium text-gray-900 hover:text-[#008000]']");

    By editNameField    = By.xpath("//input[@name='name' and @placeholder='Enter client name']");
    By editEmailField   = By.xpath("//input[@name='email' and @type='email' and @data-slot='form-control']");
    By ccEmailInput     = By.xpath("//input[@data-slot='input'] ");
    By ccAddBtn         = By.xpath("//button[normalize-space()='Add']");
    By editAddressField = By.xpath("//textarea[@name='address' and @placeholder='Enter address']");
    By editNoteField    = By.xpath("//textarea[contains(@class,'border-input') and @name='note']");
    By saveBtn          = By.xpath("//button[normalize-space()='SAVE']");
    By cancelBtn        = By.xpath("//button[normalize-space()='Cancel']");

    By firstRowCheckbox = By.xpath("(//button[@value='on'])[2]");

    By bulkArchiveBtn = By.xpath("//button[normalize-space()='Archive']");
    By bulkDeleteBtn  = By.xpath("//button[normalize-space()='Delete']");
    By bulkClearBtn   = By.xpath("//button[normalize-space()='Clear']");

    // FIX: simplified 3-dot trigger — hover first then find any button in the row
    By firstRowThreeDot = By.xpath("(//tbody//tr[1]//button)[last()]");

    By menuEdit    = By.xpath("//div[normalize-space()='Edit']");
    By menuArchive = By.xpath("//div[normalize-space()='Archive']");
    By menuDelete  = By.xpath(
        "//div[normalize-space()='Delete'] | " +
        "//*[normalize-space()='Delete'][not(self::button)]");
    By menuRestore = By.xpath(
        "(//*[@role='menuitem' and normalize-space()='Restore'])[1] | " +
        "//div[normalize-space()='Restore']");

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

    private void safeClickWithDismiss(By locator, String label) {
        try {
            driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
            pause(600);
        } catch (Exception ignored) {}
        safeClick(locator, label);
    }

    private void fillField(By locator, String value, String label) {
        try {
            WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", el);
            pause(300);
            el.click();
            el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            el.sendKeys(Keys.DELETE);
            js.executeScript("arguments[0].value='';", el);
            el.sendKeys(value);
            pause(300);
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
            pause(1000);
            System.out.println("✅ Menu clicked: " + label);
        } catch (Exception e) {
            System.out.println("⚠ menuClick failed [" + label + "]: " + e.getMessage());
            throw new RuntimeException("❌ Could not click menu item: " + label, e);
        }
    }

    private void pause(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // NAVIGATION
    // ─────────────────────────────────────────────────────────────────────────────

    public void goToClients() {
        try {
            WebElement link = wait.until(ExpectedConditions.elementToBeClickable(sidebarClients));
            js.executeScript("arguments[0].click();", link);
            pause(1500);
            System.out.println("✅ Navigated to Clients page");
        } catch (Exception e) {
            throw new RuntimeException("❌ Clients sidebar link not found.", e);
        }
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // TABS
    // ─────────────────────────────────────────────────────────────────────────────

    public void clickActiveTab() {
        safeClick(activeTab, "Active Tab");
        pause(1500);
    }

    public void clickArchivedTab() {
        safeClick(archivedTab, "Archived Tab");
        pause(1500);
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // CREATE CLIENT
    // ─────────────────────────────────────────────────────────────────────────────

    public void clickCreateClientRow() {
        safeClickWithDismiss(createClientRow, "Create Client Row (inline)");
        pause(800);
    }

    public void enterClientName(String name) {
        fillField(clientNameInput, name, "Client Name Input");
    }

    public void pressEnterToSave() {
        try {
            WebElement input = wait.until(
                ExpectedConditions.visibilityOfElementLocated(clientNameInput));
            input.sendKeys(Keys.ENTER);
            pause(2000);
            System.out.println("✅ Pressed ENTER — client saved");
        } catch (Exception e) {
            throw new RuntimeException("❌ Could not press ENTER on client name input.", e);
        }
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // EDIT MODAL
    // ─────────────────────────────────────────────────────────────────────────────

    public void clickFirstClientName() {
        safeClick(firstClientNameLink, "First Client Name Link");
        pause(1500);
    }

    public void editClientName(String name)      { fillField(editNameField,    name,    "Edit Name");    }
    public void editClientEmail(String email)    { fillField(editEmailField,   email,   "Edit Email");   }
    public void enterCcEmail(String email)       { fillField(ccEmailInput,     email,   "CC Email");     }
    public void editClientAddress(String address){ fillField(editAddressField, address, "Edit Address"); }
    public void editClientNote(String note)      { fillField(editNoteField,    note,    "Edit Note");    }

    public void clickCcAdd() {
        safeClick(ccAddBtn, "CC Add Button");
        pause(500);
    }

    public void clickSave() {
        safeClick(saveBtn, "Save Button");
        pause(1500);
    }

    public void clickCancel() {
        safeClick(cancelBtn, "Cancel Button");
        pause(1000);
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // CHECKBOX & BULK ACTION BAR
    // ─────────────────────────────────────────────────────────────────────────────

    public void clickFirstRowCheckbox() {
        try {
            WebElement row = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//tbody//tr)[1]")));
            actions.moveToElement(row).perform();
            pause(700);
        } catch (Exception ignored) {}
        safeClick(firstRowCheckbox, "First Row Checkbox");
        pause(1000);
    }

    public void clickBulkArchive() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(bulkArchiveBtn));
        safeClick(bulkArchiveBtn, "Bulk Archive Button");
        pause(1500);
    }

    public void clickBulkDelete() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(bulkDeleteBtn));
        safeClick(bulkDeleteBtn, "Bulk Delete Button");
        pause(1500);
    }

    public void clickBulkClear() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(bulkClearBtn));
        safeClick(bulkClearBtn, "Bulk Clear Button");
        pause(1000);
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // 3-DOT MENU
    // FIX: hover row first → wait for button to appear → JS click
    // ─────────────────────────────────────────────────────────────────────────────

    public void clickThreeDotMenu() {
        try {
            // Step 1: hover the first row to reveal the 3-dot button
            WebElement row = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//tbody//tr)[1]")));
            actions.moveToElement(row).perform();
            pause(1000); // longer pause — button only appears after hover

            // Step 2: wait for button to be present then JS click
            WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(firstRowThreeDot));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
            pause(300);
            js.executeScript("arguments[0].click();", btn);
            pause(800);
            System.out.println("✅ Clicked: 3-Dot Menu Trigger");
        } catch (Exception e) {
            System.out.println("⚠ safeClick failed [3-Dot Menu Trigger]: " + e.getMessage());
            throw new RuntimeException("❌ Could not click: 3-Dot Menu Trigger", e);
        }
    }

    public void clickMenuEdit()    { menuClick(menuEdit,    "Menu → Edit");    }
    public void clickMenuArchive() { menuClick(menuArchive, "Menu → Archive"); }
    public void clickMenuDelete()  { menuClick(menuDelete,  "Menu → Delete");  }
    public void clickMenuRestore() { menuClick(menuRestore, "Menu → Restore"); }

    // ─────────────────────────────────────────────────────────────────────────────
    // ALIAS METHODS
    // ─────────────────────────────────────────────────────────────────────────────

    public void clickCreateInlineRow()       { clickCreateClientRow(); }
    public void pressEnterToSaveClientName() { pressEnterToSave();     }
    public void clickCreatedClientName()     { clickFirstClientName(); }
}