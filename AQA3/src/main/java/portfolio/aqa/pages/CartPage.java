package portfolio.aqa.pages;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

// ai-assisted-solution
public class CartPage {
    public CartPage shouldContainBackpack() {
        $("[data-test='inventory-item-name']")
                .shouldBe(visible.because("товар должен отображаться в корзине"))
                .shouldHave(exactText("Sauce Labs Backpack")
                        .because("в корзине должен быть выбранный рюкзак"));
        return this;
    }

    public void proceedToCheckout() {
        $("[data-test='checkout']").click();
    }
}

