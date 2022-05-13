package ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProfilePage extends BasePage {
    private final SelenideElement writeEmail = $x("//*[text()='Написать письмо']");
    private final SelenideElement sendEmail = $x("//*[text()='Отправить']");
    private final SelenideElement saveInDrafts = $x("//*[text()='Сохранить']");
    private final SelenideElement cancel = $x("//*[text()='Отменить']");
    private final SelenideElement fieldTo = $x("//label[@class='container--zU301']");
    private final SelenideElement fieldTopic = $x("//input[@name='Subject']");
    private final SelenideElement fieldText = $x("//div[@role='textbox']");
    private final SelenideElement emailHasBeenSend = $x("//*[text()='Письмо отправлено']");
    private final SelenideElement closeWindow = $x("//span[@title='Закрыть']");
    private final SelenideElement countEmail = $x("//span[@class='badge__text']");
    private final SelenideElement emailsToYourself = $x("//*[text()='Письма себе']");
    private final SelenideElement bucket = $x("//*[text()='Корзина']");
    private final SelenideElement deleteEmail = $x("//span[@title='Удалить']");

    private final ElementsCollection listCheckBox = $$x("//div[@class='checkbox__box']");
    private final ElementsCollection listEmail = $$x("//div[@class='llc__background']");
    private final ElementsCollection listEmailsTopic = $$x("//span[@class='ll-sj__normal']");

    private static final String TO = "testuiselenide@mail.ru";
    private static final String TOPIC = "Тестовая тема письма " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    private static final String TEXT = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    //Нажать на кнопку "Написать письмо"
    public void clickWriteEmail() {
        writeEmail.click();
    }

    /*
        Заполнить форму письма
        Отправить
        Закрыть окно отправленного письма
        Проверить, что количество писем увеличилось на 1
    */
    public void sendAndCheckCountEmail() {
        //Переделать с помощью нового Page Object - EmailWindowPage
        int emailBefore = checkCountEmail();
        fieldTo.sendKeys(TO);
        fieldTopic.sendKeys(TOPIC);
        fieldText.sendKeys(TEXT);
        sendEmail.click();
        emailHasBeenSend.shouldBe(visible);
        closeWindow.click();

        int emailAfter = waitCountEmail(emailBefore);
        assertEquals(1, (emailAfter - emailBefore));
    }

    /*
        Проверить тему верхнего письма
    */
    public void checkTopicEmail() {
        listEmailsTopic.get(0).shouldHave(text(TOPIC));
    }

    public void choseAndDeleteEmail() {
        listEmail.get(0).hover();
        listCheckBox.get(0).click();
        deleteEmail.click();
        listEmailsTopic.get(0).shouldNotHave(text(TOPIC));
    }

    public void saveEmailInDrafts() {
        fieldTo.sendKeys(TO);
        fieldTopic.sendKeys(TOPIC);
        fieldText.sendKeys(TEXT);
        saveInDrafts.click();
        cancel.click();
        listEmailsTopic.get(0).shouldHave(text(TOPIC));
    }

    public void deleteEmailFromDrafts() {
        int countEmailsBefore = listEmail.size();
        choseAndDeleteEmail();
        int countEmailsAfter = listEmail.size();
        assertEquals(1, countEmailsBefore - countEmailsAfter);
    }

    private int checkCountEmail() {
        return Integer.parseInt(
                countEmail
                        .shouldBe(visible)
                        .getText());
    }

    private int waitCountEmail(int emailsBefore) {
        for (int i = 0; i < 10; i++) {
            int emailAfter = checkCountEmail();

            //добавить try catch с логгером
            if (emailAfter > emailsBefore) {
                return emailAfter;
            } else {
                sleep(500);
            }
        }
        return emailsBefore;
    }

    //Перейти в "Письма себе"
    public void moveToYourself() {
        emailsToYourself.click();
    }

    //Перейти в корзину
    public void moveToBucket() {
        bucket.click();
    }

    public void moveToDrafts() {

    }

}
