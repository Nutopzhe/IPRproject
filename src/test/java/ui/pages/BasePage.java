package ui.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static com.codeborne.selenide.Condition.*;

public abstract class BasePage {
    protected static final String BASE_URL = "https://mail.ru/";

    public BasePage() {
        Configuration.timeout = Duration.of(10, ChronoUnit.SECONDS).toMillis();
    }

    protected void waitAndClick(SelenideElement element) {
        element.shouldBe(exist, visible).click();
    }
}
