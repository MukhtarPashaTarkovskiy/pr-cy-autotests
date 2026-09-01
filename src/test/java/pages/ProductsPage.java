package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class ProductsPage extends BasePage{

    private final By pageTitle = By.cssSelector("[data-test='title']");

    public ProductsPage(WebDriver driver, WebDriverWait wait) {
       super(driver,wait);
    }


    public boolean isProductsPageVisible() {
        return driver.findElement(pageTitle).isDisplayed();
    }


    public String getTitle() {
        return driver.findElement(pageTitle).getText();
    }
}
