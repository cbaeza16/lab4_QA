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

/**
 * Nombre de la prueba: testPHPLocationsSearch
 * Objetivo: Verificar que la búsqueda de la ubicación "Dubai" en el sitio PHPTravels se realice correctamente.
 * Datos de prueba: 
 * - Búsqueda de la ciudad: "Dubai".
 * Resultado esperado: 
 * - El texto del resultado debe contener "Dubai" después de realizar la búsqueda.
 */

public class PHPLocationsSearchTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://phptravels.net/");
    }

    @Test
    public void testPHPLocationsSearch() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/main/div[1]/div[2]/div[2]/div/div[2]/div/div/div[1]/form/div/div[1]/div[1]/span/span[1]/span/span[1]")));
        searchBox.click();

        WebElement cityOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/span/span/span[2]/ul/div/div[1]")));
        cityOption.click();
       

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/main/div[1]/div[2]/div[2]/div/div[2]/div/div/div[1]/form/div/div[5]/button")));
        searchButton.click();

        WebElement resultText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/div[2]/div/div/div[2]/div/div[1]/div/div/div[2]/div/p")));
        String actualText = resultText.getText();
        assertTrue("El texto no contiene 'Dubai'", actualText.contains("Dubai"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
