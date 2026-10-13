package portfolio.aqa.pages;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

// ai-assisted-solution
public class ProductsPage {
    public ProductsPage shouldBeOpened() {
        $("[data-test='title']")
                .shouldBe(visible.because("заголовок каталога должен отображаться"))
                .shouldHave(exactText("Products").because("после входа должна открыться страница товаров"));
        return this;
    }

    public ProductsPage addBackpackToCart() {
        $("[data-test='add-to-cart-sauce-labs-backpack']").click();
        return this;
    }

    public ProductsPage shouldHaveCartItems(int expectedCount) {
        $("[data-test='shopping-cart-badge']")
                .shouldBe(visible.because("счётчик корзины должен появиться"))
                .shouldHave(exactText(String.valueOf(expectedCount))
                        .because("счётчик должен соответствовать числу товаров"));
        return this;
    }

    public void openCart() {
        $("[data-test='shopping-cart-link']").click();
    }
}

