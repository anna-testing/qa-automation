package portfolio.aqa.pages;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

// ai-assisted-solution
public class CheckoutPage {
    public CheckoutPage fillCustomerInformation(String firstName, String lastName, String postalCode) {
        $("[data-test='firstName']").setValue(firstName);
        $("[data-test='lastName']").setValue(lastName);
        $("[data-test='postalCode']").setValue(postalCode);
        return this;
    }

    public CheckoutPage continueCheckout() {
        $("[data-test='continue']").click();
        return this;
    }

    public CheckoutPage shouldShowRequiredFieldError(String expectedMessage) {
        $("[data-test='error']")
                .shouldBe(visible.because("ошибка обязательного поля должна отображаться"))
                .shouldHave(exactText(expectedMessage)
                        .because("ошибка должна называть незаполненное обязательное поле"));
        return this;
    }

    public CheckoutPage finishCheckout() {
        $("[data-test='finish']").click();
        return this;
    }

    public void shouldShowSuccessfulOrder() {
        $("[data-test='complete-header']")
                .shouldBe(visible.because("подтверждение заказа должно отображаться"))
                .shouldHave(exactText("Thank you for your order!")
                        .because("заказ должен завершиться успешно"));
    }
}

