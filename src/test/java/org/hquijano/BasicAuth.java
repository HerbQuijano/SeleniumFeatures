package org.hquijano;

import org.openqa.selenium.By;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import java.net.URI;
import java.time.Duration;
import java.util.function.Predicate;

public class BasicAuth {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Predicate, consumer, function
        //Predicate<URI> uriPredicate = uri -> uri.getHost().contains("jigsaw.w3.org");
        Predicate<URI> uriPredicate = uri -> uri.getHost().contains("jigsaw.w3.org");

        //Cast driver into HasAuthentication class
        ((HasAuthentication)driver).register(uriPredicate, UsernameAndPassword.of("guest", "guest"));

        driver.get("https://jigsaw.w3.org/HTTP/");
        driver.findElement(By.cssSelector("a[href*='Basic/']")).click();



    }
}
