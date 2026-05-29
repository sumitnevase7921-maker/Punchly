package com.aipxperts.punchly.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class KiosksPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final JavascriptExecutor js;
    private final Actions actions;

    public KiosksPage(WebDriver driver) {
        this.driver  = driver;
        this.wait    = new WebDriverWait(driver, Duration.ofSeconds(25));
        this.js      = (JavascriptExecutor) driver;
        this.actions = new Actions(driver);
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // HELPERS
    // ─────────────────────────────────────────────────────────────────────────────

    private void safeClick(By locator, String label) {
        try {
            WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", el);
            pause(300);
            js.executeScript("arguments[0].click();", el);
            System.out.println("✅ Clicked: " + label);
        } catch (Exception e) {
            System.out.println("⚠ safeClick failed [" + label + "]: " + e.getMessage());
            throw new RuntimeException("❌ Could not click: " + label, e);
        }
    }

    private void fillField(By locator, String value, String label) {
        try {
            WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", el);
            pause(200);
            js.executeScript("arguments[0].value='';", el);
            el.click();
            pause(200);
            el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            el.sendKeys(Keys.DELETE);
            actions.moveToElement(el).click().sendKeys(value).perform();
            pause(400);
            System.out.println("✅ Filled [" + label + "]: " + value);
        } catch (Exception e) {
            System.out.println("⚠ fillField failed [" + label + "]: " + e.getMessage());
            throw new RuntimeException("❌ Could not fill field: " + label, e);
        }
    }

    private void menuClick(By locator, String label) {
        try {
            WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            pause(400);
            js.executeScript("arguments[0].click();", el);
            pause(800);
            System.out.println("✅ Menu clicked: " + label);
        } catch (Exception e) {
            System.out.println("⚠ menuClick failed [" + label + "]: " + e.getMessage());
            throw new RuntimeException("❌ Could not click: " + label, e);
        }
    }

    private void pause(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // NAVIGATION
    // ─────────────────────────────────────────────────────────────────────────────

    public void goToKiosks() {
        By loc = By.xpath(
            "//span[normalize-space()='KIOSKS'] | //span[normalize-space()='Kiosks']");
        try {
            WebElement link = wait.until(ExpectedConditions.elementToBeClickable(loc));
            js.executeScript("arguments[0].click();", link);
            pause(1500);
            System.out.println("✅ Navigated to Kiosks page");
        } catch (Exception e) {
            throw new RuntimeException("❌ Kiosks sidebar link not found.", e);
        }
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // STATUS DROPDOWN  (Active / Inactive)
    // ─────────────────────────────────────────────────────────────────────────────

    public void clickStatusDropdown() {
        By loc = By.xpath(
            "//button[contains(.,'Active') or contains(.,'Inactive')]" +
            "[not(contains(@class,'status'))] | " +
            "(//button[@role='combobox'])[1]");
        safeClick(loc, "Status Dropdown");
        pause(800);
    }

    public void selectStatusActive() {
        By loc = By.xpath("//*[normalize-space()='Active'][not(ancestor::table)]");
        safeClick(loc, "Status: Active");
        pause(1000);
    }

    public void selectStatusInactive() {
        By loc = By.xpath("//*[normalize-space()='Inactive']");
        safeClick(loc, "Status: Inactive");
        pause(1000);
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // SEARCH BAR
    // ─────────────────────────────────────────────────────────────────────────────

    public void searchKiosk(String name) {
        By loc = By.xpath(
            "//input[@placeholder='Search kiosks...'] | " +
            "//input[contains(@placeholder,'Search kiosk')]");
        fillField(loc, name, "Search Kiosks");
        pause(1000);
    }

    public void clickSearchBar() {
        By loc = By.xpath(
            "//input[@placeholder='Search kiosks...'] | " +
            "//input[contains(@placeholder,'Search kiosk')]");
        safeClick(loc, "Search Bar");
        pause(500);
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // CREATE KIOSK MODAL
    // Flow: clickCreateKioskButton() → fill form → clickCreate() / clickCancel()
    // ─────────────────────────────────────────────────────────────────────────────

    public void clickCreateKioskButton() {
        By loc = By.xpath("//button[.//text()[contains(.,'Create Kiosk')]]");
        safeClick(loc, "Create Kiosk Button");
        pause(1000);
    }

    public void enterKioskName(String name) {
        By loc = By.xpath("//input[@placeholder='e.g., Main Office Kiosk']");
        fillField(loc, name, "Kiosk Name");
    }

   /* public void clickAssigneesDropdown() {
        By loc = By.xpath(
            "//span[contains(@class,'text-muted-foreground') and normalize-space()='Select team members...']");
        safeClick(loc, "Assignees Dropdown");
        pause(800);
    }*/
    public void clickAssigneesDropdown() {
        By loc = By.xpath(
            "//span[contains(@class,'text-muted-foreground') and normalize-space()='Select team members...']" +
            "/ancestor::button[1] | " +
            "//span[contains(@class,'text-muted-foreground') and normalize-space()='Select team members...']" +
            "/ancestor::div[@role='combobox'][1] | " +
            "//span[contains(@class,'text-muted-foreground') and normalize-space()='Select team members...']" +
            "/parent::*"
        );
        try {
            WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(loc));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", el);
            pause(500);
            js.executeScript("arguments[0].click();", el);
            pause(1000);
            System.out.println("✅ Clicked: Assignees Dropdown");
        } catch (Exception e) {
            throw new RuntimeException("❌ Could not click: Assignees Dropdown", e);
        }
    }

    
    public void searchAndSelectAssignee(String name) {
        By option = By.xpath(
            "//*[normalize-space()='" + name + "'][not(self::input)]"
        );
        try {
            // JS-force click the dropdown trigger
            clickAssigneesDropdown();
            pause(1000);

            WebElement user = wait.until(ExpectedConditions.presenceOfElementLocated(option));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", user);
            pause(300);
            js.executeScript("arguments[0].click();", user);

            System.out.println("✅ Assignee selected: " + name);
        } catch (Exception e) {
            throw new RuntimeException("❌ Assignee not found: " + name, e);
        }
    }
  /*  public void searchAndSelectAssignee(String name) {
        By dropdown = By.xpath(
            "//span[contains(@class,'text-muted-foreground') and normalize-space()='Select team members...']"
        );
        By option = By.xpath(
            "//div[@role='option' or contains(@class,'option')]//span[normalize-space()='" + name + "'] | " +
            "//div[@role='option' or contains(@class,'option')][normalize-space()='" + name + "']"
        );
        try {
            // Open dropdown
            safeClick(dropdown, "Assignee Dropdown");

            // Wait for options
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@role='option' or contains(@class,'option')]")
            ));

            // Click user directly (NO SEARCH)
            WebElement user = wait.until(ExpectedConditions.elementToBeClickable(option));
            user.click();

            System.out.println("✅ Assignee selected: " + name);
        } catch (Exception e) {
            throw new RuntimeException("❌ Assignee not found: " + name, e);
        }
    }*/

    public void clickDefaultProjectDropdown() {
        By loc = By.xpath("//div[@class='flex items-center gap-2 flex-1 overflow-hidden min-w-0']");
        safeClick(loc, "Project Dropdown");
        pause(800);
    }

    public void searchAndSelectProject(String projectName) {
        By dropdown = By.xpath("//input[@placeholder='Search Project, Task, or Client']");
        By option = By.xpath(
            "//div[contains(@class,'menu')]//*[normalize-space()='" + projectName + "']"
        );
        try {
            safeClick(dropdown, "Project Dropdown");

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'menu')]")
            ));

            WebElement proj = wait.until(ExpectedConditions.elementToBeClickable(option));
            proj.click();

            System.out.println("✅ Project selected: " + projectName);
        } catch (Exception e) {
            throw new RuntimeException("❌ Project not found: " + projectName, e);
        }
    }

    public void clickSessionTimeoutDropdown() {
        By loc = By.xpath("//label[text()='Session Timeout']/following::button[1]");
        safeClick(loc, "Session Timeout Dropdown");
        pause(800);
    }

    public void selectSessionTimeout(String option) {
        By loc = By.xpath("//div[@role='option' and normalize-space()='" + option + "']");
        safeClick(loc, "Session Timeout: " + option);
        pause(500);
    }

    public void clickCreateButton() {
        By loc = By.xpath("//button[normalize-space()='Create']");
        safeClick(loc, "Create Button");
        pause(2000);
    }

    public void clickCancelButton() {
        By loc = By.xpath("//button[normalize-space()='Cancel']");
        safeClick(loc, "Cancel Button");
        pause(1000);
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // LAUNCH KIOSK BUTTON
    // ─────────────────────────────────────────────────────────────────────────────

    public void clickLaunchKiosk() {
        By loc = By.xpath(
            "//button[contains(.,'Launch Kiosk')] | " +
            "//a[contains(.,'Launch Kiosk')]");
        safeClick(loc, "Launch Kiosk Button");
        pause(2000);
    }

    public void switchToKioskTab() {
        try {
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            if (tabs.size() > 1) {
                driver.switchTo().window(tabs.get(tabs.size() - 1));
                pause(1500);
                System.out.println("✅ Switched to Kiosk tab");
            }
        } catch (Exception e) {
            System.out.println("⚠ Could not switch to Kiosk tab: " + e.getMessage());
        }
    }

    public void switchBackToMainTab() {
        try {
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(0));
            pause(1000);
            System.out.println("✅ Switched back to main tab");
        } catch (Exception e) {
            System.out.println("⚠ Could not switch back to main tab: " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // 3-DOT MENU  (Copy URL / Edit / Deactivate / Delete)
    // ─────────────────────────────────────────────────────────────────────────────

    public void clickFirstRowThreeDotMenu() {
        By rowLoc = By.xpath("(//tbody//tr)[1]");
        By dotLoc = By.xpath(
            "(//tbody//tr)[1]//button[@data-slot='dropdown-menu-trigger']");
        try {
            WebElement row = wait.until(ExpectedConditions.visibilityOfElementLocated(rowLoc));
            actions.moveToElement(row).perform();
            pause(700);
        } catch (Exception ignored) {}
        safeClick(dotLoc, "3-Dot Menu");
        pause(800);
    }

    public void clickMenuCopyUrl() {
        By loc = By.xpath("//*[@role='menuitem' and normalize-space()='Copy URL']");
        menuClick(loc, "Menu → Copy URL");
    }

    public void clickMenuEdit() {
        By loc = By.xpath("//*[@role='menuitem' and normalize-space()='Edit']");
        menuClick(loc, "Menu → Edit");
    }

    public void clickMenuDeactivate() {
        By loc = By.xpath("//*[@role='menuitem' and normalize-space()='Deactivate']");
        menuClick(loc, "Menu → Deactivate");
    }

    public void clickMenuDelete() {
        By loc = By.xpath("//*[@role='menuitem' and normalize-space()='Delete']");
        menuClick(loc, "Menu → Delete");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // KIOSK LAUNCH PAGE  (clock in/out screen)
    // ─────────────────────────────────────────────────────────────────────────────

    public void searchKioskMember(String nameOrEmail) {
        By loc = By.xpath(
            "//input[@placeholder='Search by name or email...'] | " +
            "//input[contains(@placeholder,'Search by name')]");
        fillField(loc, nameOrEmail, "Kiosk Member Search");
        pause(1000);
    }

    public void clickKioskMember(String name) {
        By loc = By.xpath("(//*[contains(normalize-space(),'" + name + "')])[1]");
        safeClick(loc, "Kiosk Member: " + name);
        pause(1000);
    }
}