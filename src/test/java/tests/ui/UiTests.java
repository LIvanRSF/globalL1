package tests.ui;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

import tests.base.UiTestBase;
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
    ProfilePage profilePage = new ProfilePage();

    @Test
    @DisplayName("Edit profile of authorized user")
    public void editUserProfile() {
        Faker faker = new Faker();
        String name = faker.name().nameWithMiddle();
        String bio = faker.harryPotter().quote();

        step("Open the page", () ->
            profilePage.open());
        step("Make user authorized", () ->
            profilePage.makeUserAuthorized(userAuthCookie, hostCookie));
        step("Edit user profile: set name and bio", () ->
            profilePage.clickEditProfileButton()
                       .setUserName(name)
                       .setUserBio(bio)
                       .clickSaveButton());
        step("Check set information appears on the page", () -> {
            profilePage.getFullNameProfile().shouldHave(text(name));
            profilePage.getBioInfoProfile().shouldHave(text(bio));
        });
    }

    @Test
    @DisplayName("Check date of user registration")
    public void checkUserRegistrationDate() {
        step("Open the page", () ->
            profilePage.open());
        step("Make user authorized", () ->
            profilePage.makeUserAuthorized(userAuthCookie, hostCookie));
        step("Click more activity button", () ->
            profilePage.clickMoreActivityButton());
        step("Check date of registration", () ->
            profilePage.getJoinedGitHubDate().shouldHave(text("on November 8, 2022")));
    }

    @Test
    @DisplayName("Authorized. Main profile page have absence repo text with pronoun 'you'")
    public void noRepositoriesMainPageInfoText() {
        step("Open the page", () ->
            profilePage.open());
        step("Make user authorized", () ->
            profilePage.makeUserAuthorized(userAuthCookie, hostCookie));
        step("Main page should have text about the absence of repositories with pronoun 'you'", () ->
            profilePage.getHavingRepositoriesText().shouldHave(text("You don't have any public repositories yet.")));
    }

    @Test
    @DisplayName("Not authorization. Main profile page have absence repo text with login name")
    public void loginNameInInfoText() {
        String userName = credentialsConfig.accLogin();
        step("Open the page", () ->
            profilePage.open());
        step("Main page should have text about the absence of repositories with login name", () ->
            profilePage.getHavingRepositoriesText()
                       .shouldHave(text(userName + " doesn't have any public repositories yet.")));
    }

    @Test
    @DisplayName("User doesn't have repo. Check if click 'Customize your pins' button")
    public void editRepoTextWithoutRepo() {
        step("Open the page", () ->
            profilePage.open());
        step("Make user authorized", () ->
            profilePage.makeUserAuthorized(userAuthCookie, hostCookie));
        step("Click 'Customize your pins' button", () ->
            profilePage.clickCustomizePinButton());
        step("New frame should appear with text 'No repositories'", () ->
            $(byText("No repositories or gists found.")).shouldBe(visible)
        );
    }

    @Test
    @DisplayName("Check package menu main title")
    public void packageMainTitle() {
        step("Open the page", () ->
            profilePage.open());
        step("Make user authorized", () ->
            profilePage.makeUserAuthorized(userAuthCookie, hostCookie));
        step("Open user package menu ", () ->
            profilePage.clickUserPackageButton());
        step("Check main title text", () ->
            $(byText("Get started with GitHub Packages")).shouldBe(visible));
    }
}
