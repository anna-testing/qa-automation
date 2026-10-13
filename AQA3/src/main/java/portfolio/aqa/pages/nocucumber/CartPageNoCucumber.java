package portfolio.aqa.pages.nocucumber;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CartPageNoCucumber {
    /* manual-solution
       public void next() { $(".btn").click(); }
       Проблема: общий класс .btn соответствует нескольким кнопкам корзины.
    */

    // ai-assisted-solution
    public CartPageNoCucumber shouldContainBackpack() {
        $("[data-test='inventory-item-name']")
                .shouldBe(visible.because("добавленный товар должен отображаться в корзине"))
                .shouldHave(exactText("Sauce Labs Backpack")
                        .because("в корзине должен находиться выбранный рюкзак"));
        return this;
    }

    public void proceedToCheckout() {
        $("[data-test='checkout']").click();
    }
}

