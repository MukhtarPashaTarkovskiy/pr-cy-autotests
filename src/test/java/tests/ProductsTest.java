package tests;

import enums.TitleNamiing;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import user.UserFactory;
import java.util.List;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Epic("Каталог товаров")
@Feature("Добавление товаров в корзину")
public class ProductsTest extends BaseTest {

    private final List<String> goodsList = List.of(
            "Sauce Labs Backpack", "Sauce Labs Bolt T-Shirt", "Sauce Labs Onesie",
            "Sauce Labs Bike Light", "Sauce Labs Fleece Jacket", "Test.allTheThings() T-Shirt (Red)"
    );

    @Story("Добавление товаров в корзину")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка добавления товаров в корзину, количества товаров и отображения счётчика корзины")
    @Test
    public void checkGoodsAdded() {
        loginPage.open();
        loginPage.login(UserFactory.withAdminPermission());
        assertTrue(productsPage.isProductsPageVisible());
        assertEquals(productsPage.getTitle(), TitleNamiing.PRODUCTES.getDisplayName());

        for (String good : goodsList) {
            productsPage.addGoodsToCart(good);
        }

        assertTrue(productsPage.isShoppingCartVisible());
        assertEquals(productsPage.getShoppingCartBadge(), String.valueOf(goodsList.size()));
        assertEquals(productsPage.getCounterColor(), TitleNamiing.CART_COUNTER_COLOR.getDisplayName());
    }
}
