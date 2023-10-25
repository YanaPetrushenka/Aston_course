package ru.astondevs;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.options.BaseOptions;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.*;
import ru.astondevs.screen.CalculatorScreen;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {
    private static final String DEVICE_NAME = "POCO M3";
    private static final String AUTOMATION_NAME = "UiAutomator2";
    private static final String URL = "http://127.0.0.1:4723";

    private static AppiumDriver driver;
    private static CalculatorScreen calculatorScreen;

    @BeforeAll
    static void setUp() throws MalformedURLException {
        var options = new BaseOptions()
                .setPlatformName("Android")
                .setAutomationName(AUTOMATION_NAME)
                .amend("deviceName", DEVICE_NAME)
                .amend("appPackage", "com.google.android.calculator")
                .amend("appActivity", "com.android.calculator2.Calculator")
                .noReset();
        driver = new AppiumDriver(new URL(URL), options);
        calculatorScreen = new CalculatorScreen(driver);
    }

    @AfterAll
    static void teardown() {
        driver.quit();
    }

    @AfterEach
    void clear() {
        calculatorScreen.clear();
    }

    @Test
    @DisplayName("Test two numbers sum")
    @Description("The test attempts to put two numbers and find their sum, check result")
    @Severity(SeverityLevel.CRITICAL)
    void testSum() {
        var sum = calculatorScreen.sum(2, 3);
        assertEquals("5", sum);
    }

    @Test
    @DisplayName("Test two numbers subtraction")
    @Description("The test attempts to put two numbers and find their subtraction, check result")
    @Severity(SeverityLevel.CRITICAL)
    void testMinus() {
        var sum = calculatorScreen.minus(7, 3);
        assertEquals("4", sum);
    }

    @Test
    @DisplayName("Test two numbers multiplication")
    @Description("The test attempts to put two numbers and find their multiplication, check result")
    @Severity(SeverityLevel.CRITICAL)
    void testMultiply() {
        var sum = calculatorScreen.multiply(5, 6);
        assertEquals("30", sum);
    }

    @Test
    @DisplayName("Test two numbers division")
    @Description("The test attempts to put two numbers and find their division, check result")
    @Severity(SeverityLevel.CRITICAL)
    void testDivision() {
        var sum = calculatorScreen.division(8, 2);
        assertEquals("4", sum);
    }
}
