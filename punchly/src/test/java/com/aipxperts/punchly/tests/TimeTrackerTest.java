package com.aipxperts.punchly.tests;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import com.aipxperts.punchly.base.BaseTest;
import com.aipxperts.punchly.pages.LoginPage;
import com.aipxperts.punchly.pages.TimeTrackerPage;

public class TimeTrackerTest extends BaseTest {

    @Test
    public void testTimeTracker() throws InterruptedException {

        // ===== LOGIN =====
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("sumit.nevase@aipxperts.com");
        loginPage.enterOTP();

        TimeTrackerPage tracker = new TimeTrackerPage(driver);

        // ===== STEP 1: Enter Task =====
        tracker.enterTask("This is new testing task");

        // ===== STEP 2: Select Project =====
        tracker.selectProject();

        // ===== STEP 3: Start Timer =====
        tracker.clickStart();

        Thread.sleep(10000);

        // ===== STEP 4: Stop Timer =====
        tracker.clickStop();
        Thread.sleep(5000);
        // ===== STEP 5: Manual Entry =====
        tracker.clickManual();
        Thread.sleep(5000);
        tracker.selectProject();   // again select project
        Thread.sleep(5000);
        tracker.enterManualTime();
        
        Thread.sleep(5000);
        tracker.clickAddEntry();

        Thread.sleep(10000);

        // ===== STEP 6: Back to Timer =====
        tracker.clickTimer();

        System.out.println("✅ Full Flow Completed");
    }
}

