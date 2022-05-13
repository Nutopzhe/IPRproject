package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage extends BasePage {
    private final SelenideElement openLogin = $x("//*[text()='Войти']");
    private final SelenideElement loginIFrame = $x("//iframe[@class=\"ag-popup__frame__layout__iframe\"]");
    private final SelenideElement loginBox = $(byName("username"));
    private final SelenideElement passwordBox = $(byName("password"));
    private final SelenideElement loggedInEmail = $x("//span[@class=\"ph-project__user-name svelte-1hiqrvn\"]");

    public LoginPage() {
        open(BASE_URL);
        WebDriverRunner.getWebDriver().manage().window().maximize();
        waitAndClick(openLogin);
    }

    public void enterLoginAndPassword(String login, String password) {
        switchTo().frame(loginIFrame);
        $(loginBox).setValue(login).pressEnter();
        $(passwordBox).setValue(password).pressEnter();
    }

    public void checkEmail(String login) {
        loggedInEmail.shouldHave(Condition.text(login));
    }
}
