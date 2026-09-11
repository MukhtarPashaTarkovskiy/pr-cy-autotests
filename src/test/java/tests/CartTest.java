package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.List;

import static org.testng.Assert.*;

public class CartTest extends BaseTest {
    private final String username = "standard_user";
    private final String password = "secret_sauce";
    private final List<String> goodsList = List.of(
            "Sauce Labs Backpack", "Sauce Labs Bolt T-Shirt", "Sauce Labs Onesie",
            "Sauce Labs Bike Light", "Sauce Labs Fleece Jacket", "Test.allTheThings() T-Shirt (Red)"
    );

    @Test
    public void checkGoodsInCart() {
        SoftAssert soft = new SoftAssert();
        loginPage.open();
        loginPage.login(username, password);
        assertTrue(productsPage.isProductsPageVisible());
        assertEquals(productsPage.getTitle(), "Products");

        for (String good : goodsList) {
            productsPage.addGoodsToCart(good);
        }

        productsPage.switchToCart();
        List<String> actualGoods = cartPage.getProductsNames();
        soft.assertFalse(actualGoods.isEmpty());
        soft.assertEquals(actualGoods.size(), goodsList.size());
        soft.assertEquals(actualGoods, goodsList);
        soft.assertAll();
    }
}
