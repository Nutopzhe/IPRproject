package ui;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import ui.pages.EmailWindowPage;
import ui.pages.LoginPage;
import ui.pages.ProfilePage;

import static com.codeborne.selenide.Selenide.closeWebDriver;

@TestMethodOrder(OrderAnnotation.class)
public class TestMail {
    public static final String LOGIN = "testuiselenide@mail.ru";
    public static final String PASSWORD = "i21)IvuapSAT";

    private static LoginPage loginPage;
    private static ProfilePage profilePage;
    private static EmailWindowPage emailWindowPage;

    @BeforeAll
    static void setUp() {
        loginPage = new LoginPage();
        profilePage = new ProfilePage();
        emailWindowPage = new EmailWindowPage();
    }

    //1) Авторизация на сайте mail.ru - проверить, что в верхнем правом углу записан логин почтового ящика
    @Test
    @Order(1)
    @DisplayName("Авторизация на сайте mail.ru - проверка логина почты")
    void checkLoginMail() {
        loginPage.enterLoginAndPassword(LOGIN, PASSWORD);
        loginPage.checkEmail(LOGIN);
    }

    //2) Написать письмо и отправить себе (проверить, что количество писем увеличилось на 1, добавлено письмо с темой, которая заполнена при отправлении)
    @Test
    @Order(2)
    @DisplayName("Отправка письма. Проверка темы и количества писем")
    void checkWritingAndSendingEmail() {
        profilePage.clickWriteEmail();
        profilePage.sendAndCheckCountEmail(emailWindowPage);
        profilePage.moveToYourself();
        profilePage.checkTopicEmail();
    }

    //3) Удалить одно письмо (проверить, что во входящих нет удалённого письма и оно появилось в корзине)
    @Test
    @Order(3)
    @DisplayName("Удаление письма. Проверка письма в корзине")
    void checkDeletingEmail() {
        profilePage.choseAndDeleteEmail();
        profilePage.moveToBucket();
        profilePage.checkTopicEmail();
    }

    //4) Написать письмо и сохранить его в черновиках (не отправлять)
    @Test
    @Order(4)
    @DisplayName("Сохранение письма в черновиках")
    void checkWritingAndSavingEmailInDrafts() {
        profilePage.clickWriteEmail();
        emailWindowPage.saveEmailInDrafts();
        profilePage.checkTopicEmail();
    }


    //5) Удалить одно письмо из черновиков (проверить, что количество черновиков уменьшилось на 1)
    @Test
    @Order(5)
    @DisplayName("Удаление письма из черновиков. Проверка количества писем в черновиках")
    void checkDeletingEmailFromDrafts() {
        profilePage.moveToDrafts();
        profilePage.deleteEmailFromDrafts();
    }

    @AfterAll
    static void tearDown() {
        closeWebDriver();
    }
}
