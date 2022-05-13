package ui.pages;

import com.codeborne.selenide.SelenideElement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$x;

public class EmailWindowPage extends BasePage {
    private final SelenideElement fieldTo = $x("//label[@class='container--zU301']");
    private final SelenideElement fieldTopic = $x("//input[@name='Subject']");
    private final SelenideElement fieldText = $x("//div[@role='textbox']");

    private static final String TO = "testuiselenide@mail.ru";
    private static final String TOPIC = "Тестовая тема письма " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    private static final String TEXT = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
}
