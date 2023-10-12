package ru.astondevs;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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

//        accept cookies
        var cookiesButton = driver.findElement(By.id("cookie-agree"));
        cookiesButton.click();

        payBlock = driver.findElement(By.xpath("//section[@class = 'pay']"));
    }

    @AfterAll
    static void teardown() {
        driver.quit();
    }

    @Test
    @Order(1)
    void testPayForms() {
        var payFormsExpectedPlaceholders = Map.of(
                "pay-connection", List.of("Номер телефона", "Сумма", "E-mail для отправки чека"),
                "pay-internet", List.of("Номер абонента", "Сумма", "E-mail для отправки чека"),
                "pay-instalment", List.of("Номер счета на 44", "Сумма", "E-mail для отправки чека"),
                "pay-arrears", List.of("Номер счета на 2073", "Сумма", "E-mail для отправки чека")
        );

        for (var entry : payFormsExpectedPlaceholders.entrySet()) {
            var actualPlaceholders = getPayFormPlaceholders(entry.getKey());
            var expectedPlaceholders = entry.getValue();

            assertEquals(expectedPlaceholders.size(), actualPlaceholders.size());
            assertTrue(actualPlaceholders.containsAll(expectedPlaceholders));
            assertTrue(expectedPlaceholders.containsAll(actualPlaceholders));
        }
    }

    @Test
    @Order(2)
    void testFieldsAndProceedButton() {
        fillConnectionPayForm();
        var proceedButton = payBlock.findElement(By.cssSelector("button[type='submit']"));
        proceedButton.click();

//        wait payment popup visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe[class='bepaid-iframe']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p[class='header__payment-amount']")));

//        check sum header
        var sumHeader = driver.findElement(By.cssSelector("p[class='header__payment-amount']"));
        assertEquals("1.00 BYN", sumHeader.getText());

//        check sum button
        var submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        assertEquals("Оплатить 1.00 BYN", submitButton.getText());

//        check phone header
        var phoneHeader = driver.findElement(By.cssSelector("p[class='header__payment-info']"));
        assertEquals("Оплата: Услуги связи Номер:375297777777", phoneHeader.getText());

//        check card inputs placeholders
        var cardInputsExpectedLabels = Map.of(
                "creditCard", "Номер карты",
                "expirationDate", "Срок действия",
                "cvc", "CVC",
                "holder", "Имя держателя (как на карте)"
        );

        cardInputsExpectedLabels.forEach((key, value) -> {
            var labelXpath = String.format("//div[input[@formcontrolname='%s']]/label", key);
            var label = driver.findElement(By.xpath(labelXpath));
            assertEquals(value, label.getText());
        });

//        check logos
        var logosImageNames = List.of("mastercard-system.svg", "visa-system.svg", "belkart-system.svg");
        var logoXpathTemplate = "//div[contains (@class, 'cards-brands__container')]//img[contains (@src, '%s')]";

        logosImageNames.forEach(name -> {
                    var logoImgXpath = String.format(logoXpathTemplate, name);
                    var logoImg = driver.findElement(By.xpath(logoImgXpath));
                    assertTrue(logoImg.isDisplayed());
                }
        );

        var mirLogo = driver.findElement(By.xpath(String.format(logoXpathTemplate, "mir-system-ru.svg")));
        var maestroLogo = driver.findElement(By.xpath(String.format(logoXpathTemplate, "maestro-system.svg")));
        assertTrue(mirLogo.isDisplayed() || maestroLogo.isDisplayed());
    }

    private List<String> getPayFormPlaceholders(String payFormId) {
        var payForm = payBlock.findElement(By.id(payFormId));

        return payForm.findElements(By.tagName("input")).stream()
                .map(e -> e.getAttribute("placeholder"))
                .collect(Collectors.toList());
    }

    private void fillConnectionPayForm() {
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
    }
}