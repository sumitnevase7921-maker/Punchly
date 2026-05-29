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

public class ReportsPage {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    public ReportsPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js     = (JavascriptExecutor) driver;
    }

    // ─── Locators ────────────────────────────────────────────────────────────────

    // Sidebar
    By sidebarReports1 = By.xpath("//span[normalize-space()='REPORTS']");
   
    // Header Tabs
    By timeReportTab    = By.xpath("//button[normalize-space()='TIME REPORT']");
    By teamReportTab    = By.xpath("//button[normalize-space()='TEAM REPORT']");
    By expenseReportTab = By.xpath("//button[normalize-space()='EXPENSE REPORT']");

    // Sub Tabs
    By summaryTab     = By.xpath("//button[contains(text(),'Summary')]");
    By detailedTab    = By.xpath("//button[normalize-space()='Detailed']");
    By attendanceTab  = By.xpath("//button[contains(text(),'Attendance')]");
    By assignmentsTab = By.xpath("//button[contains(text(),'Assignments')]");

    // Export Button
    By exportButton = By.xpath("//button[contains(.,'Export')]");

    // ─── Core Helpers ─────────────────────────────────────────────────────────────

    /**
     * Dismisses any open overlay/dropdown/toast by pressing Escape,
     * then waits for the body to be stable before proceeding.
     */
    private void dismissOverlay() {
        try {
            driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
            Thread.sleep(800);
        } catch (Exception ignored) {}
    }

    /**
     * Waits for element to be clickable, dismisses any overlay,
     * then clicks via JavascriptExecutor to bypass interception.
     */
    private void safeClick(By locator, String label) {
        try {
            WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
            dismissOverlay();
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", el);
            js.executeScript("arguments[0].click();", el);
            System.out.println("✅ Clicked: " + label);
        } catch (Exception e) {
            System.out.println("⚠ safeClick failed for: " + label + " — " + e.getMessage());
            throw new RuntimeException("❌ Could not click: " + label, e);
        }
    }

    // ─── Navigation ──────────────────────────────────────────────────────────────

    public void goToReports() {
        System.out.println("🔍 Looking for Reports sidebar link...");
        By[] locators = { sidebarReports1 };
        WebElement reportsLink = null;

        for (By locator : locators) {
            try {
                reportsLink = wait.until(ExpectedConditions.elementToBeClickable(locator));
                System.out.println("✅ Found Reports with: " + locator);
                break;
            } catch (Exception e) {
                System.out.println("⚠ Not found with: " + locator);
            }
        }

        if (reportsLink == null) {
            printSidebarElements();
            throw new RuntimeException("❌ Reports sidebar link not found.");
        }

        js.executeScript("arguments[0].click();", reportsLink);
        System.out.println("✅ Navigated to Reports");
    }

    // ─── Header Tab Actions ───────────────────────────────────────────────────────

    public void clickTimeReport() {
        safeClick(timeReportTab, "Time Report");
    }

    public void clickTeamReport() {
        safeClick(teamReportTab, "Team Report");
    }

    public void clickExpenseReport() {
        safeClick(expenseReportTab, "Expense Report");
    }

    // ─── Sub Tab Actions ──────────────────────────────────────────────────────────

    public void clickSummary() {
        safeClick(summaryTab, "Summary");
    }

    public void clickDetailed() {
        safeClick(detailedTab, "Detailed");
    }

    public void clickAttendance() {
        safeClick(attendanceTab, "Attendance");
    }

    public void clickAssignments() {
        safeClick(assignmentsTab, "Assignments");
    }

    // ─── Export Action ────────────────────────────────────────────────────────────

    public void clickExport() {
        safeClick(exportButton, "Export");
    }

    // ─── Debug Helper ─────────────────────────────────────────────────────────────

    public void printSidebarElements() {
        System.out.println("=== DEBUG: All <a> links on page ===");
        List<WebElement> links = driver.findElements(By.tagName("a"));
        for (WebElement link : links) {
            System.out.println("  href=" + link.getAttribute("href") + " | text=" + link.getText().trim());
        }
        System.out.println("=== DEBUG: Elements with 'report' text ===");
        List<WebElement> els = driver.findElements(
            By.xpath("//*[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'report')]")
        );
        for (WebElement el : els) {
            System.out.println("  <" + el.getTagName() + "> text=" + el.getText().trim());
        }
    }
}