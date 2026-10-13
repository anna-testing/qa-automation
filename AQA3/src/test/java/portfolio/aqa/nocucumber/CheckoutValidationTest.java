package portfolio.aqa.nocucumber;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import portfolio.aqa.pages.nocucumber.CartPageNoCucumber;
import portfolio.aqa.pages.nocucumber.CheckoutPageNoCucumber;
import portfolio.aqa.pages.nocucumber.LoginPageNoCucumber;
import portfolio.aqa.pages.nocucumber.ProductsPageNoCucumber;

@DisplayName("Валидация оформления заказа без Cucumber")
class CheckoutValidationTest extends BaseUiTest {
    @Test
    @DisplayName("Система не продолжает оформление с пустыми обязательными полями")
    void checkoutRequiresCustomerInformation() {
        new LoginPageNoCucumber().openPage().loginAs("standard_user", "secret_sauce");
        new ProductsPageNoCucumber().shouldBeOpened().addBackpackToCart().openCart();
        new CartPageNoCucumber().proceedToCheckout();

        new CheckoutPageNoCucumber().continueCheckout()
                .shouldShowRequiredFieldError("Error: First Name is required");
    }
}
