package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import user.User;

public class LoginPage extends BasePage {

    private final By loginInput = By.cssSelector(ID_PATTERN.formatted("user-name"));
    private final By passwordInput = By.cssSelector(ID_PATTERN.formatted("password"));
    private final By loginButton = By.id("login-button");
    private final By errorMessageLocator = By.xpath("//h3[@data-test='error']");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void enterUsername(String username) {
        driver.findElement(loginInput).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void login(User user) {
        enterUsername(user.getUser());
        enterPassword(user.getPassword());
        clickLoginButton();
    }

    public boolean isErrorMessageVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator)).isDisplayed();
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator)).getText();
    }
}
