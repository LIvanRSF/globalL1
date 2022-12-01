package tests.api;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.BaseSpec.baseRequest;
import static specs.BaseSpec.credentialsConfig;

import tests.base.ApiTestBase;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;
import specs.models.RepositoryPojoModel;
import specs.models.UserPojoModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import specs.AuthSpec;

@Tag("API")
@Story("Work with api on gitHub")
@DisplayName("API tests")
public class ApiTests extends ApiTestBase {

    @ParameterizedTest
    @Feature("test with csv file source")
    @CsvFileSource(resources = "/csv/users.csv")
    @DisplayName("Comparing user data")
    public void compareUserData(String login, String name, String mail) {
        AtomicReference<UserPojoModel> user = new AtomicReference<>();

        step("Get user data", () ->
            user.set(given()
                .spec(baseRequest)
                .basePath(baseConfig.users())
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

    static Stream<Arguments> userCompareTestDataProvider() {
        return Stream.of(
            Arguments.of("wycats", "Yehuda Katz", null),
            Arguments.of("KirinDave", "Dave Fayram", null)
        );
    }

    @ParameterizedTest
    @Feature("test with stream arguments data")
    @MethodSource(value = "userCompareTestDataProvider")
    @DisplayName("Comparing user data with stream arguments")
    public void compareUserDataWithStreamArguments(String login, String name, String mail) {
        AtomicReference<UserPojoModel> user = new AtomicReference<>();

        step("Get user data", () ->
            user.set(given()
                .spec(baseRequest)
                .basePath(baseConfig.users())
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
    @DisplayName("Comparing user data with secret arguments")
    public void compareUserDataWithSecretArguments() {
        AtomicReference<UserPojoModel> user = new AtomicReference<>();

        step("Get user data", () ->
            user.set(given()
                .spec(baseRequest)
                .basePath(baseConfig.users())
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

    @Test
    @Feature("test with post method and Authorization token")
    @DisplayName("Create new repository to user")
    public void createUserRepository() {
        //Set options of repository we want to create
        RepositoryPojoModel repositoryModel = new RepositoryPojoModel()
            .name("repository")
            .isPrivate(true)
            .description("Repository for test. Should be deleted");
        AtomicReference<RepositoryPojoModel> repo = new AtomicReference<>();

        step("Create repository for authorized user", () ->
            repo.set(given()
                .spec(AuthSpec.reqSpec)
                .basePath(baseConfig.userRepos())
                .body(repositoryModel)
                .when()
                .post()
                .then()
                .log().body()
                .statusCode(201)
                .extract().body().as(RepositoryPojoModel.class)));

        step("Compare repository info and entered before data ", () -> {
            RepositoryPojoModel repoInfo = repo.get();
            assertThat(repoInfo.id()).isNotNull();
            assertThat(repoInfo.name()).isEqualTo(repositoryModel.name());
            assertThat(repoInfo.isPrivate()).isEqualTo(repositoryModel.isPrivate());
            assertThat(repoInfo.description()).isEqualTo(repositoryModel.description());
            assertThat(repoInfo.owner().login()).isEqualTo(credentialsConfig.accLogin());
        });

        // Delete created repository. Clear test data
        step("Delete created repository", () ->
            cleanRepoList(repositoryModel.name()));
    }

    //method to delete repository from test
    private static void cleanRepoList(String repositoryName) {
        String reqPath = String.format("/repos/%s/", credentialsConfig.accLogin());
        given()
            .spec(AuthSpec.reqSpec)
            .noFilters()
            .basePath(reqPath)
            .when()
            .delete(repositoryName)
            .then()
            .log().body()
            .statusCode(204);
    }
}
