package com.aipxperts.punchly.listeners;

import com.aipxperts.punchly.utils.ScreenshotUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = getDriver(result);
        if (driver != null) {
            ScreenshotUtils.captureScreenshot(driver, result.getName());
            System.out.println("📸 Failure screenshot taken for: " + result.getName());
        } else {
            System.out.println("⚠ ScreenshotListener: driver is null, screenshot skipped.");
        }
    }

    /**
     * Gets the WebDriver from the test instance.
     * First checks the test class itself, then walks up to superclass (BaseTest)
     * where the driver field actually lives.
     */
    private WebDriver getDriver(ITestResult result) {
        Object testInstance = result.getInstance();
        Class<?> clazz = testInstance.getClass();

        // Walk up the class hierarchy until we find the "driver" field
        while (clazz != null) {
            try {
                java.lang.reflect.Field field = clazz.getDeclaredField("driver");
                field.setAccessible(true);
                Object value = field.get(testInstance);
                if (value instanceof WebDriver) {
                    return (WebDriver) value;
                }
            } catch (NoSuchFieldException e) {
                // Not in this class, go up to superclass
                clazz = clazz.getSuperclass();
            } catch (Exception e) {
                System.out.println("⚠ ScreenshotListener: Error accessing driver — " + e.getMessage());
                break;
            }
        }
        return null;
    }

    @Override public void onTestStart(ITestResult result) {}
    @Override public void onTestSuccess(ITestResult result) {}
    @Override public void onTestSkipped(ITestResult result) {}
}