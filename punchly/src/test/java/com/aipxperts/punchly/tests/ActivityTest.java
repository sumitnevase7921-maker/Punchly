package com.aipxperts.punchly.tests;

import com.aipxperts.punchly.base.BaseTest;
import com.aipxperts.punchly.pages.ActivityPage;
import com.aipxperts.punchly.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ActivityTest extends BaseTest {

    ActivityPage activityPage;

    @BeforeMethod
    public void loginAndGoToActivity() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("sumit.nevase@aipxperts.com");
        loginPage.enterOTP();
        activityPage = new ActivityPage(driver);
        activityPage.navigateToActivityPage();
    }

    // TC_ACT_001 - Verify Activity page loads
    @Test(priority = 1)
    public void TC_ACT_001_verifyActivityPageLoaded() {
        Assert.assertTrue(activityPage.isActivityPageDisplayed(),
            "FAIL: My Activity page title not visible");
        System.out.println("PASS: TC_ACT_001 - Activity page loaded");
    }

    // TC_ACT_002 - Verify Activity Monitoring Disabled banner
    @Test(priority = 2)
    public void TC_ACT_002_verifyMonitoringDisabledBanner() {
        Assert.assertTrue(activityPage.isMonitoringDisabledBannerVisible(),
            "FAIL: Activity Monitoring Disabled banner not visible");
        System.out.println("PASS: TC_ACT_002 - Monitoring Disabled banner visible");
    }

    // TC_ACT_003 - Verify Screenshots tab is clickable
    @Test(priority = 3)
    public void TC_ACT_003_verifyScreenshotsTabClick() {
        activityPage.clickScreenshotsTab();
        System.out.println("PASS: TC_ACT_003 - Screenshots tab clicked");
    }

    // TC_ACT_004 - Verify no screenshots empty state message
    @Test(priority = 4)
    public void TC_ACT_004_verifyNoScreenshotsMessage() {
        activityPage.clickScreenshotsTab();
        Assert.assertTrue(activityPage.isNoScreenshotsMsgVisible(),
            "FAIL: No screenshots message not visible");
        System.out.println("PASS: TC_ACT_004 - No screenshots empty state shown");
    }

    // TC_ACT_005 - Verify calendar opens on Screenshots tab
    @Test(priority = 5)
    public void TC_ACT_005_verifyCalendarOpens() {
        activityPage.clickScreenshotsTab();
        activityPage.openDatePicker();
        System.out.println("PASS: TC_ACT_005 - Calendar opened");
    }

    // TC_ACT_006 - Verify selecting a date from calendar
    @Test(priority = 6)
    public void TC_ACT_006_verifySelectDateFromCalendar() {
        activityPage.clickScreenshotsTab();
        activityPage.openDatePicker();
        activityPage.selectDayFromCalendar("15");
        System.out.println("PASS: TC_ACT_006 - Date 15 selected from calendar");
    }

    // TC_ACT_007 - Verify calendar navigates to previous month
    @Test(priority = 7)
    public void TC_ACT_007_verifyCalendarPreviousMonth() {
        activityPage.clickScreenshotsTab();
        activityPage.openDatePicker();
        String before = activityPage.getCalendarMonthYear();
        activityPage.clickPreviousMonth();
        String after = activityPage.getCalendarMonthYear();
        Assert.assertNotEquals(after, before, "FAIL: Calendar did not go to previous month");
        System.out.println("PASS: TC_ACT_007 - Calendar went to: " + after);
    }

    // TC_ACT_008 - Verify calendar navigates to next month
    @Test(priority = 8)
    public void TC_ACT_008_verifyCalendarNextMonth() {
        activityPage.clickScreenshotsTab();
        activityPage.openDatePicker();
        String before = activityPage.getCalendarMonthYear();
        activityPage.clickNextMonth();
        String after = activityPage.getCalendarMonthYear();
        Assert.assertNotEquals(after, before, "FAIL: Calendar did not go to next month");
        System.out.println("PASS: TC_ACT_008 - Calendar went to: " + after);
    }

    // TC_ACT_009 - Verify Location tab is clickable
    @Test(priority = 9)
    public void TC_ACT_009_verifyLocationTabClick() {
        activityPage.clickLocationTab();
        System.out.println("PASS: TC_ACT_009 - Location tab clicked");
    }
    
    // TC_ACT_010 - Verify no location empty state message
    @Test(priority = 10)
    public void TC_ACT_010_verifyNoLocationMessage() {
        activityPage.clickLocationTab();
        Assert.assertTrue(activityPage.isNoLocationMsgVisible(),
            "FAIL: No location message not visible");
        System.out.println("PASS: TC_ACT_010 - No location empty state shown");
    }

    // TC_ACT_011 - Verify date range dropdown opens on Location tab
    @Test(priority = 11)
    public void TC_ACT_011_verifyDateRangeDropdownOpens() {
        activityPage.clickLocationTab();
        activityPage.openDateRangeDropdown();
        System.out.println("PASS: TC_ACT_011 - Date range dropdown opened");
    }

    // TC_ACT_012 - Verify selecting Today from date range
    @Test(priority = 12)
    public void TC_ACT_012_verifySelectToday() {
        activityPage.clickLocationTab();
        activityPage.openDateRangeDropdown();
        activityPage.selectToday();
        System.out.println("PASS: TC_ACT_012 - Selected: Today");
    }

    // TC_ACT_013 - Verify selecting Yesterday from date range
    @Test(priority = 13)
    public void TC_ACT_013_verifySelectYesterday() {
        activityPage.clickLocationTab();
        activityPage.openDateRangeDropdown();
        activityPage.selectYesterday();
        System.out.println("PASS: TC_ACT_013 - Selected: Yesterday");
    }

    // TC_ACT_014 - Verify selecting This Week from date range
    @Test(priority = 14)
    public void TC_ACT_014_verifySelectThisWeek() {
        activityPage.clickLocationTab();
        activityPage.openDateRangeDropdown();
        activityPage.selectThisWeek();
        System.out.println("PASS: TC_ACT_014 - Selected: This week");
    }

    // TC_ACT_015 - Verify selecting Last Week from date range
    @Test(priority = 15)
    public void TC_ACT_015_verifySelectLastWeek() {
        activityPage.clickLocationTab();
        activityPage.openDateRangeDropdown();
        activityPage.selectLastWeek();
        System.out.println("PASS: TC_ACT_015 - Selected: Last Week");
    }

    // TC_ACT_016 - Verify selecting This Month from date range
    @Test(priority = 16)
    public void TC_ACT_016_verifySelectThisMonth() {
        activityPage.clickLocationTab();
        activityPage.openDateRangeDropdown();
        activityPage.selectThisMonth();
        System.out.println("PASS: TC_ACT_016 - Selected: This Month");
    }

    // TC_ACT_017 - Verify selecting Last Month from date range
    @Test(priority = 17)
    public void TC_ACT_017_verifySelectLastMonth() {
        activityPage.clickLocationTab();
        activityPage.openDateRangeDropdown();
        activityPage.selectLastMonth();
        System.out.println("PASS: TC_ACT_017 - Selected: Last Month");
    }

    // TC_ACT_018 - Verify selecting This Year from date range
    @Test(priority = 18)
    public void TC_ACT_018_verifySelectThisYear() {
        activityPage.clickLocationTab();
        activityPage.openDateRangeDropdown();
        activityPage.selectThisYear();
        System.out.println("PASS: TC_ACT_018 - Selected: This Year");
    }

    // TC_ACT_019 - Verify selecting Custom Range from date range
    @Test(priority = 19)
    public void TC_ACT_019_verifySelectCustomRange() {
        activityPage.clickLocationTab();
        activityPage.openDateRangeDropdown();
        activityPage.selectCustomRange();
        System.out.println("PASS: TC_ACT_019 - Selected: Custom Range");
    }
}