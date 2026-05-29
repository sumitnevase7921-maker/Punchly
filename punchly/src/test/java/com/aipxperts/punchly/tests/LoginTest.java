package com.aipxperts.punchly.tests;

import com.aipxperts.punchly.base.BaseTest;
import com.aipxperts.punchly.pages.LoginPage;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends BaseTest {

    // TC_LOGIN_001 - Valid Login
    @Test(priority = 1)
    public void TC_LOGIN_001_validLogin() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("sumit.nevase@aipxperts.com");
        loginPage.enterOTP();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.urlContains("dashboard"));

        Assert.assertTrue(loginPage.isDashboardLoaded(),
                "FAIL: Dashboard not loaded");

        System.out.println("PASS: TC_LOGIN_001 - Valid Login");
    }

    // TC_LOGIN_002 - Invalid Email
    @Test(priority = 2)
    public void TC_LOGIN_002_invalidEmail() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("invalid@test.com");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//*[contains(text(),'Invalid')]")),
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//*[contains(text(),'not found')]"))
        ));

        Assert.assertTrue(loginPage.isErrorMessageVisible(),
                "FAIL: Error message not displayed");

        System.out.println("PASS: TC_LOGIN_002 - Invalid Login");
    }

    // TC_LOGIN_003 - Empty Email
    @Test(priority = 3)
    public void TC_LOGIN_003_emptyEmail() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("");

        String value = driver.findElement(By.xpath("//input[@id='email']")).getAttribute("value");

        Assert.assertTrue(value.isEmpty(),
                "FAIL: Email field should be empty");

        System.out.println("PASS: TC_LOGIN_003 - Empty Email Validation");
    }

    // TC_LOGIN_004 - Forgot Password
    @Test(priority = 4)
    public void TC_LOGIN_004_forgotPassword() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickForgotPassword();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("reset"),
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//*[contains(text(),'Reset')]"))
        ));

        Assert.assertTrue(loginPage.isResetPageDisplayed(),
                "FAIL: Reset page not opened");

        System.out.println("PASS: TC_LOGIN_004 - Forgot Password");
    }

    // TC_LOGIN_005 - OTP Screen Validation
    @Test(priority = 5)
    public void TC_LOGIN_005_verifyOTPScreen() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("sumit.nevase@aipxperts.com");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.xpath("//input[@type='text']"), 4
        ));

        Assert.assertTrue(loginPage.isOTPScreenVisible(),
                "FAIL: OTP screen not displayed");

        System.out.println("PASS: TC_LOGIN_005 - OTP Screen");
    }
}