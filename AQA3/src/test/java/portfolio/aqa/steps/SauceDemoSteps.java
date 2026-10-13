package portfolio.aqa.steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import portfolio.aqa.pages.CartPage;
import portfolio.aqa.pages.CheckoutPage;
import portfolio.aqa.pages.LoginPage;
import portfolio.aqa.pages.ProductsPage;

// ai-assisted-solution
public class SauceDemoSteps {
    private final LoginPage loginPage = new LoginPage();
    private final ProductsPage productsPage = new ProductsPage();
    private final CartPage cartPage = new CartPage();
    private final CheckoutPage checkoutPage = new CheckoutPage();

    @Дано("пользователь открыл страницу авторизации SauceDemo")
    public void openLoginPage() {
        loginPage.openPage();
    }

    @Когда("пользователь вводит логин {string} и пароль {string}")
    public void login(String username, String password) {
        loginPage.loginAs(username, password);
    }

    @Тогда("открывается страница товаров")
    public void verifyProductsPage() {
        productsPage.shouldBeOpened();
    }

    @Когда("пользователь добавляет рюкзак Sauce Labs в корзину")
    public void addBackpackToCart() {
        productsPage.addBackpackToCart();
    }

    @Тогда("в счётчике корзины отображается {int} товар")
    public void verifyCartCounter(int expectedCount) {
        productsPage.shouldHaveCartItems(expectedCount);
    }

    @И("пользователь открывает корзину")
    public void openCart() {
        productsPage.openCart();
    }

    @И("в корзине находится рюкзак Sauce Labs")
    public void verifyBackpackInCart() {
        cartPage.shouldContainBackpack();
    }

    @И("пользователь переходит к оформлению заказа")
    public void proceedToCheckout() {
        cartPage.proceedToCheckout();
    }

    @Когда("пользователь заполняет имя {string}, фамилию {string} и индекс {string}")
    public void fillCustomerInformation(String firstName, String lastName, String postalCode) {
        checkoutPage.fillCustomerInformation(firstName, lastName, postalCode);
    }

    @И("пользователь продолжает оформление")
    public void continueCheckout() {
        checkoutPage.continueCheckout();
    }

    @И("пользователь подтверждает заказ")
    public void finishCheckout() {
        checkoutPage.finishCheckout();
    }

    @Тогда("отображается подтверждение успешного заказа")
    public void verifySuccessfulOrder() {
        checkoutPage.shouldShowSuccessfulOrder();
    }

    @Тогда("отображается ошибка авторизации {string}")
    public void verifyLoginError(String expectedMessage) {
        loginPage.shouldShowError(expectedMessage);
    }

    @Тогда("отображается ошибка обязательного поля {string}")
    public void verifyRequiredFieldError(String expectedMessage) {
        checkoutPage.shouldShowRequiredFieldError(expectedMessage);
    }

    @Тогда("для диагностики ожидается ошибочный заголовок каталога")
    public void intentionallyFailForAllureDiagnostics() {
        loginPage.shouldShowError("Этот текст намеренно неверный для учебной диагностики Allure");
    }
}

