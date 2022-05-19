package ui.pages;

import com.codeborne.selenide.Configuration;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public abstract class BasePage {

    //убрать в конце
    public BasePage() {
        Configuration.timeout = Duration.of(10, ChronoUnit.SECONDS).toMillis();
    }
}
