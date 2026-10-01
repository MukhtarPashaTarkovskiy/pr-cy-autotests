package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.PropertyReader;
import utils.TestListener;
import java.time.Duration;

@Listeners({AllureTestNg.class,TestListener.class})
public class BaseTest {
    WebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    String password = PropertyReader.getProperty("sausedemo.password");
    String wrongPassword = PropertyReader.getProperty("sausedemo.wrongPassword");

    @Parameters("browser")
    @BeforeMethod
    public void openBrowser(@Optional("chrome") String browser, ITestContext context) {
        if(browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
            wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("-private");
            driver = new FirefoxDriver(options);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
            wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        }
        context.setAttribute("driver", driver);
        loginPage = new LoginPage(driver, wait);
        productsPage = new ProductsPage(driver, wait);
        cartPage = new CartPage(driver, wait);
    }

    @Step("Закрываем браузер")
    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }
}
