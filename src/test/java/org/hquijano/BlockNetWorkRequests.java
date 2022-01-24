package org.hquijano;

import com.google.common.collect.ImmutableList;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v96.network.Network;

import javax.annotation.concurrent.Immutable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BlockNetWorkRequests {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        DevTools devTools = driver.getDevTools();

        devTools.createSession();
        List<String> blockedUrls = new ArrayList<>();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.send(Network.setBlockedURLs(ImmutableList.of("*.jpg", "*.css"))); // block all jpg images

        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.linkText("Browse Products")).click();
        driver.findElement(By.linkText("Selenium")).click();
        driver.findElement(By.cssSelector(".add-to-cart.btn.btn-default")).click();
        System.out.println(driver.findElement(By.cssSelector(".sp")).getText());



    }
}
