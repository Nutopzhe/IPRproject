package ui;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import ui.pages.EmailWindowPage;
import ui.pages.LoginPage;
import ui.pages.ProfilePage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ui.utils.Config.*;

/*
    + 1 - логин, пароль в проперти - зашифровать
    + 2 - убрать статик Page Objects (сразу проинициализировать)
    3 - убрать очередность, где необходимо дописать Assume
    + 4 - BASE_URL - убрать из BasePage
    5 - BasePage - убрать задержку в кофниг файл Selenide
    + 6 - waitAndClick убрать
    + 7 - заменить поля на методы в PO SelenideElement
    8 - добавить комментарии к действиям
    + 9 - sendAndCheckCountEmail убрать из PageObject
    + 10 - choseAndDeleteEmail передать аргумент - индекс письма
    + 11 - waitCountEmail убрать цикл ожидания, добавить sleep
 */

@TestMethodOrder(OrderAnnotation.class)
public class UITestMail {

    private final String TO = "testuiselenide@mail.ru";
    private final String TOPIC = "Тестовая тема письма " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    private final String TEXT = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    private final LoginPage loginPage = new LoginPage();
    private final ProfilePage profilePage = new ProfilePage();
    private final EmailWindowPage emailWindowPage = new EmailWindowPage();

    @BeforeEach
    void setUp() {
        open(getProperty("url"));
        WebDriverRunner.getWebDriver().manage().window().maximize();
        profilePage.getButtonLogin().click();
        switchTo().frame($x("//iframe[@class=\"ag-popup__frame__layout__iframe\"]"));
        loginPage.enterLoginAndPassword(getProperty("login"), getProperty("password"));
    }

    @Test
    @DisplayName("Авторизация на сайте mail.ru - проверка логина почты")
    void checkLoginMail() {
        profilePage.getAuthorizedEmail().shouldHave(text(getProperty("login")));
    }

    @Test
    @DisplayName("Отправка письма. Проверка темы и количества писем")
    void checkWritingAndSendingEmail() {
        int emailsBefore = profilePage.getEmailCount();
        profilePage.getButtonWriteEmail().click();
        emailWindowPage.fillFields(TO, TOPIC, TEXT);
        emailWindowPage.sendEmail();
        int emailsAfter = profilePage.getEmailCount();
        assertEquals(1, (emailsAfter - emailsBefore));
        profilePage.getNavItemEmailsToYourself().click();

        //Проверка темы письма
        profilePage.checkTopicInEmailList(TOPIC);
    }

    @Test
    @DisplayName("Удаление письма. Проверка письма в корзине")
    void checkDeletingEmail() {
        profilePage.getNavItemEmailsToYourself().click();
        String topicEmail = profilePage.getListOfEmailsTopics().get(0).getText();
        profilePage.choseAndDeleteEmail(0);
        profilePage.getNavItemBucket().click();

        //Проверка темы письма в корзине
        profilePage.checkTopicInEmailList(topicEmail);
    }

    @Test
    @DisplayName("Сохранение письма в черновиках")
    void checkWritingAndSavingEmailInDrafts() {
        profilePage.getButtonWriteEmail().click();
        emailWindowPage.fillFields(TO, TOPIC, TEXT);
        emailWindowPage.saveEmailInDrafts();
        profilePage.getNavItemDrafts().click();

        //Проверка темы письма
        profilePage.checkTopicInEmailList(TOPIC);
    }

    @Test
    @DisplayName("Удаление письма из черновиков. Проверка количества писем в черновиках")
    void checkDeletingEmailFromDrafts() {
        profilePage.getNavItemDrafts().click();
        profilePage.deleteEmailFromDrafts();
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }
}
