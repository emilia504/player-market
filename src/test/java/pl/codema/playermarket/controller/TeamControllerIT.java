package pl.codema.playermarket.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import pl.codema.playermarket.model.TeamDto;

import java.util.Currency;

import static io.restassured.RestAssured.given;
import static pl.codema.playermarket.TestConstants.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class TeamControllerIT {

    @LocalServerPort
    private int restAssuredPort;

    @BeforeEach
    public void setup() {
        RestAssured.port = restAssuredPort;
    }

    @Test
    void shouldReturnCreatedOnCreateTeam() {
        TeamDto teamToAdd = new TeamDto("aTeam", Currency.getInstance("PLN"), null);
        given()
                .body(teamToAdd)
                .with()
                .contentType(ContentType.JSON)
                .when()
                .post(CREATE_TEAM_URL)
                .then()
                .statusCode(HTTP_CREATED_STATUS_CODE);
    }

    @Test
    void shouldReturnOkOnGetAllTeams() {
        given()
                .with()
                .contentType(ContentType.JSON)
                .when()
                .get(GET_ALL_TEAM_URL)
                .then()
                .statusCode(HTTP_OK_STATUS_CODE)
                .body("", Matchers.hasSize(0));
    }

    @Test
    void shouldReturnMethodNotAllowedOnPostAllTeams() {
        given()
                .with()
                .contentType(ContentType.JSON)
                .when()
                .post(GET_ALL_TEAM_URL)
                .then()
                .statusCode(HTTP_METHOD_NOT_ALLOWED_STATUS_CODE);
    }

    @Test
    void shouldReturnNotFoundTeam() {
        Long teamId = 1L;
        given()
                .pathParam("id", teamId)
                .when()
                .get(GET_TEAM_URL)
                .then()
                .statusCode(HTTP_NOT_FOUND_STATUS_CODE);
    }

}