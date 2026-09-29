package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.Color;

public class ProductsPage extends BasePage {

    public static final String ADD_TO_CART_PATTERN =
            "//*[text()='%s']" +
                    "/ancestor::div[@class='inventory_item']" +
                    "//button[text()='Add to cart']";
    private final By cartBadge = By.cssSelector("[data-test='shopping-cart-badge']");
    private final By pageTitle = By.cssSelector("[data-test='title']");
    private final By shoppingCart = By.id("shopping_cart_container");
    private final By cartLink = By.cssSelector(DATA_TEST_PATTERN.formatted("shopping-cart-link"));

    public ProductsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Step("Проверяем отображение страницы Products")
    public boolean isProductsPageVisible() {
        return driver.findElement(pageTitle).isDisplayed();
    }

    @Step("Получаем заголовок страницы")
    public String getTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).getText();
    }

    @Step("Добавляем товар '{productName}' в корзину")
    public void addGoodsToCart(String productName) {
        By addToCartButton = By.xpath(ADD_TO_CART_PATTERN.formatted(productName));
        driver.findElement(addToCartButton).click();
    }

    @Step("Добавляем товар с индексом {index} в корзину")
    public void addGoodsToCart(int index) {
        By addToCartButton = By.xpath("//button[text()='Add to cart']");
        driver.findElements(addToCartButton).get(index).click();
    }

    @Step("Проверяем отображение корзины")
    public boolean isShoppingCartVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingCart)).isDisplayed();
    }

    @Step("Получаем количество товаров в корзине")
    public String getShoppingCartBadge() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge)).getText();
    }

    @Step("Получаем цвет счётчика корзины")
    public String getCounterColor() {
        String cssColor = wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge))
                .getCssValue("background-color");
        return Color.fromString(cssColor).asRgb();
    }

    @Step("Переходим в корзину")
    public void switchToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();
    }
}
