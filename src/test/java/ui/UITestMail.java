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
        profilePage.getButtonLogin().click(); //нажать кнопку "Войти"
        switchTo().frame($x("//iframe[@class=\"ag-popup__frame__layout__iframe\"]")); //изменить фрейм
        loginPage.enterLoginAndPassword(getProperty("login"), getProperty("password")); //ввести данные логин и пароль
    }

    @Test
    @DisplayName("Авторизация на сайте mail.ru - проверка логина почты")
    void checkLoginMail() {
        profilePage.getAuthorizedEmail().shouldHave(text(getProperty("login"))); //проверка соответствия логина на странице
    }

    @Test
    @DisplayName("Отправка письма. Проверка темы и количества писем")
    void checkWritingAndSendingEmail() {
        profilePage.getNavItemEmailsToYourself().click(); //перейти в панель "Письма себе"
        int emailsBefore = profilePage.getListEmails().size(); //получить количество писем на странице
        profilePage.getButtonWriteEmail().click(); //нажать на кнопку "Написать сообщение"
        emailWindowPage.fillFields(TO, TOPIC, TEXT); //заполнить поля письма
        emailWindowPage.sendEmail(); //отправить письмо
        int emailsAfter = profilePage.getListEmails().size(); //получить количество писем на странице
        assertEquals(1, (emailsAfter - emailsBefore)); //проверить что количество писем увеличилось на 1
        profilePage.checkTopicInEmailList(TOPIC); //проверка темы письма
    }

    @Test
    @DisplayName("Удаление письма. Проверка письма в корзине")
    void checkDeletingEmail() {
        profilePage.getNavItemEmailsToYourself().click(); //перейти в панель "Письма себе"
        Assumptions.assumeTrue(profilePage.getListEmails().size() > 0); //проверить предположение, что количество писем на странице > 0
        String topicEmail = profilePage.getListOfEmailsTopics().get(0).getText(); //получить тему верхнего письма из списка
        profilePage.choseAndDeleteEmail(0); //выбрать и удалить верхнее письмо
        profilePage.getNavItemBucket().click(); //перейти в корзину
        profilePage.checkTopicInEmailList(topicEmail); //проверка, что в корзине есть удаленное письмо
    }

    @Test
    @DisplayName("Сохранение письма в черновиках")
    void checkWritingAndSavingEmailInDrafts() {
        profilePage.getButtonWriteEmail().click(); //нажать на кнопку "Написать сообщение"
        emailWindowPage.fillFields(TO, TOPIC, TEXT); //заполнить поля письма
        emailWindowPage.saveEmailInDrafts(); //сохранить письмо в черновиках
        profilePage.getNavItemDrafts().click(); //перейти в черновики
        profilePage.checkTopicInEmailList(TOPIC); //проверка, что в черновиках есть сохраненное письмо
    }

    @Test
    @DisplayName("Удаление письма из черновиков. Проверка количества писем в черновиках")
    void checkDeletingEmailFromDrafts() {
        profilePage.getNavItemDrafts().click(); //перейти в черновики
        int emailsBefore = profilePage.getListEmails().size(); //получить количество писем на странице
        profilePage.choseAndDeleteEmail(0); //удалить письмо из черновиков
        int emailsAfter = profilePage.getListEmails().size(); //получить количество писем на странице
        assertEquals(1, (emailsBefore - emailsAfter)); //проверить что количество писем уменьшилось на 1
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }
}
