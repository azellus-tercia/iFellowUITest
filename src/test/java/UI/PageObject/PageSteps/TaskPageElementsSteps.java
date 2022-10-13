package UI.PageObject.PageSteps;

import UI.PageObject.PageElements.TaskPageElements;
import com.codeborne.selenide.Condition;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

public final class TaskPageElementsSteps {
    public static String getTaskStatus() {
        return TaskPageElements.taskStatus.getText();
    }

    @Then("Проверить, что статус задачи равен {string}")
    @Step("Проверить, что статус задачи равен {taskStatus}")
    public static void checkTaskStatus(String taskStatus) {
        Assertions.assertEquals(taskStatus, getTaskStatus(), "Начальный статус не равен '" + taskStatus + "'.");
    }

    @Then("Проверить, что у задачи есть статус")
    @Step("Проверить, что у задачи есть статус")
    public static void checkTaskStatusIsSet() {
        Assertions.assertTrue(getTaskStatus().length() > 0, "Статус задачи не задан.");
    }

    public static String getChangedTaskStatus() {
        String status = TaskPageElements.taskStatus.getText();
        return TaskPageElements.taskStatus.shouldNotHave(Condition.exactText(status)).getText();
    }

    @Then("Проверить, что новый статус задачи равен {string}")
    @Step("Проверить, что новый статус задачи равен {taskStatus}")
    public static void checkChangedTaskStatus(String taskStatus) {
        Assertions.assertEquals(taskStatus, getChangedTaskStatus(), "Статус не равен '" + taskStatus + "'.");
    }

    public static String getFixInVersion() {
        return TaskPageElements.taskFixInVersion.getText();
    }

    @And("Проверить, что задача должна быть исправлена в {string}")
    @Step("Проверить, что задача должна быть исправлена в {version}")
    public static void checkFixInVersion(String version) {
        Assertions.assertEquals(version, getFixInVersion(), "Версия для исправления не соответствует " + version);
    }

    @Then("Поменять статус задачи на \"В работе\"")
    @Step("Поменять статус задачи на \"В работе\"")
    public static void setStatusInProgress() {
        TaskPageElements.taskInProgressButton.click();
    }

    @Then("Поменять статус задачи на \"Решенные\"")
    @Step("Поменять статус задачи на \"Решенные\"")
    public static void setStatusResolved() {
        TaskPageElements.taskBusinessProcess.click();
        TaskPageElements.taskResolved.click();
        TaskPageElements.taskAccept.click();
    }

    @Then("Поменять статус задачи на \"Готово\"")
    @Step("Поменять статус задачи на \"Готово\"")
    public static void setStatusDone() {
        TaskPageElements.taskBusinessProcess.click();
        TaskPageElements.taskDone.click();
        TaskPageElements.taskAccept.click();
    }
}
