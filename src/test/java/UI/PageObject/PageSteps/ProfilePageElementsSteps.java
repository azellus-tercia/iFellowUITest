package UI.PageObject.PageSteps;

import io.cucumber.java.en.Then;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static UI.PageObject.PageElements.ProfilePageElements.*;
import static utils.Configuration.getConfigurationValue;

public final class ProfilePageElementsSteps {
    public static String getDisplayedUsername() {
        return displayedUserName.getText().trim();
    }

    @Then("Пользователь {string} авторизован")
    @Step("Пользователь {username} авторизован")
    public static void checkUserIsLogged(String username) {
        Assertions.assertEquals(username, getDisplayedUsername(),
                "Пользователь " + getConfigurationValue("username") + " не авторизован.");
    }
}
