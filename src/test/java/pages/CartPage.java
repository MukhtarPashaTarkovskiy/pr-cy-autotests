package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    private final By continueShoppingButton = By.id("continue-shopping");
    private final By productNames = By.cssSelector(".inventory_item_name");

    public CartPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    private void waitForCartPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(continueShoppingButton));
    }

    public ArrayList<String> getProductsNames() {
        waitForCartPage();

        List<WebElement> allProducts = driver.findElements(productNames);
        ArrayList<String> names = new ArrayList<>();

        for (WebElement product : allProducts) {
            names.add(product.getText());
        }
        return names;
    }
}
