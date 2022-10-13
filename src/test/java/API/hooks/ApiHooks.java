package API.hooks;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static utils.Configuration.getConfigurationValue;

public abstract class ApiHooks {
    public static RequestSpecification jiraRequest;
    public static RequestSpecification rickAndMorty;
    public static RequestSpecification reqRes;

    @BeforeAll
    static void setUp() {
         jiraRequest = new RequestSpecBuilder()
                 .setBaseUri(getConfigurationValue("jiraUrlAPI"))
                 .setContentType(ContentType.JSON)
                 .setAccept(ContentType.JSON)
                 .addFilter(new AllureRestAssured())
                 .addFilter(new RequestLoggingFilter())
                 .addFilter(new ResponseLoggingFilter())
                 .build();

         rickAndMorty = new RequestSpecBuilder()
                 .setBaseUri(getConfigurationValue("rickAndMortyUrl"))
                 .setContentType(ContentType.JSON)
                 .setAccept(ContentType.JSON)
                 .addFilter(new AllureRestAssured())
                 .addFilter(new RequestLoggingFilter())
                 .addFilter(new ResponseLoggingFilter())
                 .build();

         reqRes = new RequestSpecBuilder()
                 .setBaseUri(getConfigurationValue("reqResUrl"))
                 .setContentType(ContentType.JSON)
                 .setAccept(ContentType.JSON)
                 .addFilter(new AllureRestAssured())
                 .addFilter(new RequestLoggingFilter())
                 .addFilter(new ResponseLoggingFilter())
                 .build();
    }

    public JSONObject getJSONObject(String fileName) {
        try {
            return new JSONObject(new String(Files.readAllBytes(Paths.get("src/test/resources/requests/" + fileName))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
