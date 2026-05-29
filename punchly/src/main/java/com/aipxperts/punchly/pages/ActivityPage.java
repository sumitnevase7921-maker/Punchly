package com.aipxperts.punchly.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ActivityPage {

    WebDriver driver;
    WebDriverWait wait;

    public ActivityPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Locators
    By activityMenu          = By.xpath("//span[normalize-space()='ACTIVITY']");
    By pageTitle             = By.xpath("//h1[normalize-space()='My Activity']");
    By monitoringBanner      = By.xpath("//span[@class='font-medium text-gray-900 text-sm']");
    By screenshotsTab        = By.xpath("//span[normalize-space()='Screenshots']");
    By locationTab           = By.xpath("//span[normalize-space()='Location']");
    By datePickerButton      = By.xpath("//span[@class='hidden sm:inline']");
    By calendarPrevArrow     = By.xpath("//button[@aria-label='Go to the Previous Month']//*[name()='svg']");
    By calendarNextArrow     = By.xpath("//button[@aria-label='Go to the Next Month']//*[name()='svg']");
    By calendarMonthLabel    = By.xpath("//span[@role='status']");
    By locationFilterButton  = By.xpath("//span[@class='hidden sm:inline']");
    By dateRangeDropdown     = By.xpath("//span[@class='flex items-center gap-2']");
    By optionToday           = By.xpath("//div[normalize-space()='Today']");
    By optionYesterday       = By.xpath("//div[normalize-space()='Yesterday']");
    By optionThisWeek        = By.xpath("//div[normalize-space()='This week']");
    By optionLastWeek        = By.xpath("//div[normalize-space()='Last Week']");
    By optionThisMonth       = By.xpath("//div[normalize-space()='This Month']");
    By optionLastMonth       = By.xpath("//div[normalize-space()='Last Month']");
    By optionThisYear        = By.xpath("//div[normalize-space()='This Year']");
    By optionCustomRange     = By.xpath("//div[normalize-space()='Custom Range']");
    By noScreenshotsMsg      = By.xpath("//*[contains(text(),'No screenshots captured yet')]");
    By noLocationMsg         = By.xpath("//*[contains(text(),'No location data tracked yet')]");

    // Navigate to Activity page via sidebar
    public void navigateToActivityPage() {
        wait.until(ExpectedConditions.elementToBeClickable(activityMenu)).click();
    }

    // Verify page loaded
    public boolean isActivityPageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).isDisplayed();
    }

    // Check monitoring disabled banner
    public boolean isMonitoringDisabledBannerVisible() {
        try { return driver.findElement(monitoringBanner).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    // Click Screenshots tab
    public void clickScreenshotsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(screenshotsTab)).click();
    }

    // Click Location tab
    public void clickLocationTab() {
        wait.until(ExpectedConditions.elementToBeClickable(locationTab)).click();
    }

    // Open calendar on Screenshots tab
    public void openDatePicker() {
        wait.until(ExpectedConditions.elementToBeClickable(datePickerButton)).click();
    }

    // Select a day from the open calendar
    public void selectDayFromCalendar(String day) {
        By dayBtn = By.xpath("//button[@name='day' and normalize-space(.)='" + day + "']");
        wait.until(ExpectedConditions.elementToBeClickable(dayBtn)).click();
    }

    // Go to previous month in calendar
    public void clickPreviousMonth() {
        wait.until(ExpectedConditions.elementToBeClickable(calendarPrevArrow)).click();
    }

    // Go to next month in calendar
    public void clickNextMonth() {
        wait.until(ExpectedConditions.elementToBeClickable(calendarNextArrow)).click();
    }

    // Get current month+year text from calendar
    public String getCalendarMonthYear() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(calendarMonthLabel)).getText();
    }

    // Check no screenshots empty state
    public boolean isNoScreenshotsMsgVisible() {
        try { return driver.findElement(noScreenshotsMsg).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    // Open date range dropdown on Location tab
    public void openDateRangeDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(locationFilterButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(dateRangeDropdown)).click();
    }

    public void selectToday()       { wait.until(ExpectedConditions.elementToBeClickable(optionToday)).click(); }
    public void selectYesterday()   { wait.until(ExpectedConditions.elementToBeClickable(optionYesterday)).click(); }
    public void selectThisWeek()    { wait.until(ExpectedConditions.elementToBeClickable(optionThisWeek)).click(); }
    public void selectLastWeek()    { wait.until(ExpectedConditions.elementToBeClickable(optionLastWeek)).click(); }
    public void selectThisMonth()   { wait.until(ExpectedConditions.elementToBeClickable(optionThisMonth)).click(); }
    public void selectLastMonth()   { wait.until(ExpectedConditions.elementToBeClickable(optionLastMonth)).click(); }
    public void selectThisYear()    { wait.until(ExpectedConditions.elementToBeClickable(optionThisYear)).click(); }
    public void selectCustomRange() { wait.until(ExpectedConditions.elementToBeClickable(optionCustomRange)).click(); }

    // Check no location empty state
    public boolean isNoLocationMsgVisible() {
        try { return driver.findElement(noLocationMsg).isDisplayed(); }
        catch (Exception e) { return false; }
    }
}