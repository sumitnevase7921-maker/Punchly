package com.aipxperts.punchly.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;

public class ApprovalsPage {

    WebDriver driver;
    WebDriverWait wait;

    public ApprovalsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ================= LOCATORS =================

    By approvalsMenu = By.xpath("//span[normalize-space()='APPROVALS']");
    By pageTitle     = By.xpath("//h1[normalize-space()='Approvals']");
    By searchBar     = By.xpath("//input[@placeholder='Search by employee name...']");

    // Dropdown buttons (more stable)
    By typeDropdown   = By.xpath("(//button[@role='combobox'])[1]");
    By statusDropdown = By.xpath("(//button[@role='combobox'])[2]");

    // Type options
    By allTypes   = By.xpath("//div[normalize-space()='All Types']");
    By timesheets = By.xpath("//div[normalize-space()='Timesheets']");
    By expenses   = By.xpath("//div[normalize-space()='Expenses']");
    By timeOff    = By.xpath("//div[normalize-space()='Time-Off']");

    // Status options
    By pending  = By.xpath("//div[normalize-space()='Pending']");
    By approved = By.xpath("//div[normalize-space()='Approved']");
    By rejected = By.xpath("//div[normalize-space()='Rejected']");
    By all      = By.xpath("//div[normalize-space()='All']");

    // Results
    By noDataMsg         = By.xpath("//*[contains(text(),'No pending approvals')]");
    By approvedStatusTag = By.xpath("//span[normalize-space()='APPROVED']");

    // ================= COMMON ACTION =================

    private void click(By locator) {
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
        wait.until(ExpectedConditions.elementToBeClickable(el));

        try {
            el.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
        }
    }

    // ================= NAVIGATION =================

    public void navigateToApprovalsPage() {
        click(approvalsMenu);
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
    }

    public boolean isApprovalsPageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).isDisplayed();
    }

    public boolean isSearchBarVisible() {
        return !driver.findElements(searchBar).isEmpty();
    }

    // ================= TYPE DROPDOWN =================

    public void openTypeDropdown() {
        click(typeDropdown);
    }

    public void selectAllTypes() {
        click(allTypes);
    }

    public void selectTimesheets() {
        click(timesheets);
    }

    public void selectExpenses() {
        click(expenses);
    }

    public void selectTimeOff() {
        click(timeOff);
    }

    // ================= STATUS DROPDOWN =================

    public void openStatusDropdown() {
        click(statusDropdown);
    }

    public void selectPending() {
        click(pending);
    }

    public void selectApproved() {
        click(approved);
        wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(approvedStatusTag),
                ExpectedConditions.presenceOfElementLocated(noDataMsg)
        ));
    }

    public void selectRejected() {
        click(rejected);
    }

    public void selectAllStatus() {
        click(all);
    }

    // ================= VALIDATIONS =================

    public boolean isNoDataVisible() {
        return !driver.findElements(noDataMsg).isEmpty();
    }

    public boolean isApprovedDataVisible() {
        List<WebElement> list = driver.findElements(approvedStatusTag);
        return list.size() > 0;
    }
}