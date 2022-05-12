package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public abstract class BasePage {
    protected static final String BASE_URL = "https://mail.ru/";

    public BasePage() {
        Configuration.timeout = Duration.of(10, ChronoUnit.SECONDS).toMillis();
    }

    protected void waitAndClick(SelenideElement element) {
        element.shouldBe(Condition.enabled).click();
    }
}
