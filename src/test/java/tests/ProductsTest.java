package tests;

import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProductsTest extends BaseTest {

    private final List<String> goodsList = List.of(
            "Sauce Labs Backpack", "Sauce Labs Bolt T-Shirt", "Sauce Labs Onesie",
            "Sauce Labs Bike Light", "Sauce Labs Fleece Jacket", "Test.allTheThings() T-Shirt (Red)"
    );
    private final String username = "standard_user";
    private final String password = "secret_sauce";

    @Test
    public void checkGoodsAdded() {
        loginPage.open();
        loginPage.login(username, password);
        assertTrue(productsPage.isProductsPageVisible());
        assertEquals(productsPage.getTitle(), "Products");

        for (String good : goodsList) {
            productsPage.addGoodsToCart(good);
        }

        assertTrue(productsPage.isShoppingCartVisible());
        assertEquals(productsPage.getShoppingCartBadge(), String.valueOf(goodsList.size()));
        assertEquals(productsPage.getCounterColor(), "rgb(226, 35, 26)");
    }
}
