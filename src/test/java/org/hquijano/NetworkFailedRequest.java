package org.hquijano;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.network.model.ResourceType;
import org.openqa.selenium.devtools.v96.fetch.Fetch;
import org.openqa.selenium.devtools.v96.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v96.fetch.model.RequestStage;
import org.openqa.selenium.devtools.v96.network.model.ErrorReason;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class NetworkFailedRequest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        DevTools devTools = driver.getDevTools();

        devTools.createSession();
        //List<RequestPattern> rp = Arrays.asList(new RequestPattern(Optional.of("*GetBook*"), Optional.empty(), Optional.empty()));
        List<RequestPattern> rp = Collections.singletonList(new RequestPattern(Optional.of("*GetBook*"), Optional.empty(), Optional.empty()));

        devTools.send(Fetch.enable(Optional.of(rp), Optional.empty()));

        devTools.addListener(Fetch.requestPaused(), request -> {
            devTools.send(Fetch.failRequest(request.getRequestId(), ErrorReason.FAILED));

        });


        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.cssSelector(".navbar-toggler")).click();
        driver.findElement(By.linkText("Library")).click();

    }
}
