package ui.pages;

import com.codeborne.selenide.SelenideElement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class EmailWindowPage extends BasePage {
    private final SelenideElement fieldTo = $x("//label[@class='container--zU301']");
    private final SelenideElement fieldTopic = $x("//input[@name='Subject']");
    private final SelenideElement fieldText = $x("//div[@role='textbox']");
    private final SelenideElement buttonSaveInDrafts = $x("//*[text()='Сохранить']");
    private final SelenideElement buttonCancel = $x("//*[text()='Отменить']");
    private final SelenideElement buttonSendEmail = $x("//*[text()='Отправить']");
    private final SelenideElement buttonCloseWindow = $x("//span[@title='Закрыть']");
    private final SelenideElement emailHasBeenSend = $x("//*[text()='Письмо отправлено']");

    public static final String TO = "testuiselenide@mail.ru";
    public static final String TOPIC = "Тестовая тема письма " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    public static final String TEXT = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    public void saveEmailInDrafts() {
        fillFields();
        saveInDrafts();
        cancel();
    }

    public void fillAndSaveEmail() {
        fillFields();
        sendEmail();
        emailHasBeenSend.shouldBe(visible);
        closeWindow();
    }

    private void fillFields() {
        fieldTo.sendKeys(TO);
        fieldTopic.sendKeys(TOPIC);
        fieldText.sendKeys(TEXT);
    }

    private void saveInDrafts() {
        waitAndClick(buttonSaveInDrafts);
    }

    private void cancel() {
        waitAndClick(buttonCancel);
    }

    private void sendEmail() {
        waitAndClick(buttonSendEmail);
    }

    private void closeWindow() {
        waitAndClick(buttonCloseWindow);
    }
}
