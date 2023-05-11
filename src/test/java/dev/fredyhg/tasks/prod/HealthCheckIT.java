package dev.fredyhg.tasks.prod;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class HealthCheckIT {

    @Test
    public void healthCheck() throws MalformedURLException {
        ChromeOptions cap = new ChromeOptions();
        WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444"), cap);
        try {
            driver.navigate().to("http://192.168.56.1:9999/tasks");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            String version = driver.findElement(By.id("version")).getText();
            Assert.assertTrue(version.startsWith("build"));
        } finally {
            driver.quit();
        }
    }

}
