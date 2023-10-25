package ru.astondevs;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.options.BaseOptions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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
    void testSum() {
        var sum = calculatorScreen.sum(2, 3);
        assertEquals("5", sum);
    }

    @Test
    void testMinus() {
        var sum = calculatorScreen.minus(7, 3);
        assertEquals("4", sum);
    }

    @Test
    void testMultiply() {
        var sum = calculatorScreen.multiply(5, 6);
        assertEquals("30", sum);
    }

    @Test
    void testDivision() {
        var sum = calculatorScreen.division(8, 2);
        assertEquals("4", sum);
    }

}
