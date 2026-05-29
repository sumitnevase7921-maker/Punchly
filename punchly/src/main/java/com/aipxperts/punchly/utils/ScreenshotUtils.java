package com.aipxperts.punchly.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    private static final String SCREENSHOT_DIR = 
        System.getProperty("user.dir") + File.separator + "screenshots";

    public static void captureScreenshot(WebDriver driver, String testName) {
        try {
            File dir = new File(SCREENSHOT_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = testName + "_FAILED_" + timestamp + ".png";
            File destFile = new File(dir, fileName);

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(srcFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            // Print FULL absolute path so you know exactly where it is saved
            System.out.println("📸 Screenshot saved: " + destFile.getAbsolutePath());

        } catch (IOException e) {
            System.out.println("⚠ Could not save screenshot: " + e.getMessage());
        }
    }
}