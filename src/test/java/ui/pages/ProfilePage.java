package ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;
import static ui.pages.EmailWindowPage.TOPIC;

public class ProfilePage extends BasePage {
    private final SelenideElement writeEmail = $x("//*[text()='Написать письмо']");
    private final SelenideElement countEmail = $x("//span[@class='badge__text']");
    private final SelenideElement emailsToYourself = $x("//*[text()='Письма себе']");
    private final SelenideElement bucket = $x("//*[text()='Корзина']");
    private final SelenideElement drafts = $x("//*[text()='Черновики']");
    private final SelenideElement deleteEmail = $x("//span[@title='Удалить']");

    private final ElementsCollection listCheckBox = $$x("//div[@class='checkbox__box']");
    private final ElementsCollection listEmail = $$x("//div[@class='llc__background']");
    private final ElementsCollection listEmailsTopic = $$x("//span[@class='ll-sj__normal']");

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
    public void sendAndCheckCountEmail(EmailWindowPage emailWindowPage) {
        //"Работает - не трогай" - мудрость джедая:)
        int emailBefore = checkCountEmail();
        emailWindowPage.fillAndSaveEmail();
        int emailAfter = waitCountEmail(emailBefore);
        assertEquals(1, (emailAfter - emailBefore));
    }

    //Проверить тему верхнего письма
    public void checkTopicEmail() {
        listEmailsTopic.get(0).shouldHave(text(TOPIC));
    }

    public void choseAndDeleteEmail() {
        listEmail.get(0).hover();
        listCheckBox.get(0).click();
        deleteEmail.click();
        listEmailsTopic.get(0).shouldNotBe(text(TOPIC));
    }

    public void deleteEmailFromDrafts() {
        sleep(1000);
        int countEmailsBefore = listEmail.size();
        choseAndDeleteEmail();
        int countEmailsAfter = listEmail.size();
        assertEquals(1, (countEmailsBefore - countEmailsAfter));
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
        waitAndClick(emailsToYourself);
    }

    //Перейти в корзину
    public void moveToBucket() {
        waitAndClick(bucket);
    }

    //Перейти в черновики
    public void moveToDrafts() {
        waitAndClick(drafts);
    }
}
