package com.aipxperts.punchly.base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    public WebDriver driver;

    @BeforeMethod
    public void setup() {

        System.out.println("SETUP METHOD RUNNING");

        // Setup ChromeDriver automatically
        WebDriverManager.chromedriver().setup();

        // Launch browser
        driver = new ChromeDriver();

        // Maximize window
        driver.manage().window().maximize();

        // Wait handling
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Open application
        driver.get("https://dev-app.punchly.work/en/dashboard");
    }

    @AfterMethod
    public void tearDown() {

        // ✅ Logout Logic Added (safe execution)
        try {
            // Click profile/avatar (update locator if needed)
            driver.findElement(By.xpath("//span[@class='flex size-full items-center justify-center rounded-full bg-gradient-to-br from-[#008000] to-emerald-600 text-white text-xs sm:text-sm font-medium']")).click();

            Thread.sleep(2000);

            // Click Logout
            driver.findElement(By.xpath("//div[@class=\"!cursor-pointer data-[variant=destructive]:text-destructive data-[variant=destructive]:focus:bg-destructive/10 dark:data-[variant=destructive]:focus:bg-destructive/20 data-[variant=destructive]:focus:text-destructive data-[variant=destructive]:*:[svg]:!text-destructive [&_svg:not([class*='text-'])]:text-muted-foreground relative flex items-center gap-2 text-sm outline-hidden select-none data-[disabled]:pointer-events-none data-[disabled]:opacity-50 data-[inset]:pl-8 [&_svg]:pointer-events-none [&_svg]:shrink-0 [&_svg:not([class*='size-'])]:size-4 cursor-pointer px-2 py-2.5 rounded-lg text-red-600 focus:text-red-600 focus:bg-red-50\"]")).click();

            System.out.println("✅ Logged out successfully");

        } catch (Exception e) {
            System.out.println("⚠ Logout skipped (element not found or already logged out)");
        }

        System.out.println("CLOSING BROWSER");

        if (driver != null) {
            driver.quit();
        }
    }
}
