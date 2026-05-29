package com.aipxperts.punchly.tests;

import com.aipxperts.punchly.base.BaseTest;
import com.aipxperts.punchly.pages.LoginPage;
import com.aipxperts.punchly.pages.ExpensesPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExpensesTest extends BaseTest {

    LoginPage loginPage;
    ExpensesPage expensesPage;

    // ─── Login Before Each Test ───────────────────────────────────────────────────

    @BeforeMethod
    public void loginAndNavigate() throws InterruptedException {
        loginPage    = new LoginPage(driver);
        expensesPage = new ExpensesPage(driver);

        // Step 1: Enter email and click Continue
        loginPage.login("sumit.nevase@aipxperts.com");

        // Step 2: Enter OTP (000000)
        loginPage.enterOTP();

        System.out.println("✅ Logged in successfully");
    }

    // ─── TC_EXP_001 : Open Expenses Page ─────────────────────────────────────────

    @Test(priority = 1)
    public void TC_EXP_001_verifyOpenExpenses() throws InterruptedException {
        expensesPage.openExpenses();
        Thread.sleep(2000);
        System.out.println("✅ TC_EXP_001 PASSED - Expenses page opened");
    }

    // ─── TC_EXP_002 : Open Manage Categories and Go Back ─────────────────────────

    @Test(priority = 2)
    public void TC_EXP_002_verifyManageCategories() throws InterruptedException {
        expensesPage.openExpenses();
        Thread.sleep(1500);
        expensesPage.openManageCategories();
        Thread.sleep(1000);
        System.out.println("✅ TC_EXP_002 PASSED - Manage Categories opened and back clicked");
    }

    // ─── TC_EXP_003 : Click Add Expense Button ───────────────────────────────────

    @Test(priority = 3)
    public void TC_EXP_003_verifyAddExpenseButton() throws InterruptedException {
        expensesPage.openExpenses();
        Thread.sleep(1500);
        expensesPage.clickAddExpense();
        Thread.sleep(1000);
        System.out.println("✅ TC_EXP_003 PASSED - Add Expense button clicked");
    }

    // ─── TC_EXP_004 : Fill Expense Form ──────────────────────────────────────────

    @Test(priority = 4)
    public void TC_EXP_004_verifyFillExpenseForm() throws InterruptedException {
        expensesPage.openExpenses();
        Thread.sleep(1500);
        expensesPage.clickAddExpense();
        Thread.sleep(1000);
        expensesPage.fillExpenseForm();
        Thread.sleep(1000);
        System.out.println("✅ TC_EXP_004 PASSED - Expense form filled");
    }

    // ─── TC_EXP_005 : Click Create Button ────────────────────────────────────────

    @Test(priority = 5)
    public void TC_EXP_005_verifyCreateExpense() throws InterruptedException {
        expensesPage.openExpenses();
        Thread.sleep(1500);
        expensesPage.clickAddExpense();
        Thread.sleep(1000);
        expensesPage.fillExpenseForm();
        Thread.sleep(1000);
        expensesPage.clickCreate();
        Thread.sleep(2000);
        System.out.println("✅ TC_EXP_005 PASSED - Create button clicked");
    }

    // ─── TC_EXP_006 : Click Submit for Approval ──────────────────────────────────

    @Test(priority = 6)
    public void TC_EXP_006_verifySubmitForApproval() throws InterruptedException {
        expensesPage.openExpenses();
        Thread.sleep(1500);
        expensesPage.clickAddExpense();
        Thread.sleep(1000);
        expensesPage.fillExpenseForm();
        Thread.sleep(1000);
        expensesPage.clickCreate();
        Thread.sleep(1500);
        expensesPage.clickSubmitForApproval();
        Thread.sleep(2000);
        System.out.println("✅ TC_EXP_006 PASSED - Submit for Approval clicked");
    }

    // ─── TC_EXP_007 : Full Flow – Final Submit ────────────────────────────────────

    @Test(priority = 7)
    public void TC_EXP_007_verifyFinalSubmit() throws InterruptedException {
        expensesPage.openExpenses();
        Thread.sleep(1500);
        expensesPage.clickAddExpense();
        Thread.sleep(1000);
        expensesPage.fillExpenseForm();
        Thread.sleep(1000);
        expensesPage.clickCreate();
        Thread.sleep(1500);
        expensesPage.clickSubmitForApproval();
        Thread.sleep(1500);
        expensesPage.clickFinalSubmit();
        Thread.sleep(2000);
        System.out.println("✅ TC_EXP_007 PASSED - Final Submit clicked, full expense flow done");
    }
}