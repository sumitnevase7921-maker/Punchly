package com.aipxperts.punchly.tests;

import com.aipxperts.punchly.base.BaseTest;
import com.aipxperts.punchly.pages.LoginPage;
import com.aipxperts.punchly.pages.TasksPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TasksTest extends BaseTest {

    LoginPage loginPage;
    TasksPage tasksPage;

    // ─── Login Before Each Test ───────────────────────────────────────────────────

    @BeforeMethod
    public void loginAndNavigate() throws InterruptedException {
        loginPage = new LoginPage(driver);
        tasksPage = new TasksPage(driver);

        loginPage.login("sumit.nevase@aipxperts.com");
        loginPage.enterOTP();
        tasksPage.goToTasks();

        System.out.println("✅ Logged in and on Tasks page");
    }

    // ─── TC_TSK_001 : Open Tasks Page ────────────────────────────────────────────

    @Test(priority = 1)
    public void TC_TSK_001_verifyTasksPageOpened() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("✅ TC_TSK_001 PASSED - Tasks page opened");
    }

    // ─── TC_TSK_002 : Search by Name ─────────────────────────────────────────────

    @Test(priority = 2)
    public void TC_TSK_002_verifySearchByName() throws InterruptedException {
        tasksPage.searchByName("test");
        Thread.sleep(2000);
        System.out.println("✅ TC_TSK_002 PASSED - Search by name done");
    }

    // ─── TC_TSK_003 : Click All Projects Dropdown ─────────────────────────────────

    @Test(priority = 3)
    public void TC_TSK_003_verifyAllProjectsDropdown() throws InterruptedException {
        tasksPage.clickAllProjectsDropdown();
        Thread.sleep(2000);
        System.out.println("✅ TC_TSK_003 PASSED - All Projects dropdown opened");
    }

    // ─── TC_TSK_004 : Search in All Projects Dropdown ────────────────────────────

    @Test(priority = 4)
    public void TC_TSK_004_verifySearchInAllProjectsDropdown() throws InterruptedException {
        tasksPage.clickAllProjectsDropdown();
        Thread.sleep(1500);
        tasksPage.searchInAllProjectsDropdown("S");
        Thread.sleep(1500);
        System.out.println("✅ TC_TSK_004 PASSED - Searched in All Projects dropdown");
    }

    // ─── TC_TSK_005 : Select Sumit Nevase Project ────────────────────────────────

    @Test(priority = 5)
    public void TC_TSK_005_verifySelectSumitNevaseProject() throws InterruptedException {
        tasksPage.clickAllProjectsDropdown();
        Thread.sleep(1500);
        tasksPage.selectSumitNeveseProject();
        Thread.sleep(2000);
        System.out.println("✅ TC_TSK_005 PASSED - Sumit Nevase project selected");
    }

    // ─── TC_TSK_006 : Click Active Dropdown ──────────────────────────────────────

    @Test(priority = 6)
    public void TC_TSK_006_verifyActiveDropdown() throws InterruptedException {
        tasksPage.clickActiveDropdown();
        Thread.sleep(2000);
        System.out.println("✅ TC_TSK_006 PASSED - Active dropdown opened");
    }

    // ─── TC_TSK_007 : Select Archived ────────────────────────────────────────────

    @Test(priority = 7)
    public void TC_TSK_007_verifySelectArchived() throws InterruptedException {
        tasksPage.clickActiveDropdown();
        Thread.sleep(1500);
        tasksPage.selectArchived();
        Thread.sleep(2000);
        System.out.println("✅ TC_TSK_007 PASSED - Archived selected");
    }

    // ─── TC_TSK_008 : Select Active ──────────────────────────────────────────────

    @Test(priority = 8)
    public void TC_TSK_008_verifySelectActive() throws InterruptedException {
        tasksPage.clickActiveDropdown();
        Thread.sleep(1500);
        tasksPage.selectActive();
        Thread.sleep(2000);
        System.out.println("✅ TC_TSK_008 PASSED - Active selected");
    }

    // ─── TC_TSK_009 : Click Create Task ──────────────────────────────────────────

    @Test(priority = 9)
    public void TC_TSK_009_verifyClickCreateTask() throws InterruptedException {
        tasksPage.clickCreateTask();
        Thread.sleep(2000);
        System.out.println("✅ TC_TSK_009 PASSED - Create task clicked");
    }

    // ─── TC_TSK_010 : Enter Task Name ────────────────────────────────────────────

    @Test(priority = 10)
    public void TC_TSK_010_verifyEnterTaskName() throws InterruptedException {
        tasksPage.clickCreateTask();
        Thread.sleep(1500);
        tasksPage.enterTaskName("Automation Test Task");
        Thread.sleep(1500);
        System.out.println("✅ TC_TSK_010 PASSED - Task name entered");
    }

    // ─── TC_TSK_011 : Create New Task Full Flow ───────────────────────────────────

    @Test(priority = 11)
    public void TC_TSK_011_verifyCreateNewTask() throws InterruptedException {
        tasksPage.clickCreateTask();
        Thread.sleep(1500);
        tasksPage.enterTaskName("Automation Test Task");
        Thread.sleep(1500);
        tasksPage.submitTaskName();
        Thread.sleep(2000);
        System.out.println("✅ TC_TSK_011 PASSED - New task created");
    }

    // ─── TC_TSK_012 : Click Checkbox on First Row ────────────────────────────────

    @Test(priority = 12)
    public void TC_TSK_012_verifyCheckboxClick() throws InterruptedException {
        tasksPage.clickFirstRowCheckbox();
        Thread.sleep(2000);
        System.out.println("✅ TC_TSK_012 PASSED - Checkbox clicked, bulk bar appeared");
    }

    // ─── TC_TSK_013 : Click Assign Project ───────────────────────────────────────

    @Test(priority = 13)
    public void TC_TSK_013_verifyAssignProject() throws InterruptedException {
        tasksPage.clickFirstRowCheckbox();
        Thread.sleep(1500);
        tasksPage.clickAssignProject();
        Thread.sleep(2000);
        System.out.println("✅ TC_TSK_013 PASSED - Assign Project modal opened");
    }

    // ─── TC_TSK_014 : Select Project in Assign Project Modal ─────────────────────

    @Test(priority = 14)
    public void TC_TSK_014_verifySelectProjectInModal() throws InterruptedException {
        tasksPage.clickFirstRowCheckbox();
        Thread.sleep(1500);
        tasksPage.clickAssignProject();
        Thread.sleep(1500);
        tasksPage.selectProjectInModal("S");
        Thread.sleep(2000);
        System.out.println("✅ TC_TSK_014 PASSED - Project selected in modal");
    }

    // ─── TC_TSK_015 : Click Assign Assignee ──────────────────────────────────────

    @Test(priority = 15)
    public void TC_TSK_015_verifyAssignAssignee() throws InterruptedException {
        tasksPage.clickFirstRowCheckbox();
        Thread.sleep(1500);
        tasksPage.clickAssignAssignee();
        Thread.sleep(2000);
        System.out.println("✅ TC_TSK_015 PASSED - Assign Assignee modal opened");
    }

    // ─── TC_TSK_016 : Select Assignee in Modal ───────────────────────────────────

    @Test(priority = 16)
    public void TC_TSK_016_verifySelectAssigneeInModal() throws InterruptedException {
        tasksPage.clickFirstRowCheckbox();
        Thread.sleep(1500);
        tasksPage.clickAssignAssignee();
        Thread.sleep(1500);
        tasksPage.selectAssigneeInModal();
        Thread.sleep(2000);
        System.out.println("✅ TC_TSK_016 PASSED - Assignee selected in modal");
    }

    // ─── TC_TSK_017 : Click Archive from Bulk Bar ────────────────────────────────

 @Test(priority = 17)
    public void TC_TSK_017_verifyArchiveBulk() throws InterruptedException {
        tasksPage.clickFirstRowCheckbox();
        Thread.sleep(1500);
        tasksPage.clickArchiveBulk();
        Thread.sleep(2000);
        System.out.println("✅ TC_TSK_017 PASSED - Archive modal opened from bulk bar");
    }

    // ─── TC_TSK_018 : Cancel Archive Modal ───────────────────────────────────────

   	@Test(priority = 18)
    public void TC_TSK_018_verifyCancelArchive() throws InterruptedException {
        tasksPage.clickFirstRowCheckbox();
        Thread.sleep(1500);
        tasksPage.clickArchiveBulk();
        Thread.sleep(1500);
        tasksPage.cancelArchive();
        Thread.sleep(2000);
        System.out.println("✅ TC_TSK_018 PASSED - Archive cancelled");
    }

    // ─── TC_TSK_019 : Click Clear Button ─────────────────────────────────────────

    @Test(priority = 19)
    public void TC_TSK_019_verifyClearButton() throws InterruptedException {
        tasksPage.clickFirstRowCheckbox();
        Thread.sleep(1500);
        tasksPage.clickClear();
        Thread.sleep(2000);
        System.out.println("✅ TC_TSK_019 PASSED - Clear button clicked");
    }

    // ─── TC_TSK_020 : Open 3-Dot Menu ────────────────────────────────────────────

    @Test(priority = 20)
    public void TC_TSK_020_verifyThreeDotMenu() throws InterruptedException {
        tasksPage.clickThreeDotMenu();
        Thread.sleep(2000);
        System.out.println("✅ TC_TSK_020 PASSED - 3-dot menu opened");
    }

    // ─── TC_TSK_021 : Click Edit from 3-Dot Menu ─────────────────────────────────

    @Test(priority = 21)
    public void TC_TSK_021_verifyEditFromMenu() throws InterruptedException {
        tasksPage.clickThreeDotMenu();
        Thread.sleep(1500);
        tasksPage.clickEdit();
        Thread.sleep(2000);
        System.out.println("✅ TC_TSK_021 PASSED - Edit clicked from 3-dot menu");
    }

    // ─── TC_TSK_022 : Click Delete from 3-Dot Menu ───────────────────────────────

    @Test(priority = 22)
    public void TC_TSK_022_verifyDeleteFromMenu() throws InterruptedException {
        tasksPage.clickThreeDotMenu();
        Thread.sleep(1500);
        tasksPage.clickDelete();
        Thread.sleep(2000);
        System.out.println("✅ TC_TSK_022 PASSED - Delete clicked from 3-dot menu");
    }

    // ─── TC_TSK_023 : Click Archive from 3-Dot Menu ──────────────────────────────

    @Test(priority = 23)
    public void TC_TSK_023_verifyArchiveFromMenu() throws InterruptedException {
        tasksPage.clickThreeDotMenu();
        Thread.sleep(1500);
        tasksPage.clickArchiveFromMenu();
        Thread.sleep(1500);
        tasksPage.confirmArchive();
        Thread.sleep(2000);
        System.out.println("✅ TC_TSK_023 PASSED - Archive confirmed from 3-dot menu");
    }
}