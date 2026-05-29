package com.aipxperts.punchly.pages;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TimeOffPage {

    WebDriver driver;
    WebDriverWait wait;

    public TimeOffPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    
    // ================= LOCATORS =================

    By timeOffMenu = By.xpath("//span[contains(text(),'TIME OFF')]");
    
    
    By balanceTab = By.xpath("(//button[normalize-space()='BALANCE'])[1]");
    
 // ================= BALANCE LOCATORS =================

    By threeDotsMenu = By.xpath("//button[.//*[name()='svg' and contains(@class,'lucide-ellipsis-vertical')]]");

    By manageBalanceOption = By.xpath("//div[normalize-space()='Manage balance']");

    By addToBalanceSection = By.xpath("//button[normalize-space()='ADD TO BALANCE']");

    By balanceInput = By.xpath("//input[@type='number']");

    By addButton = By.xpath("//button[normalize-space()='ADD']");

 // Make sure this is correct in your locators
    By historyOption = By.xpath("//div[@role='menuitem' and normalize-space()='History']");
 // ✅ Exact XPath for History modal close button
      
    
  // old locators before update balance  
    By holidaysTab = By.xpath("(//button[normalize-space()='HOLIDAYS'])[1]");

    By requestButton = By.xpath("//button[normalize-space()='REQUEST TIME OFF']");

    By policyDropdown = By.xpath("//button[@role='combobox']");
    
    By searchPolicy = By.xpath("//input[@placeholder='Search']");
    
    By vacationOption = By.xpath("//span[@class='truncate'][normalize-space()='Vacation']");

    By startDate = By.xpath("//div[@class='grid gap-4 grid-cols-2']//div[1]//div[1]//div[2]//button[1]");
    By endDate = By.xpath("//div[@class='space-y-4']//div[2]//div[1]//div[2]//button[1]");

    
   

    By noteBox = By.xpath("//textarea");
    By submitBtn = By.xpath("//button[@type='submit' and contains(text(),'SUBMIT')]");

    // ================= ACTIONS =================

    public void openTimeOff() throws InterruptedException {
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(timeOffMenu)).click();
        System.out.println("➡ Clicked Time Off");
    }
 
    
    public void addBalance(String days) throws InterruptedException {
        // Click Balance tab
        wait.until(ExpectedConditions.elementToBeClickable(balanceTab)).click();
        System.out.println("➡ Clicked Balance Tab");

        // Open three dots menu → Click Manage balance
        wait.until(ExpectedConditions.elementToBeClickable(threeDotsMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(manageBalanceOption)).click();
        System.out.println("➡ Clicked Manage Balance");

        // Add to balance
        wait.until(ExpectedConditions.elementToBeClickable(addToBalanceSection)).click();

        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(balanceInput));
        input.clear();
        input.sendKeys(days);
     // Click ADD
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
        System.out.println("➡ Clicked ADD");

        // ✅ Wait for Manage Balance modal to close
        wait.until(ExpectedConditions.invisibilityOfElementLocated(addButton));
        System.out.println("➡ Manage Balance Modal Closed");
        Thread.sleep(1000);

     // ✅ Click History from still-visible dropdown
        wait.until(ExpectedConditions.elementToBeClickable(historyOption)).click();
        System.out.println("➡ Clicked History");
        Thread.sleep(2000);
     // ✅ Use Actions to click to dismiss dropdown
        Actions actions = new Actions(driver);
        actions.moveByOffset(0, 0).click().perform();
        System.out.println("➡ Actions click to dismiss dropdown");
        Thread.sleep(1000);

        // ✅ One more JS body click to fully clear overlay
        ((JavascriptExecutor) driver).executeScript("document.body.click();");
        Thread.sleep(1000);

        // ✅ Press Escape to clear anything remaining
        driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
        Thread.sleep(1000);

        System.out.println("✅ Balance updated and UI fully ready");
        Thread.sleep(3000);;
    }
    
   
    
      public void clickHolidays() throws InterruptedException {
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(holidaysTab)).click();
        System.out.println("➡ Clicked Holidays Tab");
    }
    
 
    public void clickRequestTimeOff() throws InterruptedException {
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(requestButton)).click();
        System.out.println("➡ Clicked Request Time Off");
    }
    
    
    

      public void selectPolicy() throws InterruptedException {
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(policyDropdown)).click();

        Thread.sleep(3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchPolicy)).sendKeys("Vacation");

        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(vacationOption)).click();

        System.out.println("✅ Selected Policy: Vacation");
    }

    
 // ================= NEW METHODS (USE THESE) =================

   // WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));

    public void selectStartDate() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Step 1: Click Start Date Calendar (FIX THIS LOCATOR if needed)
        WebElement startCalendar = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[@class='grid gap-4 grid-cols-2']//div[1]//div[1]//div[2]//button[1]")
        ));
        startCalendar.click();
        System.out.println("➡ Start Date Calendar Opened");

        // Step 2: Get today's date dynamically
        LocalDate today = LocalDate.now();

        String todayLabel = today.format(
            DateTimeFormatter.ofPattern("EEEE, MMMM d")
        );

        // Step 3: Wait for calendar date & click correct one
        WebElement todayDate = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[contains(@aria-label,'" + todayLabel + "')]")
        ));
        todayDate.click();

        System.out.println("✅ Start Date Selected: " + todayLabel);
    }
    public void selectEndDate() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open End Date calendar
        wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[@class='space-y-4']//div[2]//div[1]//div[2]//button[1]")
        )).click();
        System.out.println("➡ End Date Calendar Opened");

        // Get next day dynamically
        LocalDate nextDay = LocalDate.now().plusDays(1);
        String endDay = String.valueOf(nextDay.getDayOfMonth());

        // Click next day date
        wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("(//button[normalize-space()='" + endDay + "'])[1]")
        )).click();

        System.out.println("✅ End Date Selected: " + endDay);
    }

    public void enterNote(String note) throws InterruptedException {
        Thread.sleep(3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(noteBox)).sendKeys(note);
        System.out.println("➡ Note added");
    }

    public void clickSubmit() throws InterruptedException {
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
        System.out.println("✅ Submitted Time Off Request");
    }
}