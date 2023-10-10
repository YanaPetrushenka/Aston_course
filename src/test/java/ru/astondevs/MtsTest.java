package ru.astondevs;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MtsTest {
    private static WebDriver driver;
    private static WebElement payBlock;

    @BeforeAll
    static void setupTest() {
        var config = ResourceBundle.getBundle("config");
        driver = WebDriverManager.chromedriver().create();
        driver.get(config.getString("url"));
        payBlock = driver.findElement(By.xpath("//section[@class = 'pay']"));
    }

    @AfterAll
    static void teardown() {
        driver.quit();
    }

    @Test
    @Order(1)
    void testTitle() {
        var title = payBlock.findElement(By.xpath("div/h2"));
        assertEquals("Онлайн пополнение\nбез комиссии", title.getText());
    }

    @Test
    @Order(2)
    void testPaySystemsLogos() {
        var visa = payBlock.findElement(By.xpath("//img[@alt='Visa']"));
        assertTrue(visa.isDisplayed());

        var verifiedVisa = payBlock.findElement(By.xpath("//img[@alt='Verified By Visa']"));
        assertTrue(verifiedVisa.isDisplayed());

        var masterCard = payBlock.findElement(By.xpath("//img[@alt='MasterCard']"));
        assertTrue(masterCard.isDisplayed());

        var masterCardSecureCode = payBlock.findElement(By.xpath("//img[@alt='MasterCard Secure Code']"));
        assertTrue(masterCardSecureCode.isDisplayed());

        var belCard = payBlock.findElement(By.xpath("//img[@alt='Белкарт']"));
        assertTrue(belCard.isDisplayed());

        var mir = payBlock.findElement(By.xpath("//img[@alt='МИР']"));
        assertTrue(mir.isDisplayed());
    }

    @Test
    @Order(3)
    void testDetailsLink() {
        var detailsLink = payBlock.findElement(By.linkText("Подробнее о сервисе"));
        String url = detailsLink.getAttribute("href");
        var responseCode = getLinkResponseCode(url);
        assertEquals(200, responseCode);
    }

    @Test
    @Order(4)
    void testFieldsAndProceedButton() {
//        accept cookies
        var cookiesButton = driver.findElement(By.id("cookie-agree"));
        cookiesButton.click();

//        fill payment fields
        var optionsButton = payBlock.findElement(By.cssSelector("button[class='select__header']"));
        optionsButton.click();

        var serviceOption = payBlock.findElement(By.xpath("//p[contains(text(),'Услуги связи')]"));
        serviceOption.click();

        var phoneInput = payBlock.findElement(By.id("connection-phone"));
        phoneInput.sendKeys("297777777");

        var sumInput = payBlock.findElement(By.id("connection-sum"));
        sumInput.sendKeys("1");

        var emailInput = payBlock.findElement(By.id("connection-email"));
        emailInput.sendKeys("Jouhn@Doe.com");

        var proceedButton = payBlock.findElement(By.cssSelector("button[type='submit']"));
        proceedButton.click();

//        handle payment info popup
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe[class='bepaid-iframe']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p[class='header__payment-amount']")));

        var paymentPopupSum = driver.findElement(By.cssSelector("p[class='header__payment-amount']"));
        assertEquals("1.00 BYN", paymentPopupSum.getText());

        var paymentPopupInfo = driver.findElement(By.cssSelector("p[class='header__payment-info']"));
        assertEquals("Оплата: Услуги связи Номер:375297777777", paymentPopupInfo.getText());
    }

    private int getLinkResponseCode(String url) {
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(5))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response.statusCode();
    }
}