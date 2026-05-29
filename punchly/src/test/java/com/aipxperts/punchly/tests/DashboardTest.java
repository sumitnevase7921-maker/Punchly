
package com.aipxperts.punchly.tests;

import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aipxperts.punchly.base.BaseTest;
import com.aipxperts.punchly.pages.DashboardPage;
import com.aipxperts.punchly.pages.LoginPage;

public class DashboardTest extends BaseTest {

    @Test
    public void testDashboard() throws InterruptedException {

        // ===== LOGIN =====
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("sumit.nevase@aipxperts.com");
        loginPage.enterOTP();

        // ===== WAIT: until app moves past login/otp =====
        new WebDriverWait(driver, Duration.ofSeconds(20))
            .until(d -> !driver.getCurrentUrl().contains("login")
                     && !driver.getCurrentUrl().contains("otp"));

        System.out.println("✅ Login success - URL: " + driver.getCurrentUrl());

        // ===== NAVIGATE TO DASHBOARD =====
        // App lands on Time Tracker by default after login, so navigate explicitly
        driver.get("https://dev-app.punchly.work/en/dashboard");

        // Wait for dashboard to fully load
        new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(d -> driver.getCurrentUrl().contains("/en/dashboard"));

        System.out.println("✅ Dashboard loaded - URL: " + driver.getCurrentUrl());

        // ===== ASSERT: we are on dashboard =====
        DashboardPage dashboardPage = new DashboardPage(driver);

        Assert.assertTrue(
            dashboardPage.isOnDashboardPage(),
            "Should be on Dashboard but URL was: " + driver.getCurrentUrl()
        );

        System.out.println("✅ Confirmed on Dashboard page");

        // ===== SCROLL TO BOTTOM =====
        dashboardPage.scrollToBottom();
        System.out.println("✅ Scrolled to bottom of Dashboard successfully");
    }
}
