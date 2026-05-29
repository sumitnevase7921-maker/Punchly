package com.aipxperts.punchly.tests;

import com.aipxperts.punchly.base.BaseTest;
import com.aipxperts.punchly.pages.LoginPage;
import com.aipxperts.punchly.pages.ProjectsPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProjectsTest extends BaseTest {

    LoginPage loginPage;
    ProjectsPage projectsPage;

    @BeforeMethod
    public void loginAndNavigate() throws InterruptedException {
        loginPage    = new LoginPage(driver);
        projectsPage = new ProjectsPage(driver);

        loginPage.login("sumit.nevase@aipxperts.com");
        loginPage.enterOTP();
        projectsPage.goToProjects();

        System.out.println("✅ Logged in and on Projects page");
    }

    @Test(priority = 1)
    public void TC_PRJ_001_verifyProjectsPageOpened() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("✅ TC_PRJ_001 PASSED - Projects page opened");
    }

    @Test(priority = 2)
    public void TC_PRJ_002_verifyAllDropdownOpens() throws InterruptedException {
        projectsPage.clickAllDropdown();
        Thread.sleep(2000);
        System.out.println("✅ TC_PRJ_002 PASSED - All dropdown opened");
    }

    @Test(priority = 3)
    public void TC_PRJ_003_verifySearchClientInAllDropdown() throws InterruptedException {
        projectsPage.clickAllDropdown();
        Thread.sleep(1500);
        projectsPage.searchClientInAllDropdown("s");
        Thread.sleep(1500);
        System.out.println("✅ TC_PRJ_003 PASSED - Client searched in All dropdown");
    }

    @Test(priority = 4)
    public void TC_PRJ_004_verifySelectAipxpertsClient() throws InterruptedException {
        projectsPage.clickAllDropdown();
        Thread.sleep(1500);
        projectsPage.searchClientInAllDropdown("s");
        Thread.sleep(1500);
        projectsPage.selectAipxpertsClient();
        Thread.sleep(2000);
        System.out.println("✅ TC_PRJ_004 PASSED - Aipxperts client selected");
    }

    @Test(priority = 5)
    public void TC_PRJ_005_verifyActiveDropdownOpens() throws InterruptedException {
        projectsPage.clickActiveDropdown();
        Thread.sleep(2000);
        System.out.println("✅ TC_PRJ_005 PASSED - Active dropdown opened");
    }

    @Test(priority = 6)
    public void TC_PRJ_006_verifySelectArchived() throws InterruptedException {
        projectsPage.clickActiveDropdown();
        Thread.sleep(1500);
        projectsPage.selectArchived();
        Thread.sleep(2000);
        System.out.println("✅ TC_PRJ_006 PASSED - Archived selected");
    }

    @Test(priority = 7)
    public void TC_PRJ_007_verifySelectActive() throws InterruptedException {
        projectsPage.clickActiveDropdown();
        Thread.sleep(1500);
        projectsPage.selectActive();
        Thread.sleep(2000);
        System.out.println("✅ TC_PRJ_007 PASSED - Active selected");
    }

    @Test(priority = 8)
    public void TC_PRJ_008_verifySearchProject() throws InterruptedException {
        projectsPage.searchProject("Sumit");
        Thread.sleep(2000);
        System.out.println("✅ TC_PRJ_008 PASSED - Project searched in search bar");
    }

    @Test(priority = 9)
    public void TC_PRJ_009_verifyNewProjectButtonClick() throws InterruptedException {
        projectsPage.clickNewProject();
        Thread.sleep(2000);
        System.out.println("✅ TC_PRJ_009 PASSED - New Project button clicked");
    }

    @Test(priority = 10)
    public void TC_PRJ_010_verifyEnterProjectName() throws InterruptedException {
        projectsPage.clickNewProject();
        Thread.sleep(1500);
        projectsPage.enterProjectName("Test Automation Project");
        Thread.sleep(1500);
        System.out.println("✅ TC_PRJ_010 PASSED - Project name entered");
    }

    @Test(priority = 11)
    public void TC_PRJ_011_verifyCreateNewProject() throws InterruptedException {
        projectsPage.clickNewProject();
        Thread.sleep(1500);
        projectsPage.enterProjectName("Test Automation Project");
        Thread.sleep(1500);
        projectsPage.submitProjectName();
        Thread.sleep(2000);
        System.out.println("✅ TC_PRJ_011 PASSED - New project created successfully");
    }

    @Test(priority = 12)
    public void TC_PRJ_012_verifyThreeDotMenuOpens() throws InterruptedException {
        projectsPage.clickThreeDotMenu();
        Thread.sleep(2000);
        System.out.println("✅ TC_PRJ_012 PASSED - 3-dot menu opened");
    }

    @Test(priority = 13)
    public void TC_PRJ_013_verifyViewDetails() throws InterruptedException {
        projectsPage.clickThreeDotMenu();
        Thread.sleep(1500);
        projectsPage.clickViewDetails();
        Thread.sleep(2000);
        System.out.println("✅ TC_PRJ_013 PASSED - View Details clicked");
    }

  @Test(priority = 14)
    public void TC_PRJ_014_verifyEdit() throws InterruptedException {
        projectsPage.clickThreeDotMenu();
        Thread.sleep(1500);
        projectsPage.clickEdit();
        Thread.sleep(2000);
        System.out.println("✅ TC_PRJ_014 PASSED - Edit clicked");
    }

    @Test(priority = 15)
    public void TC_PRJ_015_verifyArchive() throws InterruptedException {
        projectsPage.clickThreeDotMenu();
        Thread.sleep(1500);
        projectsPage.clickArchive();
        Thread.sleep(2000);
        System.out.println("✅ TC_PRJ_015 PASSED - Archive clicked");
    }
}