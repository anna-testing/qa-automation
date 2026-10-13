package portfolio.aqa.pages.nocucumber;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CheckoutPageNoCucumber {
    /* manual-solution
       public void fill(String name) { $("input").setValue(name); $("button").click(); }
       Проблемы: заполнено только одно из трёх обязательных полей, а кнопка найдена неустойчиво.
    */

    // ai-assisted-solution
    public CheckoutPageNoCucumber fillCustomerInformation(String firstName, String lastName, String postalCode) {
        $("[data-test='firstName']").setValue(firstName);
        $("[data-test='lastName']").setValue(lastName);
        $("[data-test='postalCode']").setValue(postalCode);
        return this;
    }

    public CheckoutPageNoCucumber continueCheckout() {
        $("[data-test='continue']").click();
        return this;
    }

    public CheckoutPageNoCucumber shouldShowRequiredFieldError(String expectedMessage) {
        $("[data-test='error']")
                .shouldBe(visible.because("валидация обязательных полей должна показать ошибку"))
                .shouldHave(exactText(expectedMessage)
                        .because("ошибка должна указывать первое незаполненное обязательное поле"));
        return this;
    }

    public CheckoutPageNoCucumber finishCheckout() {
        $("[data-test='finish']").click();
        return this;
    }

    public void shouldShowSuccessfulOrder() {
        $("[data-test='complete-header']")
                .shouldBe(visible.because("после оформления должно отображаться подтверждение"))
                .shouldHave(exactText("Thank you for your order!")
                        .because("подтверждение должно сообщать об успешном заказе"));
    }
}
