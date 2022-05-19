package ui.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;

public class EmailWindowPage extends BasePage {
    private final SelenideElement buttonCancel = $x("//*[text()='Отменить']");

    //Метод заполнения полей письма
    public void fillFields(String to, String topic, String text) {
        getFieldTo().sendKeys(to);
        getFieldTopic().sendKeys(topic);
        getFieldText().sendKeys(text);
    }

    public void sendEmail() {
        getButtonSendEmail().shouldBe(exist, visible).click();
        getNotificationEmailHasBeSend().shouldBe(visible);
        getButtonCloseWindow().shouldBe(exist, visible).click();
        sleep(1000);
    }

    public void saveEmailInDrafts() {
        getButtonSaveInDrafts().shouldBe(exist, visible).click();
        getButtonCancel().shouldBe(exist, visible). click();
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
