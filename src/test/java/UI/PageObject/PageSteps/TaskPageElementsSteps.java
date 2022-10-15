package UI.PageObject.PageSteps;

import UI.PageObject.PageElements.TaskPageElements;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

public final class TaskPageElementsSteps {
    public static String getTaskStatus() {
        return TaskPageElements.taskStatus.getText();
    }

    @Step("Проверить, что статус задачи равен {taskStatus}")
    public static void checkTaskStatus(String taskStatus) {
        Assertions.assertEquals(taskStatus, getTaskStatus(), "Начальный статус не равен '" + taskStatus + "'.");
    }

    @Step("Проверить, что у задачи есть статус")
    public static void checkTaskStatusIsSet() {
        Assertions.assertTrue(getTaskStatus().length() > 0, "Статус задачи не задан.");
    }

    public static String getChangedTaskStatus() {
        String status = TaskPageElements.taskStatus.getText();
        return TaskPageElements.taskStatus.shouldNotHave(Condition.exactText(status)).getText();
    }

    @Step("Проверить, что новый статус задачи равен {taskStatus}")
    public static void checkChangedTaskStatus(String taskStatus) {
        Assertions.assertEquals(taskStatus, getChangedTaskStatus(), "Статус не равен '" + taskStatus + "'.");
    }

    public static String getFixInVersion() {
        return TaskPageElements.taskFixInVersion.getText();
    }

    @Step("Проверить, что задача должна быть исправлена в {version}")
    public static void checkFixInVersion(String version) {
        Assertions.assertEquals(version, getFixInVersion(), "Версия для исправления не соответствует " + version);
    }

    @Step("Поменять статус задачи на \"В работе\"")
    public static void setStatusInProgress() {
        TaskPageElements.taskInProgressButton.click();
    }

    @Step("Поменять статус задачи на \"Решенные\"")
    public static void setStatusResolved() {
        TaskPageElements.taskBusinessProcess.click();
        TaskPageElements.taskResolved.click();
        TaskPageElements.taskAccept.click();
    }

    @Step("Поменять статус задачи на \"Готово\"")
    public static void setStatusDone() {
        TaskPageElements.taskBusinessProcess.click();
        TaskPageElements.taskDone.click();
        TaskPageElements.taskAccept.click();
    }
}
