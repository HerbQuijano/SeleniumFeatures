package org.hquijano;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.time.Duration;
import java.util.List;

public class ConsoleLogsCapture {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //listener - OnTestFailure TestNG
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.xpath("//p/a[@routerlink='/products']")).click();
        driver.findElement(By.linkText("Selenium")).click();
        driver.findElement(By.cssSelector("button[class$='add-to-cart btn btn-default']")).click();
        driver.findElement(By.cssSelector("button[routerlink*='/products']")).click();
        driver.findElement(By.linkText("Appium")).click();
        driver.findElement(By.cssSelector("button[class$='add-to-cart btn btn-default']")).click();
        driver.findElement(By.cssSelector("a[routerlink='/cart']")).click();
        driver.findElement(By.xpath("//tbody/tr[1]/td[2]/input")).clear();
        driver.findElement(By.xpath("//tbody/tr[1]/td[2]/input")).sendKeys("2");

        LogEntries entry = driver.manage().logs().get(LogType.BROWSER);
        List<LogEntry> logs = entry.getAll();

        for (LogEntry log : logs) {
            System.out.println(log.getMessage());
        }

    }
}
