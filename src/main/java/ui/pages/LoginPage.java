package ui.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage extends BasePage {

    //Метод заполнения логина и пароля
    public void enterLoginAndPassword(String login, String password) {
        getFieldLogin().setValue(login).pressEnter();
        getFieldPassword().setValue(password).pressEnter();
    }

    //Получить поле "Логин"
    private SelenideElement getFieldLogin() {
        return $(byName("username"));
    }

    //Получить поле "Пароль"
    private SelenideElement getFieldPassword() {
        return $(byName("password"));
    }
}
