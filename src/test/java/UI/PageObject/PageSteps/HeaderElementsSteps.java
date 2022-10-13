package UI.PageObject.PageSteps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;

import static UI.PageObject.PageElements.HeaderElements.*;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public final class HeaderElementsSteps {
    public void logout() {
        userHeaderMenuButton.click();
        logOutButton.click();
    }

    @When("Открыть страницу пользователя")
    @Step("Открыть страницу пользователя")
    public static void goToUserPage() {
        userHeaderMenuButton.click();
        profileButton.click();
    }

    @When("Открыть проект {string}")
    @Step("Открыть проект {projectName}")
    public static void openProject(String projectName) {
        projectsButton.click();
        $(byText(projectName)).click();
    }

    @And("Найти задачу под номером {string}")
    @Step("Найти задачу под номером {projectName}")
    public static void searchTask(String projectName) {
        searchField.setValue(projectName + "\n");
    }

    @And("Найти и открыть созданную задачу {string}")
    @Step("Найти и открыть созданную задачу {projectName}")
    public static void searchCreatedTask(String projectName) {
        searchTask(ProjectPageElementsSteps.getTaskTestNumber(projectName));
    }

    @Step("Переход на главную страницу")
    public void clickMainLogo() {
        headerLogo.click();
    }
}
