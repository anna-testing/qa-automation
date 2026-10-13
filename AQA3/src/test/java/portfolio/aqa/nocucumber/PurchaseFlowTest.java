package portfolio.aqa.nocucumber;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import portfolio.aqa.pages.nocucumber.CartPageNoCucumber;
import portfolio.aqa.pages.nocucumber.CheckoutPageNoCucumber;
import portfolio.aqa.pages.nocucumber.LoginPageNoCucumber;
import portfolio.aqa.pages.nocucumber.ProductsPageNoCucumber;

@DisplayName("Покупка товара без Cucumber")
class PurchaseFlowTest extends BaseUiTest {
    private final LoginPageNoCucumber loginPage = new LoginPageNoCucumber();
    private final ProductsPageNoCucumber productsPage = new ProductsPageNoCucumber();
    private final CartPageNoCucumber cartPage = new CartPageNoCucumber();
    private final CheckoutPageNoCucumber checkoutPage = new CheckoutPageNoCucumber();

    @Test
    @DisplayName("Пользователь авторизуется, добавляет рюкзак и успешно оформляет заказ")
    void standardUserCanCompletePurchase() {
        loginPage.openPage().loginAs("standard_user", "secret_sauce");
        productsPage.shouldBeOpened()
                .addBackpackToCart()
                .shouldHaveCartItems(1)
                .openCart();
        cartPage.shouldContainBackpack().proceedToCheckout();
        checkoutPage.fillCustomerInformation("Anna", "Tester", "630000")
                .continueCheckout()
                .finishCheckout()
                .shouldShowSuccessfulOrder();
    }
}

