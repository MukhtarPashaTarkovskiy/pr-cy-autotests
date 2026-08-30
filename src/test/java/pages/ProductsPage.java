package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class ProductsPage {
    WebDriver driver;
    WebDriverWait wait;
    private final By pageTitle = By.cssSelector("[data-test='title']");

    public ProductsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    @Test
    public boolean isProductsPageVisible() {
        return driver.findElement(pageTitle).isDisplayed();
    }

    @Test
    public String getTitle() {
        return driver.findElement(pageTitle).getText();
    }
}
