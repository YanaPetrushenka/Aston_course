package ru.astondevs.page;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.astondevs.element.Product;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class BasketPage {
    private static final String TITLE = "Корзина";
    private final WebDriver driver;
    private final WebDriverWait wait;

    public BasketPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        if (!TITLE.equals(driver.getTitle())) {
            throw new IllegalStateException();
        }
    }

    public int productCount() {
        var countStr = driver.findElement(By.cssSelector("span[class='basket__items-counter'")).getText();
        return Integer.parseInt(countStr);
    }

    public List<Product> getProducts() {
        List<String> itemsIds = List.of();
        try {
            itemsIds = getBasketItemsIds();
        } catch (StaleElementReferenceException e) {
            driver.navigate().refresh();
            itemsIds = getBasketItemsIds();
        }

        return itemsIds.stream()
                .map(this::getProduct)
                .collect(Collectors.toList());
    }

    public double getTotalSum() {
        var priceStr = driver.findElement(By.cssSelector("span[data-tag='totalSum']")).getText();
        return parsePrice(priceStr);
    }

    private Product getProduct(String itemId) {
        String itemDivXpath = String.format("//div[@id='%s']//", itemId);
        try {
            return createProduct(itemDivXpath);
        } catch (StaleElementReferenceException e) {
            driver.navigate().refresh();
            return createProduct(itemDivXpath);
        }
    }

    private double parsePrice(String priceStr) {
        priceStr = priceStr
                .replace(",", ".")
                .replace(" р.", "")
                .trim();
        return Double.parseDouble(priceStr);
    }

    private Product createProduct(String rootDivXpath) {
        var name = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(rootDivXpath + "span[@data-tag='itemName']"))).getText()
                .replace("шт.", "шт")
                .trim();
        var priceStr = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(rootDivXpath + "div[@data-tag='salePrice']"))).getText();
        var price = parsePrice(priceStr);
        return new Product(name, price);
    }

    private List<String> getBasketItemsIds() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[id*='basket-item-']")))
                .stream()
                .map(item -> item.getAttribute("id"))
                .collect(Collectors.toList());
    }
}
