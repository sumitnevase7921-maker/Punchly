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

public class ProjectsPage {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    public ProjectsPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js     = (JavascriptExecutor) driver;
    }

    // ─── Locators ────────────────────────────────────────────────────────────────

    By sidebarProjects      = By.xpath("//span[normalize-space()='PROJECTS'] | //span[normalize-space()='Projects']");
    By allDropdownBtn       = By.xpath("(//button[.//span[normalize-space()='All']])[1]");
    By allDropdownSearch    = By.xpath("(//input[@placeholder='Search'])[1]");
    By aipxpertsOption      = By.xpath("//*[contains(text(),'Aipxperts')]");

    // Active dropdown - second button in filter row
    By activeDropdownBtn    = By.xpath("(//button[.//span[normalize-space()='Active']])[1]");

    // Dropdown options - using broader locators to match any tag (span, div, li, button)
    By optionActive         = By.xpath("//*[normalize-space()='Active' and not(self::button)]");
    By optionArchived       = By.xpath("//*[normalize-space()='Archived']");

    By searchBar            = By.xpath("//input[@placeholder='Search']");
    By newProjectBtn        = By.xpath("//button[contains(.,'New Project')]");
    By projectNameInput     = By.xpath("//input[@placeholder='Enter project name']");

    // 3-dot menu
    By firstRowThreeDotMenu = By.xpath("//button[@id='radix-_r_d_']");

    // 3-dot menu options - broader locators
    By optionViewDetails    = By.xpath("//*[normalize-space()='View Details']");
    By optionEdit           = By.xpath("//*[normalize-space()='Edit']");
    By optionArchive        = By.xpath("//*[normalize-space()='Archive']");

    // ─── Core Helpers ─────────────────────────────────────────────────────────────

    // safeClick WITHOUT dismissOverlay - so dropdowns stay open
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

    // safeClick WITH dismiss - only used for opening fresh dropdowns
    private void safeClickWithDismiss(By locator, String label) {
        try {
            // Dismiss any existing overlay first
            driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
            Thread.sleep(800);
        } catch (Exception ignored) {}
        safeClick(locator, label);
    }

    // ─── Navigation ──────────────────────────────────────────────────────────────

    public void goToProjects() {
        System.out.println("🔍 Looking for Projects sidebar link...");
        try {
            WebElement projectsLink = wait.until(ExpectedConditions.elementToBeClickable(sidebarProjects));
            js.executeScript("arguments[0].click();", projectsLink);
            System.out.println("✅ Navigated to Projects page");
        } catch (Exception e) {
            printSidebarElements();
            throw new RuntimeException("❌ Projects sidebar link not found.", e);
        }
    }

    // ─── All (Client) Dropdown ────────────────────────────────────────────────────

    public void clickAllDropdown() {
        safeClickWithDismiss(allDropdownBtn, "All Dropdown");
    }

    public void searchClientInAllDropdown(String text) throws InterruptedException {
        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(allDropdownSearch));
        search.clear();
        search.sendKeys(text);
        Thread.sleep(1000);
        System.out.println("✅ Searched client: " + text);
    }

    public void selectAipxpertsClient() {
        // Do NOT dismiss - dropdown must stay open
        safeClick(aipxpertsOption, "Aipxperts Client Option");
    }

    // ─── Active / Archived Dropdown ───────────────────────────────────────────────

    public void clickActiveDropdown() {
        safeClickWithDismiss(activeDropdownBtn, "Active Dropdown");
    }

    public void selectActive() throws InterruptedException {
        Thread.sleep(1000);
        // Do NOT dismiss - dropdown must stay open
        safeClick(optionActive, "Active Option");
    }

    public void selectArchived() throws InterruptedException {
        Thread.sleep(1000);
        // Do NOT dismiss - dropdown must stay open
        safeClick(optionArchived, "Archived Option");
    }

    // ─── Search Bar ───────────────────────────────────────────────────────────────

    public void searchProject(String projectName) throws InterruptedException {
        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBar));
        search.clear();
        search.sendKeys(projectName);
        Thread.sleep(1500);
        System.out.println("✅ Searched project: " + projectName);
    }

    // ─── New Project ──────────────────────────────────────────────────────────────

    public void clickNewProject() {
        safeClickWithDismiss(newProjectBtn, "New Project Button");
    }

    public void enterProjectName(String name) throws InterruptedException {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(projectNameInput));
        input.clear();
        input.sendKeys(name);
        Thread.sleep(1000);
        System.out.println("✅ Entered project name: " + name);
    }

    public void submitProjectName() throws InterruptedException {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(projectNameInput));
        input.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        System.out.println("✅ Submitted project name via Enter");
    }

    // ─── 3-Dot Menu Actions ───────────────────────────────────────────────────────

    public void clickThreeDotMenu() {
        safeClickWithDismiss(firstRowThreeDotMenu, "3-Dot Menu");
    }

    public void clickViewDetails() throws InterruptedException {
        Thread.sleep(1000);
        // Do NOT dismiss - menu must stay open
        safeClick(optionViewDetails, "View Details");
    }

    public void clickEdit() throws InterruptedException {
        Thread.sleep(1000);
        // Do NOT dismiss - menu must stay open
        safeClick(optionEdit, "Edit");
    }

    public void clickArchive() throws InterruptedException {
        Thread.sleep(1000);
        // Do NOT dismiss - menu must stay open
        safeClick(optionArchive, "Archive");
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