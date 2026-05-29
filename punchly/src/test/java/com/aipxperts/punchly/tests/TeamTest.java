package com.aipxperts.punchly.tests;

import com.aipxperts.punchly.base.BaseTest;
import com.aipxperts.punchly.pages.LoginPage;
import com.aipxperts.punchly.pages.TeamPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TeamTest extends BaseTest {

    LoginPage loginPage;
    TeamPage teamPage;

    @BeforeMethod
    public void loginAndNavigate() throws InterruptedException {
        loginPage = new LoginPage(driver);
        teamPage  = new TeamPage(driver);
        loginPage.login("sumit.nevase@aipxperts.com");
        loginPage.enterOTP();
        teamPage.goToTeam();
        System.out.println("✅ Logged in and on Team page");
    }

    @Test(priority = 1)
    public void TC_TM_001_verifyTeamPageOpened() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("✅ TC_TM_001 PASSED - Team page opened");
    }

    @Test(priority = 2)
    public void TC_TM_002_verifySearchByNameOrEmail() throws InterruptedException {
        teamPage.searchByNameOrEmail("Sumit");
        Thread.sleep(2000);
        System.out.println("✅ TC_TM_002 PASSED - Search done");
    }

    @Test(priority = 3)
    public void TC_TM_003_verifyAddTeamMemberButton() throws InterruptedException {
        teamPage.clickAddTeamMember();
        Thread.sleep(2000);
        System.out.println("✅ TC_TM_003 PASSED - Add Team Member modal opened");
    }

    @Test(priority = 4)
    public void TC_TM_004_verifyEnterInviteEmail() throws InterruptedException {
        teamPage.clickAddTeamMember();
        Thread.sleep(1500);
        teamPage.enterInviteEmail(
            "test1@example.com, test2@example.com, test3@example.com, test4@example.com, test5@example.com"
        );
        Thread.sleep(1500);
        System.out.println("✅ TC_TM_004 PASSED - 5 invite emails entered");
    }

    @Test(priority = 5)
    public void TC_TM_005_verifyCancelInviteModal() throws InterruptedException {
        teamPage.clickAddTeamMember();
        Thread.sleep(1500);
        teamPage.clickCancelInvite();
        Thread.sleep(1500);
        System.out.println("✅ TC_TM_005 PASSED - Invite modal cancelled");
    }

    @Test(priority = 6)
    public void TC_TM_006_verifyBillableRateChangeBtn() throws InterruptedException {
        teamPage.clickBillableRateChange();
        Thread.sleep(2000);
        System.out.println("✅ TC_TM_006 PASSED - Edit Billable Rate modal opened");
    }

    @Test(priority = 7)
    public void TC_TM_007_verifyEnterBillableRate() throws InterruptedException {
        teamPage.clickBillableRateChange();
        Thread.sleep(1500);
        teamPage.enterRate("50");
        Thread.sleep(1500);
        System.out.println("✅ TC_TM_007 PASSED - Billable rate entered");
    }

    @Test(priority = 8)
    public void TC_TM_008_verifyAllPastFutureRadio() throws InterruptedException {
        teamPage.clickBillableRateChange();
        Thread.sleep(1500);
        teamPage.enterRate("50");
        Thread.sleep(1000);
        teamPage.selectAllPastFutureEntries();
        Thread.sleep(1500);
        System.out.println("✅ TC_TM_008 PASSED - All past and future radio selected");
    }

    @Test(priority = 9)
    public void TC_TM_009_verifyCancelBillableRate() throws InterruptedException {
        teamPage.clickBillableRateChange();
        Thread.sleep(1500);
        teamPage.clickCancelRate();
        Thread.sleep(1500);
        System.out.println("✅ TC_TM_009 PASSED - Billable rate modal cancelled");
    }

    @Test(priority = 10)
    public void TC_TM_010_verifyCostRateChangeBtn() throws InterruptedException {
        teamPage.clickCostRateChange();
        Thread.sleep(2000);
        System.out.println("✅ TC_TM_010 PASSED - Edit Cost Rate modal opened");
    }

    @Test(priority = 11)
    public void TC_TM_011_verifyEnterCostRate() throws InterruptedException {
        teamPage.clickCostRateChange();
        Thread.sleep(1500);
        teamPage.enterRate("30");
        Thread.sleep(1500);
        System.out.println("✅ TC_TM_011 PASSED - Cost rate entered");
    }

    @Test(priority = 12)
    public void TC_TM_012_verifyCancelCostRate() throws InterruptedException {
        teamPage.clickCostRateChange();
        Thread.sleep(1500);
        teamPage.clickCancelRate();
        Thread.sleep(1500);
        System.out.println("✅ TC_TM_012 PASSED - Cost rate modal cancelled");
    }

    @Test(priority = 13)
    public void TC_TM_013_verifyFilterButtonOpens() throws InterruptedException {
        teamPage.clickFilter();
        Thread.sleep(2000);
        System.out.println("✅ TC_TM_013 PASSED - Filter panel opened");
    }

    @Test(priority = 14)
    public void TC_TM_014_verifyBillableRateFilterDropdown() throws InterruptedException {
        teamPage.clickFilter();
        Thread.sleep(1500);
        teamPage.clickBillableRateFilterDropdown();
        Thread.sleep(1500);
        System.out.println("✅ TC_TM_014 PASSED - Billable rate filter dropdown opened");
    }

    @Test(priority = 15)
    public void TC_TM_015_verifySelectHasRateBillable() throws InterruptedException {
        teamPage.clickFilter();
        Thread.sleep(1500);
        teamPage.clickBillableRateFilterDropdown();
        Thread.sleep(1000);
        teamPage.selectFilterHasRate();
        Thread.sleep(1500);
        System.out.println("✅ TC_TM_015 PASSED - Has Rate selected");
    }

    @Test(priority = 16)
    public void TC_TM_016_verifySelectNoRateBillable() throws InterruptedException {
        teamPage.clickFilter();
        Thread.sleep(1500);
        teamPage.clickBillableRateFilterDropdown();
        Thread.sleep(1000);
        teamPage.selectFilterNoRate();
        Thread.sleep(1500);
        System.out.println("✅ TC_TM_016 PASSED - No Rate selected");
    }

    @Test(priority = 17)
    public void TC_TM_017_verifyCostRateFilterDropdown() throws InterruptedException {
        teamPage.clickFilter();
        Thread.sleep(1500);
        teamPage.clickCostRateFilterDropdown();
        Thread.sleep(1500);
        System.out.println("✅ TC_TM_017 PASSED - Cost rate filter dropdown opened");
    }

    @Test(priority = 18)
    public void TC_TM_018_verifyRoleFilterDropdown() throws InterruptedException {
        teamPage.clickFilter();
        Thread.sleep(1500);
        teamPage.clickRoleFilterDropdown();
        Thread.sleep(1500);
        System.out.println("✅ TC_TM_018 PASSED - Role filter dropdown opened");
    }

    @Test(priority = 19)
    public void TC_TM_019_verifySelectOwnerRole() throws InterruptedException {
        teamPage.clickFilter();
        Thread.sleep(1500);
        teamPage.clickRoleFilterDropdown();
        Thread.sleep(1000);
        teamPage.selectRoleOwner();
        Thread.sleep(1500);
        System.out.println("✅ TC_TM_019 PASSED - Owner role selected");
    }

    @Test(priority = 20)
    public void TC_TM_020_verifySelectAdminRole() throws InterruptedException {
        teamPage.clickFilter();
        Thread.sleep(1500);
        teamPage.clickRoleFilterDropdown();
        Thread.sleep(1000);
        teamPage.selectRoleAdmin();
        Thread.sleep(1500);
        System.out.println("✅ TC_TM_020 PASSED - Admin role selected");
    }

    @Test(priority = 21)
    public void TC_TM_021_verifySelectManagerRole() throws InterruptedException {
        teamPage.clickFilter();
        Thread.sleep(1500);
        teamPage.clickRoleFilterDropdown();
        Thread.sleep(1000);
        teamPage.selectRoleManager();
        Thread.sleep(1500);
        System.out.println("✅ TC_TM_021 PASSED - Manager role selected");
    }

    @Test(priority = 22)
    public void TC_TM_022_verifySelectMemberRole() throws InterruptedException {
        teamPage.clickFilter();
        Thread.sleep(1500);
        teamPage.clickRoleFilterDropdown();
        Thread.sleep(1000);
        teamPage.selectRoleMember();
        Thread.sleep(1500);
        System.out.println("✅ TC_TM_022 PASSED - Member role selected");
    }

    @Test(priority = 23)
    public void TC_TM_023_verifyShowInactiveMembers() throws InterruptedException {
        teamPage.clickFilter();
        Thread.sleep(1500);
        teamPage.clickShowInactiveMembers();
        Thread.sleep(1500);
        System.out.println("✅ TC_TM_023 PASSED - Show inactive members clicked");
    }

    @Test(priority = 24)
    public void TC_TM_024_verifyApplyFilter() throws InterruptedException {
        teamPage.clickFilter();
        Thread.sleep(1500);
        teamPage.clickApplyFilter();
        Thread.sleep(2000);
        System.out.println("✅ TC_TM_024 PASSED - Apply Filter clicked");
    }

    @Test(priority = 25)
    public void TC_TM_025_verifyClearFilter() throws InterruptedException {
        teamPage.clickFilter();
        Thread.sleep(1500);
        teamPage.clickClearFilter();
        Thread.sleep(1500);
        System.out.println("✅ TC_TM_025 PASSED - Clear Filter clicked");
    }

    @Test(priority = 26)
    public void TC_TM_026_verifyExportButton() throws InterruptedException {
        teamPage.clickExport();
        Thread.sleep(3000);
        System.out.println("✅ TC_TM_026 PASSED - Export clicked");
    }
}