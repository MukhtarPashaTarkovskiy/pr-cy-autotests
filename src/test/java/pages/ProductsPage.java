package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage extends BasePage {

    public static final String ADD_TO_CART_PATTERN =
            "//*[text()='%s']" +
                    "/ancestor::div[@class='inventory_item']" +
                    "//button[text()='Add to cart']";
    private final By cartBadge = By.cssSelector("[data-test='shopping-cart-badge']");
    private final By pageTitle = By.cssSelector("[data-test='title']");
    private final By shoppingCart = By.id("shopping_cart_container");

    public ProductsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean isProductsPageVisible() {
        return driver.findElement(pageTitle).isDisplayed();
    }

    public String getTitle() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(pageTitle)
        ).getText();
    }

    public void addGoodsToCart(String productName) {
        By addToCartButton = By.xpath(ADD_TO_CART_PATTERN.formatted(productName));
        driver.findElement(addToCartButton).click();
    }

    public void addGoodsToCart(int index) {
        By addToCartButton = By.xpath("//button[text()='Add to cart']");
        driver.findElements(addToCartButton).get(index).click();
    }

    public boolean isShoppingCartVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingCart)).isDisplayed();
    }

    public String getShoppingCartBadge() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge)).getText();
    }

    public String getCounterColor() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge))
                .getCssValue("background-color");
    }
}
