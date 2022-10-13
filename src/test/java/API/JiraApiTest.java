package API;

import API.hooks.ApiHooks;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static API.endpoints.EndPoints.*;
import static API.utils.ProjectUtils.*;
import static utils.Configuration.getConfigurationValue;

public final class JiraApiTest extends ApiHooks {
    @Test
    @DisplayName("Создание новой задачи и ее удаление через базовую авторизацию.")
    public void Test_CreateBugBasicAuth() {
        Response createTaskResponse = postJiraBasicAuth(jiraRequest,
                getJSONObject("createBug.json")
                        .put("description", "Here is some description.")
                        .toString(),
                JIRA_CREATE_ISSUE);

        String responseId = getJsonPath(createTaskResponse, "id");

        deleteJiraBasicAuth(jiraRequest, responseId);
    }

    @Test
    @DisplayName("Создание новой задачи и ее удаление через OAuth2.0 авторизацию.")
    public void Test_CreateBugOAuth2() {
        Response createTaskResponse = postJiraOAuth2(jiraRequest,
                getJSONObject("createBug.json")
                        .put("description", "Here is some description.")
                        .toString(),
                JIRA_CREATE_ISSUE);

        String responseId = getJsonPath(createTaskResponse, "id");

        deleteJiraOAuth2(jiraRequest, responseId);
    }

    @Test
    @DisplayName("Создание новой задачи и ее удаление через cookie авторизацию.")
    public void Test_CreateBugCookieAuth() {
        JSONObject requestCookies = new JSONObject();
        requestCookies.put("username", getConfigurationValue("username"));
        requestCookies.put("password", getConfigurationValue("password"));

        Response sessionCookies = postJiraEmptySpec(jiraRequest,
                requestCookies.toString(),
                JIRA_GET_SESSION_ID);

        String sessionId = getJsonPath(sessionCookies, "session.value");

        Response createTaskResponse = postJiraSessionID(jiraRequest, sessionId,
                getJSONObject("createBug.json")
                        .put("description", "Here is some description.")
                        .toString(),
                JIRA_CREATE_ISSUE);

        String responseId = getJsonPath(createTaskResponse, "id");

        deleteJiraSessionID(jiraRequest, sessionId, responseId);
    }

    @Test
    @DisplayName("Получить и вывести количество всех задач в Jira.")
    public void Test_GetAllTasks() {
        Response createTaskResponse = getJiraOAuth2(jiraRequest, JIRA_GET_ALL_TASKS);

        System.out.println(getJsonPath(createTaskResponse, "total"));
    }
}
