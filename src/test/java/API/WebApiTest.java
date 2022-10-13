package API;

import API.hooks.ApiHooks;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static API.endpoints.EndPoints.*;
import static API.utils.Asserts.*;
import static API.utils.ProjectUtils.*;

public final class WebApiTest extends ApiHooks {
    @Test
    @DisplayName("Задание 1. Работа с API по Rick and Morty.")
    public void Test_RickAndMorty() {
        Response getMortySmith = getWithQueryParam(rickAndMorty,
                "name", "morty smith",
                RICK_AND_MORTY_CHARACTERS);

        String mortySpecies = getJsonPath(getMortySmith, "results[0].species");
        String mortyLocation = getJsonPath(getMortySmith, "results[0].location.name");
        String lastEpisode = getJsonPath(getMortySmith, "results[0].episode[-1]");

        Response getLastCharacterInEpisode = getLastCharacterInEpisode(rickAndMorty, lastEpisode);

        String lastCharacterUrl = getJsonPath(getLastCharacterInEpisode, "characters[-1]");

        Response lastCharacter = getLastCharacter(rickAndMorty, lastCharacterUrl);

        String characterSpecies = getJsonPath(lastCharacter, "species");
        String characterLocation = getJsonPath(lastCharacter, "location.name");

        checkSpeciesAreEqual(mortySpecies, characterSpecies);
        checkOnSameLocation(mortyLocation, characterLocation);
    }

    @Test
    @DisplayName("Задание 2. Работа с API по reqres.in.")
    public void Test_ReqRes() {
        Response createUser = postWithoutAuth(reqRes,
                getJSONObject("createUser.json").put("name", "Tomato").put("job", "Eat market").toString(),
                REQRES_CREATE_USER);

        checkStatusCode(201, createUser.getStatusCode());
        checkJsonFieldIsPresent(createUser, "name");
        checkJsonFieldIsPresent(createUser, "job");
        checkJsonFieldIsPresent(createUser, "id");
        checkJsonFieldIsPresent(createUser, "createdAt");
        checkJsonFieldIsEquals(createUser,"Tomato", "name");
        checkJsonFieldIsEquals(createUser, "Eat market", "job");
        checkJsonFieldIsNotEmpty(createUser, "", "id");
        checkJsonFieldIsNotEmpty(createUser, "", "createdAt");
    }
}
