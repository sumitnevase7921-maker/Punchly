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

public class DashboardPage {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.js     = (JavascriptExecutor) driver;
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // LOCATORS
    // ─────────────────────────────────────────────────────────────────────────────

    By sidebarDashboard = By.xpath(
        "//span[normalize-space()='DASHBOARD'] | //span[normalize-space()='Dashboard']");

    By greetingHeader = By.xpath(
        "//span[@class='inline-flex items-center gap-4 flex-nowrap whitespace-nowrap']"
    );

    By productivitySubtext = By.xpath(
        "//p[@class='text-gray-600']"
    );

    By timeTodayCard = By.xpath(
        "//h3[normalize-space()='Time Today']"
    );

    By thisWeekCard = By.xpath(
        "(//div[@class='flex items-center justify-between mb-4'])[2]"
    );

    By weeklyActivitySection = By.xpath(
        "//*[normalize-space(text())='Weekly Activity']"
    );

    By projectBreakdownSection = By.xpath(
        "//h3[normalize-space()='Project Breakdown']"
    );

    By recentActivitySection = By.xpath(
        "//h3[normalize-space()='Recent Activity']"
    );

    By teamOverviewSection = By.xpath(
        "//h3[normalize-space()='Team Overview']"
    );

    // ─────────────────────────────────────────────────────────────────────────────
    // HELPERS
    // ─────────────────────────────────────────────────────────────────────────────

    private void pause(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // NAVIGATION
    // Click Dashboard from sidebar — used after landing on Time Tracker post-login
    // ─────────────────────────────────────────────────────────────────────────────

    public void goToDashboard() {
        try {
            WebElement link = wait.until(
                ExpectedConditions.elementToBeClickable(sidebarDashboard));
            js.executeScript("arguments[0].click();", link);
            pause(1500);
            System.out.println("✅ Clicked Dashboard in sidebar");
        } catch (Exception e) {
            // Fallback: direct URL navigation
            System.out.println("⚠ Sidebar click failed — navigating directly to Dashboard");
            driver.get("https://dev-app.punchly.work/en/dashboard");
            pause(1500);
        }
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // PAGE CHECKS
    // ─────────────────────────────────────────────────────────────────────────────

    public boolean isOnDashboardPage() {
        return driver.getCurrentUrl().contains("/en/dashboard");
    }

    public String getGreetingText() {
        WebElement el = wait.until(
            ExpectedConditions.visibilityOfElementLocated(greetingHeader));
        return el.getText();
    }

    public boolean isGreetingHeaderDisplayed() {
        try {
            return wait.until(ExpectedConditions
                .visibilityOfElementLocated(greetingHeader)).isDisplayed();
        } catch (Exception e) { return false; }
    }

    public boolean isProductivitySubtextDisplayed() {
        try {
            return wait.until(ExpectedConditions
                .visibilityOfElementLocated(productivitySubtext)).isDisplayed();
        } catch (Exception e) { return false; }
    }

    public boolean isTimeTodayCardDisplayed() {
        try {
            return wait.until(ExpectedConditions
                .visibilityOfElementLocated(timeTodayCard)).isDisplayed();
        } catch (Exception e) { return false; }
    }

    public boolean isThisWeekCardDisplayed() {
        try {
            return wait.until(ExpectedConditions
                .visibilityOfElementLocated(thisWeekCard)).isDisplayed();
        } catch (Exception e) { return false; }
    }

    public boolean isWeeklyActivityDisplayed() {
        try {
            return wait.until(ExpectedConditions
                .visibilityOfElementLocated(weeklyActivitySection)).isDisplayed();
        } catch (Exception e) { return false; }
    }

    public boolean isProjectBreakdownDisplayed() {
        try {
            return wait.until(ExpectedConditions
                .visibilityOfElementLocated(projectBreakdownSection)).isDisplayed();
        } catch (Exception e) { return false; }
    }

    public boolean isRecentActivityDisplayed() {
        try {
            return wait.until(ExpectedConditions
                .visibilityOfElementLocated(recentActivitySection)).isDisplayed();
        } catch (Exception e) { return false; }
    }

    public boolean isTeamOverviewDisplayed() {
        try {
            return wait.until(ExpectedConditions
                .visibilityOfElementLocated(teamOverviewSection)).isDisplayed();
        } catch (Exception e) { return false; }
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // SCROLL TO BOTTOM
    // ─────────────────────────────────────────────────────────────────────────────

    public void scrollToBottom() throws InterruptedException {

        // Strategy 1: find inner scrollable container
        try {
            List<WebElement> scrollables = driver.findElements(By.xpath(
                "//main | " +
                "//div[contains(@class,'overflow-y-auto')] | " +
                "//div[contains(@class,'overflow-auto')] | " +
                "//div[contains(@class,'overflow-y-scroll')] | " +
                "//div[contains(@class,'scroll-area')]"
            ));
            if (!scrollables.isEmpty()) {
                for (WebElement el : scrollables) {
                    Long scrollHeight = (Long) js.executeScript(
                        "return arguments[0].scrollHeight;", el);
                    if (scrollHeight != null && scrollHeight > 500) {
                        js.executeScript(
                            "arguments[0].scrollTop = arguments[0].scrollHeight;", el);
                        pause(800);
                        System.out.println("✅ Scrolled inner container (scrollHeight="
                            + scrollHeight + ")");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("⚠ Inner container scroll failed: " + e.getMessage());
        }

        // Strategy 2: window scroll
        try {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            pause(500);
        } catch (Exception ignored) {}

        // Strategy 3: documentElement scroll
        try {
            js.executeScript(
                "document.documentElement.scrollTop = document.documentElement.scrollHeight;");
            pause(500);
        } catch (Exception ignored) {}

        // Strategy 4: END key
        try {
            driver.findElement(By.tagName("body")).sendKeys(Keys.END);
            pause(500);
        } catch (Exception ignored) {}

        pause(1000);
        System.out.println("✅ Scrolled to bottom of Dashboard successfully");
    }
}