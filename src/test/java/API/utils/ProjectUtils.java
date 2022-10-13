package API.utils;

import API.endpoints.EndPoints;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static API.utils.RequestSpecifications.*;

public final class ProjectUtils {
    @Step("Получаем значение поля {path} из JSON ответа")
    public static String getJsonPath(Response response, String path) {
        return response.jsonPath().get(path).toString();
    }

    @Step("Отправка POST запроса на создание задачи в Jira с помощью базовой авторизации")
    public static Response postJiraBasicAuth(RequestSpecification specification, String body, String url) {
        return jiraBasicAuth(specification)
                .body(body)
                .post(url);
    }

    @Step("Отправка POST запроса на удаление задачи номер {number} в Jira с помощью базовой авторизации")
    public static Response deleteJiraBasicAuth(RequestSpecification specification, String number) {
        return jiraBasicAuth(specification)
                .delete(EndPoints.JIRA_CREATE_ISSUE + "/" + number);
    }

    @Step("Отправка POST запроса на создание задачи в Jira с помощью токена")
    public static Response postJiraOAuth2(RequestSpecification specification, String body, String url) {
        return jiraOAuth2(specification)
                .body(body)
                .post(url);
    }

    @Step("Отправка POST запроса на удаление задачи номер {number} в Jira с помощью токена")
    public static Response deleteJiraOAuth2(RequestSpecification specification, String number) {
        return jiraOAuth2(specification)
                .delete(EndPoints.JIRA_CREATE_ISSUE + "/" + number);
    }

    @Step("Отправка POST запроса для получения sessionID")
    public static Response postJiraEmptySpec(RequestSpecification specification, String body, String url) {
        return specWithoutAuth(specification)
                .body(body)
                .post(url);
    }

    @Step("Отправка POST запроса на создание задачи в Jira с помощью cookies")
    public static Response postJiraSessionID(RequestSpecification specification, String sessionID, String body, String url) {
        return jiraSessionID(specification, sessionID)
                .body(body)
                .post(url);
    }

    @Step("Отправка POST запроса на удаление задачи номер {number} в Jira с помощью cookies")
    public static Response deleteJiraSessionID(RequestSpecification specification, String sessionID, String number) {
        return jiraSessionID(specification, sessionID)
                .delete(EndPoints.JIRA_CREATE_ISSUE + "/" + number);
    }

    @Step("Отправка GET запроса на создание задачи в Jira с помощью токена")
    public static Response getJiraOAuth2(RequestSpecification specification, String url) {
        return jiraOAuth2(specification)
                .get(url);
    }

    @Step("Отправка GET запроса с помощью queryParam для получения данных о персонаже")
    public static Response getWithQueryParam(RequestSpecification specification, String queryName, String queryObjects, String url) {
        return specWithoutAuth(specification)
                .queryParam(queryName, queryObjects)
                .get(url);
    }

    private static Response getWithoutAuth(RequestSpecification specification, String url) {
        return specWithoutAuth(specification)
                .get(url);
    }

    @Step("Отправка запроса на получение последнего персонажа в серии")
    public static Response getLastCharacterInEpisode(RequestSpecification specification, String url) {
        return getWithoutAuth(specification, url);
    }

    @Step("Отправка запроса на получение данных по указанному персонажу")
    public static Response getLastCharacter(RequestSpecification specification, String url) {
        return getWithoutAuth(specification, url);
    }

    @Step("Отправка GET запроса на регистрацию нового пользователя")
    public static Response postWithoutAuth(RequestSpecification specification, String body, String url) {
        return specWithoutAuth(specification)
                .body(body)
                .post(url);
    }
}
