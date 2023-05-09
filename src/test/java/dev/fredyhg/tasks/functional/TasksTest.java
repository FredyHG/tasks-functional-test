package dev.fredyhg.tasks.functional;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TasksTest {


    public WebDriver acessarAplicacao(){
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("http://localhost:8001/tasks");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return driver;
    }



    @Test
    public void deveSalvarTarefaComSucesso(){

        WebDriver driver = acessarAplicacao();

        try {
            driver.findElement(By.id("addTodo")).click();

            driver.findElement(By.id("task")).sendKeys("Test via Selenium");

            driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");

            driver.findElement(By.id("saveButton")).click();

            String message = driver.findElement(By.id("message")).getText();

            Assertions.assertEquals("Success!", message);

        } finally {
            driver.quit();
        }
    }

    @Test
    public void naoDeveSalvarTarefaSemDesc(){

        WebDriver driver = acessarAplicacao();

        try {
            driver.findElement(By.id("addTodo")).click();

            driver.findElement(By.id("dueDate")).sendKeys("10/10/2020");

            driver.findElement(By.id("saveButton")).click();

            String message = driver.findElement(By.id("message")).getText();

            Assertions.assertEquals("Fill the task description correct", message);
        } finally {
            driver.quit();
        }

    }

    @Test
    public void deveSalvarTarefaSemData(){

        WebDriver driver = acessarAplicacao();

        try {
            driver.findElement(By.id("addTodo")).click();

            driver.findElement(By.id("task")).sendKeys("Test via Selenium");

            driver.findElement(By.id("saveButton")).click();

            String message = driver.findElement(By.id("message")).getText();

            Assertions.assertEquals("Fill the due date", message);

        } finally {
            driver.quit();
        }
    }

    @Test
    public void deveSalvarTarefaComDataPassada(){

        WebDriver driver = acessarAplicacao();

        try {
            driver.findElement(By.id("addTodo")).click();

            driver.findElement(By.id("task")).sendKeys("Test via Selenium");

            driver.findElement(By.id("dueDate")).sendKeys("10/10/2020");

            driver.findElement(By.id("saveButton")).click();

            String message = driver.findElement(By.id("message")).getText();

            Assertions.assertEquals("Due date must not be in past", message);

        } finally {
            driver.quit();
        }
    }

}
