package tests.api;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.BaseSpec.baseRequest;
import static specs.BaseSpec.credentialsConfig;

import base.TestBase;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;
import models.UserPojoModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

@Tag("API")
@Story("Work with api on gitHub")
@DisplayName("API tests")
public class ApiTests extends TestBase {

    @ParameterizedTest
    @Feature("test with csv file source")
    @CsvFileSource(resources = "/csv/users.csv")
    @DisplayName("Comparing user data")
    public void compareUserData(String login, String name, String mail) {
        baseRequest.basePath("/users");
        AtomicReference<UserPojoModel> user = new AtomicReference<>();

        step("Get user data", () ->
            user.set(given()
                .spec(baseRequest)
                .when()
                //.get(credentialsConfig.login1())
                .get(login)
                .then()
                .log().body()
                .statusCode(200)
                .extract().as(UserPojoModel.class)));

        step("User data must be tha same as entered before", () -> {
            assertThat(user.get().getLogin()).isEqualTo(login);
            assertThat(user.get().getName()).isEqualTo(name);
            assertThat(user.get().getEmail()).isEqualTo(mail);
        });
    }


    static Stream<Arguments> userCompareTestDataProvider() {
        return Stream.of(
            Arguments.of("wycats", "Yehuda Katz", null ),
            Arguments.of("KirinDave", "Dave Fayram", null)
        );
    }

    @ParameterizedTest
    @Feature("test with stream arguments data")
    @MethodSource(value = "userCompareTestDataProvider")
    @DisplayName("Comparing user data with stream arguments")
    public void compareUserDataWithStreamArguments(String login, String name, String mail) {
        baseRequest.basePath("/users");
        AtomicReference<UserPojoModel> user = new AtomicReference<>();

        step("Get user data", () ->
            user.set(given()
                .spec(baseRequest)
                .when()
                .get(login)
                .then()
                .log().body()
                .statusCode(200)
                .extract().as(UserPojoModel.class)));

        step("User data must be tha same as entered before", () -> {
            assertThat(user.get().getLogin()).isEqualTo(login);
            assertThat(user.get().getName()).isEqualTo(name);
            assertThat(user.get().getEmail()).isEqualTo(mail);
        });
    }

    @Test
    @Feature("test with secret user data")
    @DisplayName("Comparing user data with stream arguments")
    public void compareUserDataWithSecretArguments() {
        baseRequest.basePath("/users");
        AtomicReference<UserPojoModel> user = new AtomicReference<>();

        step("Get user data", () ->
            user.set(given()
                .spec(baseRequest)
                .when()
                .get(credentialsConfig.login())
                .then()
                .log().body()
                .statusCode(200)
                .extract().as(UserPojoModel.class)));

        step("User data must be tha same as entered before", () -> {
            assertThat(user.get().getLogin()).isEqualTo(credentialsConfig.login());
            assertThat(user.get().getName()).isEqualTo(credentialsConfig.name());
            assertThat(user.get().getEmail()).isEqualTo(credentialsConfig.email());
        });
    }
}
