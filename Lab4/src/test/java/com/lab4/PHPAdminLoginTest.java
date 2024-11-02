package com.lab4;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PHPAdminLoginTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        driver.manage().deleteAllCookies();
        driver.get("https://phptravels.net/admin/login.php");
    }

    @Test
    public void testAdminLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div/div[1]/form/div[1]/div/input")));
        emailField.sendKeys("admin@phptravels.com");

        WebElement passwordField = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[1]/form/div[2]/div/input"));
        passwordField.sendKeys("demoadmin");

        WebElement loginButton = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[1]/form/button"));
        loginButton.click();

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
