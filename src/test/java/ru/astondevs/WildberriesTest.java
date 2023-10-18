package ru.astondevs;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import ru.astondevs.element.Product;
import ru.astondevs.page.MainPage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WildberriesTest {
    private final static int PRODUCT_COUNT = 3;
    private static WebDriver driver;
    private static MainPage mainPage;

    @BeforeAll
    static void setupTest() {
        var config = ResourceBundle.getBundle("config");
        driver = WebDriverManager.chromedriver().create();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
        driver.get(config.getString("url"));

        mainPage = new MainPage(driver);
        mainPage.acceptCookies();
    }

    @AfterAll
    static void teardown() {
        driver.quit();
    }

    @Test
    void testBasket() {
        var selectedProducts = mainPage.putProductsToBasket(PRODUCT_COUNT);
        var basketPage = mainPage.openBasketPage();
        var productsInBasket = basketPage.getProducts();

//      check products count in basket
        assertEquals(PRODUCT_COUNT, basketPage.productCount());

//      check products selected from main page and products in basket (equals method in Product implemented)
        assertEquals(selectedProducts.size(), productsInBasket.size());
        assertTrue(selectedProducts.containsAll(productsInBasket));
        assertTrue(productsInBasket.containsAll(selectedProducts));

//       check total sum
        var selectedProductsTotalSum = selectedProducts.stream()
                .mapToDouble(Product::getPrice)
                .sum();
        selectedProductsTotalSum = new BigDecimal(selectedProductsTotalSum).setScale(2, RoundingMode.HALF_UP).doubleValue();

        assertEquals(selectedProductsTotalSum, basketPage.getTotalSum());
    }
}
