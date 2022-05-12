package ui;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import ui.pages.LoginPage;
import ui.pages.ProfilePage;

import static com.codeborne.selenide.Selenide.closeWebDriver;

@TestMethodOrder(OrderAnnotation.class)
public class TestMail {
    public static final String LOGIN = "testuiselenide@mail.ru";
    public static final String PASSWORD = "i21)IvuapSAT";

    private static LoginPage loginPage;
    private static ProfilePage profilePage;

    @BeforeAll
    static void setUp() {
        loginPage = new LoginPage();
        profilePage = new ProfilePage();
    }

    //1) Авторизация на сайте mail.ru - проверить, что в верхнем правом углу записан логин почтового ящика
    @Test
    @Order(1)
    void checkLoginMail() {
        loginPage.enterLoginAndPassword(LOGIN, PASSWORD);
        loginPage.checkEmail(LOGIN);
    }

    //2) Написать письмо и отправить себе (проверить, что количество писем увеличилось на 1, добавлено письмо с темой, которая заполнена при отправлении)
    @Test
    @Order(2)
    void checkWritingAndSendingEmail() {
        profilePage.clickWriteEmail();
        profilePage.sendAndCheckCountEmail();
        profilePage.moveToYourself();
        profilePage.checkTopicEmail();
    }

    //3) Удалить одно письмо (проверить, что во входящих нет удалённого письма и оно появилось в корзине)
    @Test
    @Order(3)
    void checkDeletingEmail() {
        profilePage.choseAndDeleteEmail();
        profilePage.moveToBucket();
        profilePage.checkTopicEmail();
    }

    //4) Написать письмо и сохранить его в черновиках (не отправлять)
    @Test
    @Order(4)
    void checkWritingAndSavingEmailInDrafts() {

    }


    //5) Удалить одно письмо из черновиков (проверить, что количество черновиков уменьшилось на 1)
    @Test
    @Order(5)
    void checkDeletingEmailFromDrafts() {

    }

    @AfterAll
    static void tearDown() {
        closeWebDriver();
    }
}
