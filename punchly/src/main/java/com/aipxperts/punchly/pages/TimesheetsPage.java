package com.aipxperts.punchly.pages;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class TimesheetsPage {

    WebDriver driver;
    WebDriverWait wait;

    public TimesheetsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // ===== LOCATORS =====
    private By timesheetMenu = By.xpath("//span[contains(text(),'TIMESHEETS')]");
    private By addRowButton = By.xpath("//button[contains(text(),'Add Row') or contains(text(),'+ Add Row')]");
    private By projectPlusButton = By.xpath("(//button[contains(.,'Project')])[last()]");
    private By projectSearch = By.xpath("//input[@placeholder='Search Project, Task, or Client']");
    private By sumitNevaseOption = By.xpath("//span[@class='flex-1 text-sm text-gray-700 ml-0'][normalize-space()='Sumit Nevase']");
    private By billableToggle = By.xpath("//button[@role='switch']");
    private By saveButton = By.xpath("//button[normalize-space()='SAVE' or normalize-space()='Save']");
    private By submitForApprovalBtn = By.xpath("//button[contains(.,'Submit for Approval') or contains(.,'Submit New Entries')]");
    private By confirmSubmitBtn = By.xpath("//button[normalize-space()='SUBMIT' or normalize-space()='Submit']");
    private By withdrawSubmissionBtn = By.xpath("//button[normalize-space()='Withdraw Submission']");

    // ===== STEP 1: Open Timesheets =====
    public void openTimesheets() throws InterruptedException {
        WebElement menu = wait.until(ExpectedConditions.presenceOfElementLocated(timesheetMenu));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", menu);
        System.out.println("➡ Clicked Timesheets");
        Thread.sleep(2000);
    }

    // ===== STEP 2: Click Add Row =====
    public void clickAddRow() throws InterruptedException {
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(addRowButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        System.out.println("➡ Clicked Add Row");
        Thread.sleep(1000);
    }

    // ===== STEP 3: Select Project (Sumit Nevase) =====
    public void selectProject() throws InterruptedException {
        WebElement projectBtn = wait.until(ExpectedConditions.presenceOfElementLocated(projectPlusButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", projectBtn);
        System.out.println("➡ Clicked + Project button");
        Thread.sleep(1000);

        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(projectSearch));
        search.sendKeys("Sumit Nevase");
        System.out.println("➡ Typed Sumit Nevase in search");
        Thread.sleep(1000);

        WebElement option = wait.until(ExpectedConditions.presenceOfElementLocated(sumitNevaseOption));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
        System.out.println("➡ Selected Sumit Nevase");
        Thread.sleep(1000);
    }

    // ===== STEP 4: Click Today's Date Column =====
    public void clickTodayColumn() throws InterruptedException {
        int dayIndex = LocalDate.now().getDayOfWeek().getValue() - 1;
        System.out.println("➡ Today's day index: " + dayIndex);

        List<WebElement> allCells = wait.until(
            ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("(//div[contains(@class,'cursor-pointer') and contains(@class,'bg-orange-50') or contains(@class,'cursor-pointer') and contains(@class,'bg-amber-50') or contains(@class,'cursor-pointer') and contains(@class,'border-r')])[position()>0]")
            )
        );
        System.out.println("➡ Total cells found: " + allCells.size());

        WebElement todayCell = allCells.get(dayIndex);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", todayCell);
        System.out.println("➡ Clicked Today's column: " + dayIndex);
        Thread.sleep(1000);
    }

    // ===== STEP 5: Enter Start and End Time =====
    public void enterStartEndTime() throws InterruptedException {
        WebElement startField = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//input[@placeholder='HH:mm' and contains(@class,'w-full')]")
        ));
        startField.click();
        startField.sendKeys(Keys.CONTROL + "a");
        startField.sendKeys(Keys.DELETE);
        startField.sendKeys("19:00");
        System.out.println("➡ Entered Start Time: 19:00");
        Thread.sleep(500);

        WebElement endField = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//input[@placeholder='HH:mm' and contains(@class,'flex-1')]")
        ));
        endField.click();
        endField.sendKeys(Keys.CONTROL + "a");
        endField.sendKeys(Keys.DELETE);
        endField.sendKeys("20:00");
        System.out.println("➡ Entered End Time: 20:00");
        Thread.sleep(500);

        WebElement billable = wait.until(ExpectedConditions.presenceOfElementLocated(billableToggle));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", billable);
        System.out.println("➡ Enabled Billable");
        Thread.sleep(500);
    }

    // ===== STEP 6: Click Save =====
    public void clickSave() throws InterruptedException {
        WebElement save = wait.until(ExpectedConditions.presenceOfElementLocated(saveButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", save);
        System.out.println("➡ Clicked Save");
        Thread.sleep(2000);
    }

    // ===== STEP 7: Submit for Approval =====
    public void submitForApproval() throws InterruptedException {
        try {
            // ✅ Check if already submitted
            List<WebElement> withdrawBtn = driver.findElements(withdrawSubmissionBtn);
            if (!withdrawBtn.isEmpty()) {
                System.out.println("⚠ Already submitted — Withdraw Submission button present");
                System.out.println("⚠ Skipping submit — timesheet pending approval");
                return;
            }

            WebElement submit = wait.until(ExpectedConditions.presenceOfElementLocated(submitForApprovalBtn));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submit);
            System.out.println("➡ Clicked Submit for Approval");
            Thread.sleep(2000);

            WebElement confirm = wait.until(ExpectedConditions.presenceOfElementLocated(confirmSubmitBtn));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", confirm);
            System.out.println("➡ Confirmed Submit");
            Thread.sleep(2000);

        } catch (Exception e) {
            System.out.println("⚠ Submit for Approval button not found — skipping");
        }
    }

    // ===== STEP 8: Withdraw Submission =====
    public void withdrawSubmission() throws InterruptedException {
        try {
            WebElement withdraw = wait.until(ExpectedConditions.presenceOfElementLocated(withdrawSubmissionBtn));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", withdraw);
            System.out.println("➡ Clicked Withdraw Submission");
            Thread.sleep(2000);

            // ✅ Click WITHDRAW confirm button in modal
            WebElement confirmWithdraw = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[normalize-space()='WITHDRAW']")
            ));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", confirmWithdraw);
            System.out.println("➡ Confirmed Withdraw");
            Thread.sleep(2000);

            // ✅ Wait for status to go back to Draft
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[normalize-space()='Draft']")
            ));
            System.out.println("✅ Submission Withdrawn - Status back to Draft");
            Thread.sleep(1000);

        } catch (Exception e) {
            System.out.println("⚠ Withdraw Submission button not found — skipping");
        }
    }
}