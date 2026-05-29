package com.aipxperts.punchly.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ExpensesPage {

    WebDriver driver;
    WebDriverWait wait;

    private List<String> categories = Arrays.asList(
            "Accommodation",
            "Communication",
            "Equipment",
            "Marketing & Advertising",
            "Meals & Entertainment",
            "Mileage",
            "Office Supplies",
            "Other",
            "Software & Subscriptions"
    );

    public ExpensesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // ===== LOCATORS (UNCHANGED) =====
    private By expensesMenu = By.xpath("//span[normalize-space()='EXPENSES']");
    private By teamMemberDropdown = By.xpath("/html[1]/body[1]/div[2]/div[1]/main[1]/div[1]/div[2]/div[1]/div[1]/button[1]/span[1]");
    private By sumitNevaseOption = By.xpath("//span[normalize-space()='Sumit Nevase']");
    private By manageCategoriesBtn = By.xpath("//span[@class='hidden sm:inline']");
    private By backButton = By.xpath("//button[@class=\"inline-flex items-center justify-center gap-2 whitespace-nowrap rounded-md text-sm font-medium transition-all disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg:not([class*='size-'])]:size-4 shrink-0 [&_svg]:shrink-0 outline-none focus-visible:border-ring focus-visible:ring-ring/50 focus-visible:ring-[3px] aria-invalid:ring-destructive/20 dark:aria-invalid:ring-destructive/40 aria-invalid:border-destructive cursor-pointer hover:text-accent-foreground dark:hover:bg-accent/50 size-9 hover:bg-gray-100\"]");
    private By addExpenseBtn = By.xpath("//button[normalize-space()='Add Expense']");
    private By projectTaskField = By.xpath("//span[@class='text-muted-foreground text-sm md:text-base truncate']");
    private By sumitNevaseProjectOption = By.xpath("//span[@class='flex-1 text-sm text-gray-700 ml-0'][normalize-space()='Sumit Nevase']");
    private By categoryDropdown = By.xpath("//span[normalize-space()='Select category']/..");
    private By categorySearch = By.xpath("//input[@placeholder='Search']");
    private By amountField = By.xpath("//input[@placeholder='Enter amount']");
    private By descriptionField = By.xpath("//textarea[@placeholder='Add note']");
    private By billableToggle = By.xpath("//button[@role='switch']");
    private By createButton = By.xpath("//button[normalize-space()='Create']");
    private By submitForApprovalBtn = By.xpath("//button[contains(.,'Submit for Approval')]");
    private By submitFinalBtn = By.xpath("//button[normalize-space()='Submit']");

    // ===== METHODS =====

    public void openExpenses() throws InterruptedException {
        WebElement expenses = wait.until(ExpectedConditions.presenceOfElementLocated(expensesMenu));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", expenses);
        System.out.println("➡ Clicked Expenses");
        Thread.sleep(2000);
    }

    public void selectTeamMember() throws InterruptedException {
        WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(teamMemberDropdown));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdown);
        System.out.println("➡ Clicked Team Member Dropdown");
        Thread.sleep(1000);

        WebElement sumit = wait.until(ExpectedConditions.presenceOfElementLocated(sumitNevaseOption));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", sumit);
        System.out.println("➡ Selected Sumit Nevase");
        Thread.sleep(1000);
    }

    public void openManageCategories() throws InterruptedException {
        WebElement manageCategories = wait.until(ExpectedConditions.presenceOfElementLocated(manageCategoriesBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", manageCategories);
        System.out.println("➡ Clicked Manage Categories");
        Thread.sleep(3000);

        // ✅ Wait for page stability before clicking back
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button")));

        WebElement back = wait.until(ExpectedConditions.elementToBeClickable(backButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", back);
        System.out.println("➡ Clicked Back Button");
        Thread.sleep(2000);
    }

    public void clickAddExpense() throws InterruptedException {
        WebElement addExpense = wait.until(ExpectedConditions.presenceOfElementLocated(addExpenseBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addExpense);
        System.out.println("➡ Clicked Add Expense");
        Thread.sleep(2000);
    }

    public void fillExpenseForm() throws InterruptedException {

        Random random = new Random();

        // ✅ Project/Task
        WebElement projectTask = wait.until(ExpectedConditions.presenceOfElementLocated(projectTaskField));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", projectTask);
        System.out.println("➡ Clicked Project/Task Dropdown");
        Thread.sleep(1000);

        WebElement sumitProject = wait.until(ExpectedConditions.presenceOfElementLocated(sumitNevaseProjectOption));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", sumitProject);
        System.out.println("➡ Selected Sumit Nevase from Project/Task");
        Thread.sleep(1000);

        // ✅ Category Dropdown
        WebElement category = wait.until(ExpectedConditions.presenceOfElementLocated(categoryDropdown));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", category);
        System.out.println("➡ Clicked Category Dropdown");
        Thread.sleep(1000);

        // ✅ Random Category
        String selectedCategory = categories.get(random.nextInt(categories.size()));
        System.out.println("➡ Selected Category: " + selectedCategory);

        // ✅ Search
        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(categorySearch));
        search.click();
        search.clear();
        search.sendKeys(selectedCategory);
        Thread.sleep(1000);

        // ✅ Click category option directly via JS
        WebElement categoryOption = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//button[contains(@class,'hover:bg-gray-100') and normalize-space()='" + selectedCategory + "']")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", categoryOption);
        System.out.println("➡ Category Selected via click: " + selectedCategory);
        Thread.sleep(1000);

        // ✅ CONDITIONAL FIELD HANDLING
        if (selectedCategory.equalsIgnoreCase("Mileage")) {
            System.out.println("➡ Mileage selected → entering miles");

            By milesField = By.xpath("//input[contains(@placeholder,'mile') or contains(@placeholder,'distance')]");
            WebElement miles = wait.until(ExpectedConditions.visibilityOfElementLocated(milesField));

            int randomMiles = random.nextInt(50) + 1;
            miles.clear();
            miles.sendKeys(String.valueOf(randomMiles));
            System.out.println("➡ Entered Miles: " + randomMiles);

        } else {
            System.out.println("➡ Non-Mileage → entering amount");

            int randomAmount = random.nextInt(9) + 1;
            WebElement amount = wait.until(ExpectedConditions.visibilityOfElementLocated(amountField));
            amount.clear();
            amount.sendKeys(String.valueOf(randomAmount));
            System.out.println("➡ Entered Amount: " + randomAmount);
        }

        // ✅ Description
        WebElement description = wait.until(ExpectedConditions.visibilityOfElementLocated(descriptionField));
        description.clear();
        description.sendKeys("This is for " + selectedCategory);
        System.out.println("➡ Entered Description");

        // ✅ Billable Toggle
        WebElement billable = wait.until(ExpectedConditions.elementToBeClickable(billableToggle));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", billable);
        System.out.println("➡ Enabled Billable");
        Thread.sleep(500);
    }

    public void clickCreate() throws InterruptedException {
        WebElement create = wait.until(ExpectedConditions.presenceOfElementLocated(createButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", create);
        System.out.println("➡ Clicked Create");
        Thread.sleep(2000);
    }

    public void clickSubmitForApproval() throws InterruptedException {
        WebElement submitApproval = wait.until(ExpectedConditions.presenceOfElementLocated(submitForApprovalBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitApproval);
        System.out.println("➡ Clicked Submit for Approval");
        Thread.sleep(2000);
    }

    public void clickFinalSubmit() throws InterruptedException {
        WebElement submit = wait.until(ExpectedConditions.presenceOfElementLocated(submitFinalBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submit);
        System.out.println("➡ Clicked Final Submit");
        Thread.sleep(2000);
    }
}