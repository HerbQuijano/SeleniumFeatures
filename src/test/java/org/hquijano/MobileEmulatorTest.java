package org.hquijano;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v96.emulation.Emulation;

import java.time.Duration;
import java.util.Optional;

public class MobileEmulatorTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        //Chromedriver is a webdriver that is used to control Chrome browser
        //We don't use WebDriver interface because ChromeDriver exposes ChromiumDriver methods
        ChromeDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
               //command to cdp
       devTools.send(Emulation.setDeviceMetricsOverride(768, 1024, 25, true, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));

       driver.get("https://rahulshettyacademy.com/angularAppdemo/");
       driver.findElement(By.cssSelector(".navbar-toggler")).click();
       driver.findElement(By.linkText("Library")).click();






    }
}
