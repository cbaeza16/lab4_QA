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
 * Nombre de la prueba: testNavigationAndContent
 * Objetivo: Verificar la navegación en el sitio web y validar que se carga el contenido esperado.
 * Datos de prueba: 
 * - Se accede al menú desde el encabezado del sitio.
 * - Se selecciona el primer elemento del contenido.
 * Resultado esperado: 
 * - Se debe mostrar el contenido esperado en la página seleccionada.
 * - El texto del contenido no debe estar vacío.
 */

public class PHPTravlesNavigationTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://phptravels.net/");
    }

    @Test
    public void testNavigationAndContent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement menuLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div/div[2]/div[1]/ul/li[6]/a")));
        menuLink.click();

        WebElement firstElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/main/section[2]/div/div/div[1]/div[1]/div[1]/div/div[2]")));
        firstElement.click();

        WebElement expectedContent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/section/div/div[1]/div[2]/div/div/p")));
        String actualText = expectedContent.getText();

        assertTrue("El texto esperado no está presente.", actualText.length() > 0);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
