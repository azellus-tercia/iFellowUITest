package UI.PageObject.PageSteps;

import static UI.PageObject.PageElements.ProjectPageElements.*;
import com.codeborne.selenide.Condition;
import UI.ProjectUtils.Waiters;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public final class ProjectPageElementsSteps implements Waiters {
    public static String getProjectName() {
        return projectName.getAttribute("title");
    }

    @Step("Проверить, что открытый проект называется {projectName}")
    public static void checkProjectName(String projectName) {
        Assertions.assertEquals(projectName, getProjectName(), "Проект " + projectName + " не найден.");
    }

    @Step("Открыть список задач")
    public static void clickTasks() {
        tasksButton.click();
        filtersButton.shouldBe(Condition.visible);
    }

    @Step("Поменять фильтр отображения задач на {filtersType}")
    public static void changeFiltersTo(String filtersType) {
        filtersButton.click();
        $(byText(filtersType)).click();
    }

    public String waitForElementIsUpdated() {
        return waitFor(numberOfTasks, 1000);
    }

    public static String getNumberOfTasks() {
        return new ProjectPageElementsSteps().waitForElementIsUpdated().substring("1 из ".length());
    }

    @Step("Вывести общее кличество задач")
    public static void printNumberOfTasks() {
        System.out.println(getNumberOfTasks());
    }

    @Step("Нажать \"Создать задачу\" внизу экрана и выбрать \"Открыть в диалоговом окне\"")
    public static void newTaskWithDialogue() {
        createTaskButton.click();
        openInDialogueButton.shouldBe(Condition.visible);
        openInDialogueButton.click();
        taskCreationHeader.shouldBe(Condition.visible);
    }

    public static String getTaskTestNumber(String taskName) {
        String taskDescription = $x("//div[@class='issue-content-container' and span[contains(., '" + taskName + "')]]").getText();
        return taskDescription.substring(0, taskDescription.indexOf('\n'));
    }
}
