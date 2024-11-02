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

/**
 * Prueba: testUserLogin
 * Objetivo: Verificar que un usuario puede iniciar sesión correctamente en el sistema PHPTravels.
 * Datos de prueba:
 * - Correo electrónico: user@phptravels.com
 * - Contraseña: demouser
 * Resultado esperado: El usuario debe ser redirigido a la página después de iniciar sesión.
 */

public class PHPTravelsLoginTest {
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
    public void testUserLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/main/div[1]/form/div/div/div/div[1]/input")));
        emailField.sendKeys("user@phptravels.com");

        WebElement passwordField = driver.findElement(By.xpath("/html/body/main/div[1]/form/div/div/div/div[2]/input"));
        passwordField.sendKeys("demouser");

        WebElement loginButton = driver.findElement(By.xpath("/html/body/main/div[1]/form/div/div/div/div[4]/div[1]/button"));
        loginButton.click();

        wait.until(ExpectedConditions.urlContains("dashboard"));
        
        String currentUrl = driver.getCurrentUrl();
        System.out.println("La URL actual después del inicio de sesión es: " + currentUrl);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
