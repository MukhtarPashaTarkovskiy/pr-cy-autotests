package tests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    private String password = "secret_sauce";
    private String username = "standard_user";
    private String wrongPassword = "wrongPassword";

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][]{
                {
                        "12345", wrongPassword,
                        "Epic sadface: Username and password do not match any user in this service"
                },
                {
                        username, wrongPassword,
                        "Epic sadface: Username and password do not match any user in this service"
                },
                {
                        "", password,
                        "Epic sadface: Username is required"
                },
                {
                        username, "",
                        "Epic sadface: Password is required"
                },
                {
                        "locked_out_user", password,
                        "Epic sadface: Sorry, this user has been locked out."
                },
                {
                        "", "",
                        "Epic sadface: Username is required"
                }
        };
    }

    @Test(dataProvider = "invalidLoginData")
    public void invalidLoginShowsErrorMessage(String username, String password, String expectedMessage) {
        loginPage.open();
        loginPage.login(username, password);
        assertTrue(loginPage.isErrorMessageVisible(), "Error message is not visible");
        assertEquals(loginPage.getErrorMessage(), expectedMessage, "Incorrect error message for invalid login");
    }

    @DataProvider
    public Object[][] validLoginData() {

        return new Object[][]{
                {"problem_user", password, "Products"},
                {"performance_glitch_user", password, "Products"},
                {"error_user", password, "Products"},
                {"visual_user", password, "Products"},
                {"standard_user", password, "Products"}
        };
    }

    @Test(dataProvider = "validLoginData")
    public void validUserCanLogin(String username, String password, String expectedTitle) {
        loginPage.open();
        loginPage.login(username, password);
        assertTrue(productsPage.isProductsPageVisible());
        assertEquals(productsPage.getTitle(), expectedTitle);
    }
}
