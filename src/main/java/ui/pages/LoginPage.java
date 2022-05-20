package ui.pages;

import com.codeborne.selenide.SelenideElement;
import ui.pojo.User;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage extends BasePage {

    //Метод заполнения логина и пароля
    public void enterLoginAndPassword(User user) {
        getFieldLogin().shouldBe(visible).setValue(user.getLogin()).pressEnter();
        getFieldPassword().shouldBe(visible).setValue(user.getPassword()).pressEnter();
    }

    //Получить IFrame
    public SelenideElement getIFrame() {
        return $x("//iframe[@class=\"ag-popup__frame__layout__iframe\"]");
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
