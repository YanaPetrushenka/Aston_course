package ru.astondevs.screen;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CalculatorScreen {
    private static final String PACKAGE = "com.google.android.calculator";
    private static final String DIGIT_BTN_TEMPLATE = PACKAGE + ":id/digit_%d";
    private final WebDriver driver;
    private final WebElement btnEq;
    private final WebElement btnClr;

    public CalculatorScreen(WebDriver driver) {
        this.driver = driver;
        btnEq = driver.findElement(AppiumBy.id(String.format(PACKAGE + ":id/eq")));
        btnClr = driver.findElement(AppiumBy.id(String.format(PACKAGE + ":id/clr")));
    }

    public void clear() {
        btnClr.click();
    }

    public String sum(int num1, int num2) {
        var btnPlus = driver.findElement(AppiumBy.id(String.format(PACKAGE + ":id/op_add")));
        return performOperation(num1, num2, btnPlus);
    }

    public String minus(int num1, int num2) {
        var btnPlus = driver.findElement(AppiumBy.id(String.format(PACKAGE + ":id/op_sub")));
        return performOperation(num1, num2, btnPlus);
    }

    public String multiply(int num1, int num2) {
        var btnPlus = driver.findElement(AppiumBy.id(String.format(PACKAGE + ":id/op_mul")));
        return performOperation(num1, num2, btnPlus);
    }

    public String division(int num1, int num2) {
        var btnPlus = driver.findElement(AppiumBy.id(String.format(PACKAGE + ":id/op_div")));
        return performOperation(num1, num2, btnPlus);
    }

    private String performOperation(int num1, int num2, WebElement operationBtn) {
        var btnNum1 = driver.findElement(AppiumBy.id(String.format(DIGIT_BTN_TEMPLATE, num1)));
        btnNum1.click();
        operationBtn.click();
        var btnNum2 = driver.findElement(AppiumBy.id(String.format(DIGIT_BTN_TEMPLATE, num2)));
        btnNum2.click();
        btnEq.click();
        return driver.findElement(AppiumBy.id(String.format(PACKAGE + ":id/result_final"))).getText();
    }
}
