package pl.codema.playermarket.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import pl.codema.playermarket.model.PlayerDto;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static pl.codema.playermarket.TestConstants.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class PlayerControllerIT {

    @LocalServerPort
    private int restAssuredPort;

    @BeforeEach
    public void setup() {
        RestAssured.port = restAssuredPort;
    }

    @Test
    void shouldReturnCreatedOnCreatePlayer() {
        PlayerDto playerToAdd = new PlayerDto("John", "Doe", LocalDate.of(2000, 6, 20), 0, null);
        given()
                .body(playerToAdd)
                .with()
                .contentType(ContentType.JSON)
                .when()
                .post(CREATE_PLAYER_URL)
                .then()
                .statusCode(HTTP_CREATED_STATUS_CODE);
    }

    @Test
    void shouldReturnOkOnGetAllPlayers() {
        given()
                .with()
                .contentType(ContentType.JSON)
                .when()
                .get(GET_ALL_PLAYER_URL)
                .then()
                .statusCode(HTTP_OK_STATUS_CODE)
                .body("", Matchers.hasSize(0));
    }

    @Test
    void shouldReturnMethodNotAllowedOnPostAllPlayers() {
        given()
                .with()
                .contentType(ContentType.JSON)
                .when()
                .post(GET_ALL_PLAYER_URL)
                .then()
                .statusCode(HTTP_METHOD_NOT_ALLOWED_STATUS_CODE);
    }

    @Test
    void shouldReturnNotFoundPlayer() {
        Long playerId = 1L;
        given()
                .pathParam("id", playerId)
                .when()
                .get(GET_PLAYER_URL)
                .then()
                .statusCode(HTTP_NOT_FOUND_STATUS_CODE);
    }

}