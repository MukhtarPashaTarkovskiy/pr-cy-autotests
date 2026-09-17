package tests;

import enums.TitleNaming;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import user.UserFactory;
import java.util.List;

import static org.testng.Assert.*;

@Epic("Корзина")
@Feature("Работа с товарами в корзине")
public class CartTest extends BaseTest {
    private final List<String> goodsList = List.of(
            "Sauce Labs Backpack", "Sauce Labs Bolt T-Shirt", "Sauce Labs Onesie",
            "Sauce Labs Bike Light", "Sauce Labs Fleece Jacket", "Test.allTheThings() T-Shirt (Red)"
    );

    @Story("Отображение добавленных товаров в корзине")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка отображения в корзине всех ранее добавленных товаров")
    @Test
    public void checkGoodsInCart() {
        SoftAssert soft = new SoftAssert();
        loginPage.open();
        loginPage.login(UserFactory.withAdminPermission());
        assertTrue(productsPage.isProductsPageVisible());
        assertEquals(productsPage.getTitle(), TitleNaming.PRODUCTS.getDisplayName());

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
