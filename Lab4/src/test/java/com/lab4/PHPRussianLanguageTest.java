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
 * Nombre de la prueba: testLanguageSelection
 * Objetivo: Verificar que la selección del idioma ruso en el sitio PHPTRAVELS 
 * cambie correctamente el idioma de la interfaz.
 * Datos de prueba: Seleccionar 'RUSIIAN' desde el menú de idiomas.
 * Resultado esperado: 
 * 1. El idioma seleccionado debe mostrarse como 'Russian' en el menú de idiomas.
 * 2. El botón de hoteles debe mostrar el texto 'Отели'.
 */


public class PHPRussianLanguageTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://phptravels.net/");
    }

    @Test
    public void testLanguageSelection() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        WebElement languageMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div/div[2]/div[2]/ul/li[1]/a")));
        languageMenu.click();
        
        WebElement russianOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div/div[2]/div[2]/ul/li[1]/ul/li[4]/a")));
        russianOption.click();
        
        WebElement selectedLanguage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/header/div/div[2]/div[2]/ul/li[1]/a/strong")));
        String actualText = selectedLanguage.getText();
        assertTrue("El texto no indica 'RUSSIAN'", actualText.contains("RUSSIAN"));

        WebElement hotelsButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/div[1]/div[2]/div[2]/div/div[2]/ul/li[1]/button/span")));
        String hotelsText = hotelsButton.getText();
        assertTrue("El texto no indica 'Отели'", hotelsText.equals("Отели"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
