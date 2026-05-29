package com.aipxperts.punchly.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TeamPage {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    public TeamPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js     = (JavascriptExecutor) driver;
    }

    // ─── Locators ────────────────────────────────────────────────────────────────

    By sidebarTeam          = By.xpath("//span[normalize-space()='TEAM'] | //span[normalize-space()='Team']");
    By pageTitle            = By.xpath("//h1[normalize-space()='Team']");
    By searchBar            = By.xpath("//input[@placeholder='Search by name or email']");
    By addTeamMemberBtn     = By.xpath("//button[contains(.,'ADD TEAM MEMBER') or contains(.,'Add Team Member')]");
    By inviteEmailInput     = By.xpath("//input[@placeholder='Enter up to 5 email addresses']");
    By sendInviteBtn        = By.xpath("//button[normalize-space()='Send Invite']");
    By cancelInviteBtn      = By.xpath("//button[normalize-space()='Cancel']");
    By billableRateChangeBtn= By.xpath("(//button[normalize-space()='Change'])[1]");
    By costRateChangeBtn    = By.xpath("(//button[normalize-space()='Change'])[2]");
    By rateInput            = By.xpath("//input[@placeholder='0.00']");
    By allPastFutureRadio   = By.xpath("(//button[@role='radio'])[2]");
    By saveBtn              = By.xpath("//button[normalize-space()='SAVE' or normalize-space()='Save']");
    By cancelRateBtn        = By.xpath("//button[normalize-space()='Cancel']");
    By filterBtn            = By.xpath("//button[contains(.,'FILTER') or contains(.,'Filter')]");
    By billableRateDropdown = By.xpath("(//button[@role='combobox'])[1]");
    By costRateDropdown     = By.xpath("(//button[@role='combobox'])[2]");
    By roleDropdown         = By.xpath("(//button[@role='combobox'])[3]");
    By optionAll            = By.xpath("//div[normalize-space()='All']");
    By optionHasRate        = By.xpath("//div[normalize-space()='Has rate']");
    By optionNoRate         = By.xpath("//div[normalize-space()='No rate']");
    By optionOwner          = By.xpath("//div[normalize-space()='Owner']");
    By optionAdmin          = By.xpath("//div[normalize-space()='Admin']");
    By optionManager        = By.xpath("//div[normalize-space()='Manager']");
    By optionMember         = By.xpath("//div[normalize-space()='Member']");
    By showInactiveCheckbox = By.xpath("//button[@role='checkbox']");
    By applyFilterBtn       = By.xpath("//button[normalize-space()='APPLY FILTER' or normalize-space()='Apply Filter']");
    By clearFilterBtn       = By.xpath("//button[normalize-space()='Clear']");
    By exportBtn            = By.xpath("//button[contains(.,'Export')]");

    // ─── Core Helpers ─────────────────────────────────────────────────────────────

    private void dismissOverlay() {
        try {
            driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
            Thread.sleep(800);
        } catch (Exception ignored) {}
    }

    private void safeClick(By locator, String label) {
        try {
            WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", el);
            js.executeScript("arguments[0].click();", el);
            System.out.println("✅ Clicked: " + label);
        } catch (Exception e) {
            System.out.println("⚠ safeClick failed for: " + label + " — " + e.getMessage());
            throw new RuntimeException("❌ Could not click: " + label, e);
        }
    }

    private void safeClickWithDismiss(By locator, String label) {
        dismissOverlay();
        safeClick(locator, label);
    }

    // ─── Navigation ──────────────────────────────────────────────────────────────

    public void goToTeam() {
        System.out.println("🔍 Looking for Team sidebar link...");
        try {
            WebElement link = wait.until(ExpectedConditions.elementToBeClickable(sidebarTeam));
            js.executeScript("arguments[0].click();", link);
            wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
            System.out.println("✅ Navigated to Team page");
        } catch (Exception e) {
            printSidebarElements();
            throw new RuntimeException("❌ Team sidebar link not found.", e);
        }
    }

    // ─── Search ───────────────────────────────────────────────────────────────────

    public void searchByNameOrEmail(String text) throws InterruptedException {
        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBar));
        search.clear();
        search.sendKeys(text);
        Thread.sleep(1500);
        System.out.println("✅ Searched: " + text);
    }

    // ─── Add Team Member ─────────────────────────────────────────────────────────

    public void clickAddTeamMember() {
        safeClickWithDismiss(addTeamMemberBtn, "Add Team Member Button");
    }

    public void enterInviteEmail(String email) throws InterruptedException {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(inviteEmailInput));
        input.clear();
        input.sendKeys(email);
        Thread.sleep(1000);
        System.out.println("✅ Entered invite email: " + email);
    }

    public void clickSendInvite() {
        safeClick(sendInviteBtn, "Send Invite Button");
    }

    public void clickCancelInvite() {
        safeClick(cancelInviteBtn, "Cancel Invite Button");
    }

    // ─── Billable Rate ────────────────────────────────────────────────────────────

    public void clickBillableRateChange() {
        safeClickWithDismiss(billableRateChangeBtn, "Billable Rate Change Button");
    }

    public void enterRate(String rate) throws InterruptedException {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(rateInput));
        input.clear();
        input.sendKeys(rate);
        Thread.sleep(500);
        System.out.println("✅ Entered rate: " + rate);
    }

    public void selectAllPastFutureEntries() throws InterruptedException {
        Thread.sleep(500);
        safeClick(allPastFutureRadio, "All Past and Future Radio");
    }

    public void clickSaveRate() {
        safeClick(saveBtn, "Save Button");
    }

    public void clickCancelRate() {
        safeClick(cancelRateBtn, "Cancel Button");
    }

    // ─── Cost Rate ────────────────────────────────────────────────────────────────

    public void clickCostRateChange() {
        safeClickWithDismiss(costRateChangeBtn, "Cost Rate Change Button");
    }

    // ─── Filter ───────────────────────────────────────────────────────────────────

    public void clickFilter() {
        safeClickWithDismiss(filterBtn, "Filter Button");
    }

    public void clickBillableRateFilterDropdown() {
        safeClick(billableRateDropdown, "Billable Rate Filter Dropdown");
    }

    public void clickCostRateFilterDropdown() {
        safeClick(costRateDropdown, "Cost Rate Filter Dropdown");
    }

    public void clickRoleFilterDropdown() {
        safeClick(roleDropdown, "Role Filter Dropdown");
    }

    public void selectFilterAll() throws InterruptedException {
        Thread.sleep(500);
        safeClick(optionAll, "All Option");
    }

    public void selectFilterHasRate() throws InterruptedException {
        Thread.sleep(500);
        safeClick(optionHasRate, "Has Rate Option");
    }

    public void selectFilterNoRate() throws InterruptedException {
        Thread.sleep(500);
        safeClick(optionNoRate, "No Rate Option");
    }

    public void selectRoleOwner() throws InterruptedException {
        Thread.sleep(500);
        safeClick(optionOwner, "Owner");
    }

    public void selectRoleAdmin() throws InterruptedException {
        Thread.sleep(500);
        safeClick(optionAdmin, "Admin");
    }

    public void selectRoleManager() throws InterruptedException {
        Thread.sleep(500);
        safeClick(optionManager, "Manager");
    }

    public void selectRoleMember() throws InterruptedException {
        Thread.sleep(500);
        safeClick(optionMember, "Member");
    }

    public void clickShowInactiveMembers() {
        safeClick(showInactiveCheckbox, "Show Inactive Members");
    }

    public void clickApplyFilter() {
        safeClick(applyFilterBtn, "Apply Filter");
    }

    public void clickClearFilter() {
        safeClick(clearFilterBtn, "Clear Filter");
    }

    // ─── Export ───────────────────────────────────────────────────────────────────

    public void clickExport() {
        safeClickWithDismiss(exportBtn, "Export Button");
    }

    // ─── Debug Helper ─────────────────────────────────────────────────────────────

    public void printSidebarElements() {
        System.out.println("=== DEBUG: All sidebar spans ===");
        List<WebElement> spans = driver.findElements(By.tagName("span"));
        for (WebElement span : spans) {
            String text = span.getText().trim();
            if (!text.isEmpty()) {
                System.out.println("  <span> text=" + text);
            }
        }
    }
}