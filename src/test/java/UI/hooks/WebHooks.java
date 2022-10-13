package UI.hooks;

import UI.PageObject.PageSteps.HeaderElementsSteps;
import UI.PageObject.PageSteps.LogInElementsSteps;
import UI.ProjectUtils.CustomAllureSelenide;
import UI.ProjectUtils.ScreenshotOnFailure;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static utils.Configuration.getConfigurationValue;

@ExtendWith(ScreenshotOnFailure.class)
public abstract class WebHooks {
    @BeforeAll
    static void setUp() {
        Configuration.startMaximized = true;
        Selenide.open(getConfigurationValue("jiraUrl"));
        new LogInElementsSteps().authorization(getConfigurationValue("username"),
                getConfigurationValue("password"));
        SelenideLogger.addListener("AllureSelenide", new CustomAllureSelenide());
    }

    @BeforeEach
    public void logIn() {
        new HeaderElementsSteps().clickMainLogo();
    }

    @AfterAll
    static void closeWebBrowser() {
        new HeaderElementsSteps().logout();
        Selenide.closeWebDriver();
    }
}
