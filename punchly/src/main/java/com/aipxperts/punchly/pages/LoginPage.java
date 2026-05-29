package com.aipxperts.punchly.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ================= LOCATORS =================

    By emailField        = By.xpath("//input[@id='email']");
    By continueButton    = By.xpath("//button[@type='submit']");
    By otpBoxes          = By.xpath("//input[@type='text']");

    By errorMessage      = By.xpath("//*[contains(text(),'Invalid') or contains(text(),'not found')]");
    By forgotPasswordBtn = By.xpath("//a[contains(text(),'Forgot')]");
    By resetPageHeader   = By.xpath("//*[contains(text(),'Reset') or contains(text(),'Password')]");

    // ================= ACTIONS =================

    // Step 1: Enter email + Continue
    public void login(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).clear();
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(continueButton).click();
    }

    // Step 2: Enter OTP (000000)
    public void enterOTP() {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(otpBoxes, 4));

        for (int i = 0; i < driver.findElements(otpBoxes).size(); i++) {
            driver.findElements(otpBoxes).get(i).sendKeys("0");
        }
    }

    // ================= VALIDATIONS =================

    public boolean isErrorMessageVisible() {
        return !driver.findElements(errorMessage).isEmpty();
    }

    public boolean isOTPScreenVisible() {
        return driver.findElements(otpBoxes).size() >= 4;
    }

    public boolean isDashboardLoaded() {
        return driver.getCurrentUrl().contains("dashboard");
    }

    // ================= FORGOT PASSWORD =================

    public void clickForgotPassword() {
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordBtn)).click();
    }

    public boolean isResetPageDisplayed() {
        return !driver.findElements(resetPageHeader).isEmpty();
    }
}