package pages;

import io.qameta.allure.Step;
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

    @Step("Вводим логин: {username}")
    public void enterUsername(String username) {
        driver.findElement(loginInput).sendKeys(username);
    }

    @Step("Вводим пароль: {password}")
    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Нажимаем кнопку входа")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Вводим логин и пароль, нажимаем кнопку входа")
    public void login(User user) {
        enterUsername(user.getUser());
        enterPassword(user.getPassword());
        clickLoginButton();
    }

    @Step("Проверяем, что ошибка отображается")
    public boolean isErrorMessageVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator)).isDisplayed();
    }

    @Step("Получаем текст ошибки")
    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator)).getText();
    }
}
