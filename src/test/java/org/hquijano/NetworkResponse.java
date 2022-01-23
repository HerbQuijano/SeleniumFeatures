package org.hquijano;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v96.network.Network;
import org.openqa.selenium.devtools.v96.network.model.Request;
import org.openqa.selenium.devtools.v96.network.model.Response;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class NetworkResponse {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        devTools.createSession();
        driver.get("https://rahulshettyacademy.com/angularAppdemo");
        driver.findElement(By.cssSelector(".btn.btn-primary")).click();

        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        //Event trigger
        devTools.addListener(Network.requestWillBeSent(), request ->
        {
            Request req = request.getRequest();
            System.out.println(req.getUrl());
        });

        devTools.addListener(Network.responseReceived(), response ->
        {
            Response res = response.getResponse();
            //System.out.println(res.getUrl());
            System.out.println(res.getStatus());
            //res.getStatus();
            if (res.getStatus().toString().startsWith("4")) {
                System.out.println("Error");
                System.out.println(res.getUrl() + " failed with " + res.getStatus());
            }
        });

        driver.get("https://rahulshettyacademy.com/angularAppdemo");
        driver.findElement(By.cssSelector(".btn.btn-primary")).click();

    }

}

