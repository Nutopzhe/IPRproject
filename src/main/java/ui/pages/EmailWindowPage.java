package ui.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class EmailWindowPage extends BasePage {

    //Метод заполнения полей письма
    public void fillFields(String to, String topic, String text) {
        getFieldTo().sendKeys(to);
        getFieldTopic().sendKeys(topic);
        getFieldText().sendKeys(text);
    }

    //Метод отправки письма
    public void sendEmail() {
        getButtonSendEmail().shouldBe(visible).click();
        getNotificationEmailHasBeSend().shouldBe(visible);
        getButtonCloseWindow().shouldBe(visible).click();
    }

    //Метод сохранения письма в черновиках
    public void saveEmailInDrafts() {
        getButtonSaveInDrafts().shouldBe(visible).click();
        getButtonCancel().shouldBe(visible). click();
    }

    //Получить поле "Кому"
    private SelenideElement getFieldTo() {
        return $x("//label[@class='container--zU301']");
    }

    //Получить поле "Тема"
    private SelenideElement getFieldTopic() {
        return $x("//input[@name='Subject']");
    }

    //Получить поле "Текст"
    private SelenideElement getFieldText() {
        return $x("//div[@role='textbox']");
    }

    //Получить кнопку "Отправить письмо"
    private SelenideElement getButtonSendEmail() {
        return $x("//*[text()='Отправить']");
    }

    //Получить текст уведомления об отправке письма
    private SelenideElement getNotificationEmailHasBeSend() {
        return $x("//*[text()='Письмо отправлено']");
    }

    //Получить кнопку "Закрыть"
    private SelenideElement getButtonCloseWindow() {
        return $x("//span[@title='Закрыть']");
    }

    //Получить кнопку "Сохранить в черновиках"
    private SelenideElement getButtonSaveInDrafts() {
        return $x("//*[text()='Сохранить']");
    }

    //Получить кнопку "Отмена"
    private SelenideElement getButtonCancel() {
        return $x("//*[text()='Отменить']");
    }
}
