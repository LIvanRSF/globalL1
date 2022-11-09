package pages;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Cookie;

public class ProfilePage {
    public final String endpoint = "/DiplomaUser";
    private SelenideElement editProfileButton = $(byText("Edit profile"));
    private SelenideElement userNameField = $("#user_profile_name");
    private SelenideElement userBioField = $("#user_profile_bio");
    private SelenideElement saveChangesButton = $(byText("Save"));
    public SelenideElement fullNameProfile = $(".p-name.vcard-fullname");
    public SelenideElement bioInfoProfile = $(".user-profile-bio");
    public SelenideElement joinedGitHubDate = $("time.mb-2");

    public void makeUserAuthorized(Cookie userAuthCookie, Cookie hostCookie) {
        WebDriverRunner.getWebDriver().manage().addCookie(userAuthCookie);
        WebDriverRunner.getWebDriver().manage().addCookie(hostCookie);
        refresh();
    }

    public ProfilePage clickEditProfileButton() {
        editProfileButton.click();
        return this;
    }

    public ProfilePage setUserName(String value) {
        userNameField.setValue(value);
        return this;
    }

    public ProfilePage setUserBio(String value) {
        userBioField.setValue(value);
        return this;
    }

    public void clickSaveButton() {
        saveChangesButton.click();
    }
}
