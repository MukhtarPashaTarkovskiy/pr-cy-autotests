package tests;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    private String password = "secret_sauce";
    private String username = "standard_user";
    private String wrongPassword = "wrongPassword";

    @Test
    public void invalidLoginShowsErrorMessage() {
        loginPage.open();
        loginPage.login("12345", wrongPassword);
        String expectedMessage = "Epic sadface: Username and password do not match any user in this service";
        assertTrue(loginPage.isErrorMessageVisible(), "Error message is not visible");
        assertEquals(loginPage.getErrorMessage(), expectedMessage, "Incorrect error message for invalid login");
    }

    @Test
    public void invalidPasswordShowsErrorMessage() {
        loginPage.open();
        loginPage.login(username, wrongPassword);
        assertTrue(loginPage.isErrorMessageVisible(), "Error message is not visible");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username and password do not match any user in this service",
                "Incorrect error message for invalid password");
    }

    @Test
    public void emptyUsernameShowsErrorMessage() {
        loginPage.open();
        loginPage.login("", password);
        assertTrue(loginPage.isErrorMessageVisible(), "Error message is not visible");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required",
                "Incorrect error message for empty username");
    }

    @Test
    public void emptyPasswordShowsErrorMessage() {
        loginPage.open();
        loginPage.login(username, "");
        assertTrue(loginPage.isErrorMessageVisible(), "Error message is not visible");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required",
                "Incorrect error message for empty password");
    }

    @Test
    public void correctUsetTest() {
        loginPage.open();
        loginPage.login(username, password);
        assertTrue(productsPage.isProductsPageVisible(), "Products page is not visible");
        assertEquals(productsPage.getTitle(), "Products", "Incorrect title");
    }

    @Test
    public void lockedOutUserShowsErrorMessage() {
        loginPage.open();
        loginPage.login("locked_out_user", password);
        assertTrue(loginPage.isErrorMessageVisible());
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Sorry, this user has been locked out.",
                "Incorrect error message for locked out user");
    }

    @Test
    public void problemUserCanLogin() {
        loginPage.open();
        loginPage.login("problem_user", password);
        assertTrue(productsPage.isProductsPageVisible(), "Error message is not visible");
        assertEquals(productsPage.getTitle(), "Products", "Incorrect title");
    }

    @Test
    public void performanceGlitchUserCanLogin() {
        loginPage.open();
        loginPage.login("performance_glitch_user", password);
        assertTrue(productsPage.isProductsPageVisible(), "Error message is not visible");
        assertEquals(productsPage.getTitle(), "Products", "Incorrect title");
    }

    @Test
    public void errorUserCanLogin() {
        loginPage.open();
        loginPage.login("error_user", password);
        assertTrue(productsPage.isProductsPageVisible(), "Error message is not visible");
        assertEquals(productsPage.getTitle(), "Products", "Incorrect title");
    }

    @Test
    public void visualUserCanLogin() {
        loginPage.open();
        loginPage.login("visual_user", password);
        assertTrue(productsPage.isProductsPageVisible(), "Error message is not visible");
        assertEquals(productsPage.getTitle(), "Products", "Incorrect title");
    }

    @Test
    public void emptyUsernameAndPasswordShowsErrorMessage() {
        loginPage.open();
        loginPage.login("", "");
        assertTrue(loginPage.isErrorMessageVisible(), "Error message is not visible");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required",
                "Incorrect error message for empty username");
    }
}
