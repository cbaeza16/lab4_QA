package com.lab4;

import static org.junit.Assert.assertTrue;

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

public class PHPTravelsLoginNoPasswordTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().deleteAllCookies();
        driver.get("https://phptravels.net/login");
    }

    @Test
    public void testInvalidPasswordLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/main/div[1]/form/div/div/div/div[1]/input")));
        emailField.sendKeys("user@phptravels.com");

        WebElement passwordField = driver.findElement(By.xpath("/html/body/main/div[1]/form/div/div/div/div[2]/input"));
        passwordField.sendKeys("incorrectpassword");

        WebElement loginButton = driver.findElement(By.xpath("/html/body/main/div[1]/form/div/div/div/div[4]/div[1]/button"));
        loginButton.click();

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fadein']/div[4]/div[2]/div[2]/div")));
        
        String expectedErrorText = "Invalid Login";
        assertTrue("El mensaje de error no se muestra correctamente", errorMessage.getText().contains(expectedErrorText));
    }


    @After
    public void tearDown() {
        driver.quit();
    }
}
