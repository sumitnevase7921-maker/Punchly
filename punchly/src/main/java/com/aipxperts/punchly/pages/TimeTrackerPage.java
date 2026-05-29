
package com.aipxperts.punchly.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TimeTrackerPage {

    WebDriver driver;

    public TimeTrackerPage(WebDriver driver) {
        this.driver = driver;
    }

    // ================= LOCATORS =================

    By startButton = By.xpath("//button[@class=\"inline-flex items-center justify-center gap-2 whitespace-nowrap rounded-md text-sm transition-all disabled:pointer-events-none [&_svg]:pointer-events-none [&_svg:not([class*='size-'])]:size-4 [&_svg]:shrink-0 outline-none focus-visible:border-ring focus-visible:ring-ring/50 focus-visible:ring-[3px] aria-invalid:ring-destructive/20 dark:aria-invalid:ring-destructive/40 aria-invalid:border-destructive cursor-pointer py-2 has-[>svg]:px-3 shrink-0 bg-[#008000] hover:bg-[#006600] text-white h-10 px-6 font-semibold uppercase tracking-wide disabled:opacity-50\"]");

    By stopButton = By.xpath("//button[@class=\"inline-flex items-center justify-center gap-2 whitespace-nowrap rounded-md text-sm transition-all disabled:pointer-events-none [&_svg]:pointer-events-none [&_svg:not([class*='size-'])]:size-4 [&_svg]:shrink-0 outline-none focus-visible:border-ring focus-visible:ring-ring/50 focus-visible:ring-[3px] aria-invalid:ring-destructive/20 dark:aria-invalid:ring-destructive/40 aria-invalid:border-destructive cursor-pointer py-2 has-[>svg]:px-3 shrink-0 bg-red-500 hover:bg-red-600 text-white h-10 px-6 font-semibold uppercase tracking-wide disabled:opacity-50\"]");

    By taskInput = By.xpath("//input[@placeholder='What are you working on?']");

    // ================= BASIC ACTIONS =================

    public void enterTask(String task) {
        driver.findElement(taskInput).sendKeys(task);
    }

    public void clickStart() {
        driver.findElement(startButton).click();
    }

    public void clickStop() {
        driver.findElement(stopButton).click();
    }

    // ================= NEW METHODS (SCENARIO) =================

    // Step: Select Project
    public void selectProject() throws InterruptedException {

        driver.findElement(By.xpath("//span[contains(text(),'Project') or contains(text(),'+ Project')]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//input[@placeholder='Search Project, Task, or Client']"))
                .sendKeys("Sumit Nevase");

        Thread.sleep(2000);

        driver.findElement(By.xpath("//span[contains(text(),'Sumit Nevase')]")).click();
    }

    // Step: Click Manual
    public void clickManual() {
        driver.findElement(By.xpath("//button[contains(text(),'MANUAL') or contains(text(),'Manual')]")).click();
    }

    // Step: Enter Time
    public void enterManualTime() {

        driver.findElement(By.xpath("(//input[@placeholder='HH:mm'])[1]")).clear();
        driver.findElement(By.xpath("(//input[@placeholder='HH:mm'])[1]")).sendKeys("10:00");

        driver.findElement(By.xpath("(//input[@placeholder='HH:mm'])[2]")).clear();
        driver.findElement(By.xpath("(//input[@placeholder='HH:mm'])[2]")).sendKeys("10:30");
    }

    // Step: Add Entry
    public void clickAddEntry() {
        driver.findElement(By.xpath("//button[contains(text(),'ADD') or contains(text(),'Add')]")).click();
    }

    // Step: Back to Timer
    public void clickTimer() {
        driver.findElement(By.xpath("//button[contains(text(),'TIMER') or contains(text(),'Timer')]")).click();
    }
}

