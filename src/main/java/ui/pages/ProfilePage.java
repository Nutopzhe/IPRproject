package ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class ProfilePage extends BasePage {

    //Проверка темы в списке писем по переданному значению
    public boolean checkTopicInEmailList(String value) {
        List<String> topics = getListOfEmailsTopics().texts();
        if (topics.contains(value)) {
            return true;
        } else
            throw new RuntimeException("В списке писем нет письма с темой " + value);
    }

    //Выбор и удаление письма по индексу
    public void choseAndDeleteEmail(int index) {
        getListEmails().get(index).hover();
        getListCheckBox().get(index).click();
        getButtonDeleteEmail().click();
    }

    //Получить кнопку "Написать письмо"
    public SelenideElement getButtonWriteEmail() {
        return $x("//*[text()='Написать письмо']");
    }

    //Получить кнопку 'Войти'
    public SelenideElement getButtonLogin() {
        return $x("//*[text()='Войти']");
    }

    //Получить кнопку "Удалить письмо"
    public SelenideElement getButtonDeleteEmail() {
        return $x("//span[@title='Удалить']");
    }

    //Получить авторизованный email адрес
    public SelenideElement getAuthorizedEmail() {
        return $x("//span[@class=\"ph-project__user-name svelte-1hiqrvn\"]");
    }

    //Получить навигационную панель "Письма себе"
    public SelenideElement getNavItemEmailsToYourself() {
        return $x("//*[text()='Письма себе']");
    }

    //Получить навигационную панель "Корзина""
    public SelenideElement getNavItemBucket() {
        return $x("//*[text()='Корзина']");
    }

    //Получить навигационную панель "Черновики"
    public SelenideElement getNavItemDrafts() {
        return $x("//*[text()='Черновики']");
    }

    //Получить список тем всех писем
    public ElementsCollection getListOfEmailsTopics() {
        return $$x("//span[@class='ll-sj__normal']");
    }

    //Получить список всех писем
    public ElementsCollection getListEmails() {
        return $$x("//div[@class='llc__background']");
    }

    //Получить список чек-боксов
    public ElementsCollection getListCheckBox() {
        return $$x("//div[@class='checkbox__box']");
    }
}
