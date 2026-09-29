package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;
import user.UserFactory;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Epic("Авторизация")
@Feature("Вход в систему")
public class LoginTest extends BaseTest {

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][]{
                {
                        new User("12345", wrongPassword),
                        "Epic sadface: Username and password do not match any user in this service"
                },
                {
                        new User(UserFactory.withAdminPermission().getUser(), wrongPassword),
                        "Epic sadface: Username and password do not match any user in this service"
                },
                {
                        new User("", password),
                        "Epic sadface: Username is required"
                },
                {
                        new User(UserFactory.withAdminPermission().getUser(), ""),
                        "Epic sadface: Password is required"
                },
                {
                        UserFactory.withLockedPermission(),
                        "Epic sadface: Sorry, this user has been locked out."
                },
                {
                        new User("", ""),
                        "Epic sadface: Username is required"
                }
        };
    }

    @Story("Неуспешная авторизация")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка отображения корректного сообщения об ошибке при авторизации с невалидными данными")
    @Test(dataProvider = "invalidLoginData")
    public void invalidLoginShowsErrorMessage(User user, String expectedMessage) {
        loginPage.open();
        loginPage.login(user);
        assertTrue(loginPage.isErrorMessageVisible(), "Error message is not visible");
        assertEquals(loginPage.getErrorMessage(), expectedMessage, "Incorrect error message for invalid login");
    }

    @DataProvider
    public Object[][] validLoginData() {

        return new Object[][]{
                {new User("problem_user", password), "Products"},
                {new User("performance_glitch_user", password), "Products"},
                {new User("error_user", password), "Products"},
                {new User("visual_user", password), "Products"},
                {UserFactory.withAdminPermission(), "Products"}
        };
    }

    @Story("Успешная авторизация")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Проверка успешной авторизации пользователя и перехода на страницу Products")
    @Test(dataProvider = "validLoginData")
    public void validUserCanLogin(User user, String expectedTitle) {
        loginPage.open();
        loginPage.login(user);
        assertTrue(productsPage.isProductsPageVisible());
        assertEquals(productsPage.getTitle(), expectedTitle);
    }
}
