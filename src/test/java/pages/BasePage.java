package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertyReader;

public class BasePage {
    WebDriver driver;
    WebDriverWait wait;
    public static final String BASE_URL = PropertyReader.getProperty("sausedemo.url");
    public static final String ID_PATTERN = "#%s";
    protected static final String DATA_TEST_PATTERN = "[data-test='%s']";

    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void open() {
        driver.get(BASE_URL);
    }
}
