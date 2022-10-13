package UI;

import UI.hooks.WebHooks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static UI.PageObject.PageSteps.HeaderElementsSteps.*;
import static UI.PageObject.PageSteps.NewTaskWindowElementsSteps.*;
import static UI.PageObject.PageSteps.ProfilePageElementsSteps.*;
import static UI.PageObject.PageSteps.ProjectPageElementsSteps.*;
import static UI.PageObject.PageSteps.TaskPageElementsSteps.*;
import static utils.Configuration.getConfigurationValue;

public final class JiraTest extends WebHooks {
    private static final String PROJECT_NAME_WITH_CODE = "Test (TEST)";
    private static final String PROJECT_NAME = "Test";
    private static final String VERSION = "Version 2.0";
    private static final String TASK_NAME = "New Task Error";

    @Test
    @DisplayName("Проверка авторизации пользователя.")
    public void Test_UserIsAuthorized() {
        goToUserPage();
        checkUserIsLogged(getConfigurationValue("username"));
    }

    @Test
    @DisplayName("Проверка перехода на проект.")
    public void Test_ProjectIsOpen() {
        openProject(PROJECT_NAME_WITH_CODE);
        checkProjectName(PROJECT_NAME);
    }

    @Test
    @DisplayName("Вывод количества задач в консоль.")
    public void Test_ShowNumberOfTasks() {
        openProject(PROJECT_NAME_WITH_CODE);
        clickTasks();
        changeFiltersTo("Все задачи");
        printNumberOfTasks();
    }

    @Test
    @DisplayName("Проверка статуса задачи и привязки затронутой версии.")
    public void Test_GetTaskStatus() {
        openProject(PROJECT_NAME_WITH_CODE);
        searchTask("TEST-21967");
        checkTaskStatusIsSet();
        checkFixInVersion(VERSION);
    }

    @Test
    @DisplayName("Создание задачи и проверка смены статуса.")
    public void Test_CreateTask() {
        openProject(PROJECT_NAME_WITH_CODE);
        clickTasks();
        newTaskWithDialogue();
        setTaskFields("Ошибка",
                TASK_NAME,
                "Here is a task description.",
                "Version 2.0",
                "Here is some environment.",
                "Version 2.0",
                "TEST-21967");
        acceptAndCreateTask();
        searchCreatedTask(TASK_NAME);

        checkTaskStatus("СДЕЛАТЬ");

        setStatusInProgress();
        checkChangedTaskStatus("В РАБОТЕ");

        setStatusResolved();
        checkChangedTaskStatus("РЕШЕННЫЕ");

        setStatusDone();
        checkChangedTaskStatus("ГОТОВО");
    }
}
