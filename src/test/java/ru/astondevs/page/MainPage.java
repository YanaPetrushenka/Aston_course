package ru.astondevs.page;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.astondevs.element.Product;

import java.util.List;
import java.util.stream.Collectors;

public class MainPage {
    private static final String TITLE = "Wildberries — интернет-магазин модной одежды, обуви и аксессуаров";
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        if (!TITLE.equals(driver.getTitle())) {
            throw new IllegalStateException();
        }
    }

    public void acceptCookies() {
        var cookiesButton = driver.findElement(By.xpath("//div/button[@class='btn modal-body__deny']"));
        cookiesButton.click();
    }

    public List<Product> putProductsToBasket(int quantity) {
        return driver.findElements(By.cssSelector("div[class='card-cell'"))
                .stream()
                .limit(quantity)
                .map(this::putToBasketAndCreteProduct)
                .collect(Collectors.toList());
    }

    public BasketPage openBasketPage() {
        var basketButton = driver.findElement(By.xpath("//a[@href='/basket']"));
        basketButton.click();
        return new BasketPage(driver);
    }

    private double parsePrice(WebElement card) {
        var priceStr = card.findElement(By.cssSelector("span[data-tag='salePrice']")).getText();

        priceStr = priceStr
                .replace(" р.", "")
                .replace(",", ".")
                .trim();

        return Double.parseDouble(priceStr);
    }

    private Product putToBasketAndCreteProduct(WebElement card) {
        var putBtn = card.findElement(By.cssSelector("button[data-tag='btnBasket']"));
        putBtn.click();

//        check if sizes div displayed and chose any size
        var sizesDiv = driver.findElement(By.xpath("//div[@data-tag='mainToBasket']/div[@data-tag='mdToBasket']"));

        if (sizesDiv.isDisplayed()) {
            var sizeBtn = sizesDiv.findElement(By.cssSelector("div[data-tag='sizeListItem']"));
            sizeBtn.click();
        }

        var name = card.findElement(By.cssSelector("span[class='b-card__name']")).getText()
                .replace("шт.", "шт")
                .trim();

        var price = parsePrice(card);
        return new Product(name, price);
    }
}
