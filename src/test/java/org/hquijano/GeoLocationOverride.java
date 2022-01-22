package org.hquijano;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v96.emulation.Emulation;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class GeoLocationOverride {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        devTools.createSession();

        Map<String, Object> geolocation = new HashMap<String, Object>();
        geolocation.put("latitude", 40.730610);
        geolocation.put("longitude", -73.935242);
        geolocation.put("accuracy", 5);

        //devTools.send(Emulation.setGeolocationOverride(Optional.of(20.481047), Optional.of(-86.971985), Optional.of(10)));
        driver.executeCdpCommand("Emulation.setGeolocationOverride", geolocation);

        driver.get("https://www.google.com/maps");
        driver.findElement(By.cssSelector("#pWhrzc-mylocation")).click();





    }
}
