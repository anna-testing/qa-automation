package portfolio.aqa.pages.nocucumber;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProductsPageNoCucumber {
    /* manual-solution
       public void add() { $("button").click(); }
       public void check() { $("span").shouldBe(visible); }
       Проблемы: локаторы выбирают первый button/span и ломаются при перестановке элементов.
    */

    // ai-assisted-solution
    public ProductsPageNoCucumber shouldBeOpened() {
        $("[data-test='title']")
                .shouldBe(visible.because("заголовок каталога должен отображаться после входа"))
                .shouldHave(exactText("Products").because("должна открыться страница товаров"));
        return this;
    }

    public ProductsPageNoCucumber addBackpackToCart() {
        $("[data-test='add-to-cart-sauce-labs-backpack']").click();
        return this;
    }

    public ProductsPageNoCucumber shouldHaveCartItems(int expectedCount) {
        $("[data-test='shopping-cart-badge']")
                .shouldBe(visible.because("счётчик корзины должен появиться после добавления товара"))
                .shouldHave(exactText(String.valueOf(expectedCount))
                        .because("счётчик корзины должен соответствовать числу добавленных товаров"));
        return this;
    }

    public void openCart() {
        $("[data-test='shopping-cart-link']").click();
    }
}

