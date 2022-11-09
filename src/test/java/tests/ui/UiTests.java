package tests.ui;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

import base.UiTestBase;
import com.github.javafaker.Faker;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

@Tag("UI")
@Story("Work with UI on site")
@DisplayName("UI tests")
public class UiTests extends UiTestBase {

    @Test
    @DisplayName("Edit profile of authorized user")
    public void editUserProfile() {
        Faker faker = new Faker();
        String name = faker.name().nameWithMiddle();
        String bio = faker.harryPotter().quote();
        ProfilePage profilePage = new ProfilePage();

        step("Open the page", () ->
            open(profilePage.endpoint));
        step("Make user authorized", () ->
            profilePage.makeUserAuthorized(userAuthCookie, hostCookie));
        step("Edit user profile: set name and bio", () ->
            profilePage.clickEditProfileButton()
                       .setUserName(name)
                       .setUserBio(bio)
                       .clickSaveButton());
        step("Check set information appears on the page", () -> {
            profilePage.fullNameProfile.shouldHave(text(name));
            profilePage.bioInfoProfile.shouldHave(text(bio));
        });
    }

    @Test
    @DisplayName("Check date of user registration")
    public void simpleGuiTest() {
        ProfilePage profilePage = new ProfilePage();

        step("Open the page", () ->
            open(profilePage.endpoint));
        step("Make user authorized", () ->
            profilePage.makeUserAuthorized(userAuthCookie, hostCookie));

        step("Check date of registration", () ->
            profilePage.joinedGitHubDate.shouldHave(text("on November 8, 2022")));
    }
}
