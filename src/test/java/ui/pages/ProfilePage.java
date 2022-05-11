package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class ProfilePage {
    private final SelenideElement writeLetter = $x("//*[text()='Написать письмо']");
    private final SelenideElement sendLetter = $x("//*[text()='Отправить']");
    private final SelenideElement fieldTo = $x("//label[@class='container--zU301']");
    private final SelenideElement fieldTopic = $x("//input[@name='Subject']");
    private final SelenideElement fieldText = $x("//div[@role='textbox']");
    private final SelenideElement emailHasBeenSend = $x("//*[text()='Письмо отправлено']");
    private final SelenideElement countEmail = $x("//span[@class='badge__text']");

    private static final String TO = "testuiselenide@mail.ru";
    private static final String TOPIC = "Тестовая тема письма " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    private static final String TEXT = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    public void clickWriteEmail() {
        writeLetter.click();
    }

    public void sendAndCheckCountEmail() {
        int before = checkCountEmail();
        fieldTo.sendKeys(TO);
        fieldTopic.sendKeys(TOPIC);
        fieldText.sendKeys(TEXT);
        sendLetter.click();
        emailHasBeenSend.shouldBe(visible);

        int after = waitCountEmail(before);
        Assertions.assertEquals(1, (after - before));

        //добавлено письмо с темой, которая заполнена при отправлении - доделать
    }

    private int checkCountEmail() {
        return Integer.parseInt(
                countEmail
                        .shouldBe(visible)
                        .getText());
    }

    private int waitCountEmail(int before) {
        for (int i = 0; i < 10; i++) {
            int after = checkCountEmail();

            if (after > before) {
                return after;
            } else {
                sleep(500);
            }
        }
        return before;
    }

}
