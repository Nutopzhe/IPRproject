package ui;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.*;
import ui.pages.EmailWindowPage;
import ui.pages.LoginPage;
import ui.pages.ProfilePage;
import ui.pojo.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.*;

public class UITestMail {

    private LoginPage loginPage = new LoginPage();
    private ProfilePage profilePage = new ProfilePage();
    private EmailWindowPage emailWindowPage = new EmailWindowPage();
    private User user = new User();

    @BeforeEach
    void setUp() {
        open(user.getUrl());
        WebDriverRunner.getWebDriver().manage().window().maximize();
        profilePage.getButtonLogin().click(); //нажать кнопку "Войти"
        switchTo().frame(loginPage.getIFrame()); //изменить фрейм
        loginPage.enterLoginAndPassword(user); //ввести данные логин и пароль
    }

    @Test
    @DisplayName("Авторизация на сайте mail.ru - проверка логина почты")
    void checkLoginMail() {
        profilePage.getAuthorizedEmail().shouldHave(text(user.getLogin())); //проверка соответствия логина на странице
    }

    @Test
    @DisplayName("Отправка письма. Проверка темы и количества писем")
    void checkWritingAndSendingEmail() {
        String to = "testuiselenide@mail.ru";
        String topic = "Тестовая тема письма " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String text = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        profilePage.getNavItemEmailsToYourself().click(); //перейти в панель "Письма себе"
        int emailsBefore = profilePage.getListEmails().size(); //получить количество писем на странице
        profilePage.getButtonWriteEmail().click(); //нажать на кнопку "Написать сообщение"

        emailWindowPage.fillFields(to, topic, text); //заполнить поля письма
        emailWindowPage.sendEmail(); //отправить письмо
        sleep(1000);

        int emailsAfter = profilePage.getListEmails().size(); //получить количество писем на странице
        assertEquals(1, (emailsAfter - emailsBefore)); //проверить что количество писем увеличилось на 1
        profilePage.checkTopicInEmailList(topic); //проверка темы письма
    }

    @Test
    @DisplayName("Удаление письма. Проверка письма в корзине")
    void checkDeletingEmail() {
        profilePage.getNavItemEmailsToYourself().click(); //перейти в панель "Письма себе"

        assumeTrue(profilePage.getListEmails().size() > 0,
                "На странице нет писем для удаления"); //проверить предположение, что количество писем на странице > 0

        String topicEmail = profilePage.getListOfEmailsTopics().get(0).getText(); //получить тему верхнего письма из списка
        profilePage.choseAndDeleteEmail(0); //выбрать и удалить верхнее письмо
        sleep(1000);

        profilePage.getNavItemBucket().click(); //перейти в корзину
        profilePage.checkTopicInEmailList(topicEmail); //проверка, что в корзине есть удаленное письмо
    }

    @Test
    @DisplayName("Сохранение письма в черновиках")
    void checkWritingAndSavingEmailInDrafts() {
        String to = "testuiselenide@mail.ru";
        String topic = "Тестовая тема письма " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String text = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        profilePage.getButtonWriteEmail().click(); //нажать на кнопку "Написать сообщение"
        emailWindowPage.fillFields(to, topic, text); //заполнить поля письма
        emailWindowPage.saveEmailInDrafts(); //сохранить письмо в черновиках

        profilePage.getNavItemDrafts().click(); //перейти в черновики
        profilePage.checkTopicInEmailList(topic); //проверка, что в черновиках есть сохраненное письмо
    }

    @Test
    @DisplayName("Удаление письма из черновиков. Проверка количества писем в черновиках")
    void checkDeletingEmailFromDrafts() {
        profilePage.getNavItemDrafts().click(); //перейти в черновики

        int emailsBefore = profilePage.getListEmails().size(); //получить количество писем на странице
        profilePage.choseAndDeleteEmail(0); //удалить письмо из черновиков
        sleep(1000);

        int emailsAfter = profilePage.getListEmails().size(); //получить количество писем на странице
        assertEquals(1, (emailsBefore - emailsAfter)); //проверить что количество писем уменьшилось на 1
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }
}
