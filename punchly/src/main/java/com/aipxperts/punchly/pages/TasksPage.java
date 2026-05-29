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

public class TasksPage {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    public TasksPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js     = (JavascriptExecutor) driver;
    }

    // ─── Locators ────────────────────────────────────────────────────────────────

    // Sidebar
    By sidebarTasks = By.xpath("//span[normalize-space()='TASKS'] | //span[normalize-space()='Tasks']");

    // Search bar
    By searchByName = By.xpath("//input[@placeholder='Search by name']");

    // All Projects dropdown
    By allProjectsDropdown     = By.xpath("//button[contains(.,'All projects') or .//span[normalize-space()='All projects']]");
    By allProjectsSearch       = By.xpath("//input[@placeholder='Search projects...']");
    By allProjectsOption       = By.xpath("//*[normalize-space()='All projects']");
    By noProjectOption         = By.xpath("//*[normalize-space()='No project']");
    By sumitNevaseProjOption   = By.xpath("//*[normalize-space()='Sumit Nevase']");

    // Active dropdown
    By activeDropdown  = By.xpath("//button[contains(.,'Active') or .//span[normalize-space()='Active']]");
    By optionActive    = By.xpath("//*[normalize-space()='Active']");
    By optionArchived  = By.xpath("//*[normalize-space()='Archived']");

    // Create / Enter task name (inline)
    By createTaskLink  = By.xpath("//input[@id='task-create-input']");
    By taskNameInput   = By.xpath("//input[@placeholder='Enter task name...'] | //input[contains(@placeholder,'task name')]");

    // Checkbox on first task row
    By firstRowCheckbox = By.xpath("(//tr//input[@type='checkbox'] | (//button[@role='checkbox'])[2])[1]");

    // Bulk action bar (appears after checkbox click)
    By assignProjectBtn  = By.xpath("//button[contains(.,'Assign Project')]");
    By assignAssigneeBtn = By.xpath("//button[contains(.,'Assign Assignee')]");
    By archiveBulkBtn    = By.xpath("//button[normalize-space()='Archive'][not(@data-slot)]");
    By clearBtn          = By.xpath("//button[normalize-space()='Clear']");

    // Assign Project modal
    By assignProjectModal       = By.xpath("//div[contains(.,'Assign Project to Tasks')]");
    By selectProjectDropdown    = By.xpath("//button[contains(.,'Select a project')]");
    By projectSearchInModal     = By.xpath("//input[@placeholder='Search projects...'] | //input[@placeholder='S']");
    By sumitNevaseInModal       = By.xpath("//div[normalize-space()='Sumit Nevase'] | //span[normalize-space()='Sumit Nevase']");

    // Assign Assignee modal
    By assignAssigneeModal      = By.xpath("//div[contains(.,'Assign Assignee to Tasks')]");
    By selectAssigneeDropdown   = By.xpath("//button[contains(.,'Select an assignee')]");
    By sumitNevaseAssignee      = By.xpath("//button[@class='relative flex w-full cursor-default select-none items-center rounded-sm px-2 py-1.5 text-sm outline-none hover:bg-gray-100 focus:bg-gray-100 disabled:pointer-events-none disabled:opacity-50']//span[@class='truncate'][normalize-space()='Sumit Nevase']");

    // Archive Tasks modal
    By archiveModalTitle  = By.xpath("//*[normalize-space()='Archive Tasks']");
    By archiveConfirmBtn  = By.xpath("//button[normalize-space()='Archive']");
    By archiveCancelBtn   = By.xpath("//button[normalize-space()='Cancel']");

    // 3-dot menu on first task row
    By firstRowThreeDot = By.xpath("//button[@aria-label[contains(.,'Actions for task')]]");
    // 3-dot menu options
    By optionEdit        = By.xpath("//*[normalize-space()='Edit']");
    By optionDelete      = By.xpath("//*[normalize-space()='Delete']");
    By optionArchiveRow  = By.xpath("//*[normalize-space()='Archive']");

    // ─── Core Helpers ─────────────────────────────────────────────────────────────

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

    private void safeClickWithDismiss(By locator, String label) {
        try {
            driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
            Thread.sleep(800);
        } catch (Exception ignored) {}
        safeClick(locator, label);
    }

    // ─── Navigation ──────────────────────────────────────────────────────────────

    public void goToTasks() {
        System.out.println("🔍 Looking for Tasks sidebar link...");
        try {
            WebElement link = wait.until(ExpectedConditions.elementToBeClickable(sidebarTasks));
            js.executeScript("arguments[0].click();", link);
            System.out.println("✅ Navigated to Tasks page");
        } catch (Exception e) {
            printSidebarElements();
            throw new RuntimeException("❌ Tasks sidebar link not found.", e);
        }
    }

    // ─── Search ───────────────────────────────────────────────────────────────────

    public void searchByName(String name) throws InterruptedException {
        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(searchByName));
        search.clear();
        search.sendKeys(name);
        Thread.sleep(1500);
        System.out.println("✅ Searched by name: " + name);
    }

    // ─── All Projects Dropdown ────────────────────────────────────────────────────

    public void clickAllProjectsDropdown() {
        safeClickWithDismiss(allProjectsDropdown, "All Projects Dropdown");
    }

    public void searchInAllProjectsDropdown(String text) throws InterruptedException {
        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(allProjectsSearch));
        search.clear();
        search.sendKeys(text);
        Thread.sleep(1000);
        System.out.println("✅ Searched in All Projects dropdown: " + text);
    }

    public void selectAllProjects() throws InterruptedException {
        Thread.sleep(500);
        safeClick(allProjectsOption, "All Projects Option");
    }

    public void selectSumitNeveseProject() throws InterruptedException {
        Thread.sleep(500);
        safeClick(sumitNevaseProjOption, "Sumit Nevase Project");
    }

    // ─── Active / Archived Dropdown ───────────────────────────────────────────────

    public void clickActiveDropdown() {
        safeClickWithDismiss(activeDropdown, "Active Dropdown");
    }

    public void selectActive() throws InterruptedException {
        Thread.sleep(500);
        safeClick(optionActive, "Active Option");
    }

    public void selectArchived() throws InterruptedException {
        Thread.sleep(500);
        safeClick(optionArchived, "Archived Option");
    }

    // ─── Create Task ──────────────────────────────────────────────────────────────

    public void clickCreateTask() {
        safeClickWithDismiss(createTaskLink, "Create Task Link");
    }

    public void enterTaskName(String name) throws InterruptedException {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(taskNameInput));
        input.clear();
        input.sendKeys(name);
        Thread.sleep(1000);
        System.out.println("✅ Entered task name: " + name);
    }

    public void submitTaskName() throws InterruptedException {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(taskNameInput));
        input.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        System.out.println("✅ Submitted task name");
    }

    // ─── Checkbox ────────────────────────────────────────────────────────────────

    public void clickFirstRowCheckbox() {
        safeClick(firstRowCheckbox, "First Row Checkbox");
    }

    // ─── Bulk Action Bar ──────────────────────────────────────────────────────────

    public void clickAssignProject() {
        safeClick(assignProjectBtn, "Assign Project Button");
    }

    public void clickAssignAssignee() {
        safeClick(assignAssigneeBtn, "Assign Assignee Button");
    }

    public void clickArchiveBulk() {
        safeClick(archiveBulkBtn, "Archive Bulk Button");
    }

    public void clickClear() {
        safeClick(clearBtn, "Clear Button");
    }

    // ─── Assign Project Modal ─────────────────────────────────────────────────────

    public void selectProjectInModal(String projectName) throws InterruptedException {
        safeClick(selectProjectDropdown, "Select Project Dropdown");
        Thread.sleep(1000);
        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(projectSearchInModal));
        search.sendKeys(projectName);
        Thread.sleep(1000);
        safeClick(sumitNevaseInModal, "Sumit Nevase in Modal");
    }

    // ─── Assign Assignee Modal ────────────────────────────────────────────────────

    /*public void selectAssigneeInModal() throws InterruptedException {
        safeClick(selectAssigneeDropdown, "Select Assignee Dropdown");
        Thread.sleep(1000);
        safeClick(sumitNevaseAssignee, "Sumit Nevase Assignee");
    }*/
    
    public void selectAssigneeInModal() throws InterruptedException {
        safeClick(selectAssigneeDropdown, "Select Assignee Dropdown");
        Thread.sleep(1500);
        By sumitNevaseAssignee = By.xpath("//button[@class='relative flex w-full cursor-default select-none items-center rounded-sm px-2 py-1.5 text-sm outline-none hover:bg-gray-100 focus:bg-gray-100 disabled:pointer-events-none disabled:opacity-50']//span[@class='truncate'][normalize-space()='Sumit Nevase']");
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(sumitNevaseAssignee));
        js.executeScript("arguments[0].click();", el);
        System.out.println("✅ Clicked: Sumit Nevase Assignee");
        Thread.sleep(1000);
    }

    // ─── Archive Modal ────────────────────────────────────────────────────────────

    public void confirmArchive() throws InterruptedException {
        Thread.sleep(500);
        safeClick(archiveConfirmBtn, "Archive Confirm Button");
    }

    public void cancelArchive() throws InterruptedException {
        Thread.sleep(500);
        safeClick(archiveCancelBtn, "Archive Cancel Button");
    }

    // ─── 3-Dot Menu ───────────────────────────────────────────────────────────────

    public void clickThreeDotMenu() {
        safeClickWithDismiss(firstRowThreeDot, "3-Dot Menu");
    }

    public void clickEdit() throws InterruptedException {
        Thread.sleep(500);
        safeClick(optionEdit, "Edit");
    }

    public void clickDelete() throws InterruptedException {
        Thread.sleep(500);
        safeClick(optionDelete, "Delete");
    }

    public void clickArchiveFromMenu() throws InterruptedException {
        Thread.sleep(500);
        safeClick(optionArchiveRow, "Archive from Menu");
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