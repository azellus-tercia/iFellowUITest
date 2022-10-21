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
    @Test
    @DisplayName("Проверка авторизации пользователя.")
    public void Test_UserIsAuthorized() {
        goToUserPage();
        checkUserIsLogged(getConfigurationValue("username"));
    }

    @Test
    @DisplayName("Проверка перехода на проект.")
    public void Test_ProjectIsOpen() {
        openProject(getConfigurationValue("project_name_with_code"));
        checkProjectName(getConfigurationValue("project_name"));
    }

    @Test
    @DisplayName("Вывод количества задач в консоль.")
    public void Test_ShowNumberOfTasks() {
        openProject(getConfigurationValue("project_name_with_code"));
        clickTasks();
        changeFiltersTo(getConfigurationValue("displayFilter_allTasks"));
        checkNumberOfFilteredTasks();
    }

    @Test
    @DisplayName("Проверка статуса задачи и привязки затронутой версии.")
    public void Test_GetTaskStatus() {
        openProject(getConfigurationValue("project_name_with_code"));
        searchTask(getConfigurationValue("task_connectedTask"));
        checkTaskStatusIsSet();
        checkFixInVersion(getConfigurationValue("task_fix_in_version"));
    }

    @Test
    @DisplayName("Создание задачи и проверка смены статуса.")
    public void Test_CreateTask() {
        openProject(getConfigurationValue("project_name_with_code"));
        clickTasks();
        newTaskWithDialogue();
        setTaskFields(getConfigurationValue("task_type_error"),
                getConfigurationValue("new_task_name"),
                getConfigurationValue("task_description"),
                getConfigurationValue("task_fix_in_version"),
                getConfigurationValue("task_environment"),
                getConfigurationValue("task_affected_version"),
                getConfigurationValue("task_connectedTask"));
        acceptAndCreateTask();
        searchCreatedTask(getConfigurationValue("new_task_name"));

        checkTaskStatus(getConfigurationValue("status_toDo"));

        setStatusInProgress();
        checkChangedTaskStatus(getConfigurationValue("status_inProgress"));

        setStatusResolved();
        checkChangedTaskStatus(getConfigurationValue("status_resolved"));

        setStatusDone();
        checkChangedTaskStatus(getConfigurationValue("status_done"));
    }
}
