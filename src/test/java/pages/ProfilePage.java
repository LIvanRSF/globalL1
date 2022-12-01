package pages;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Cookie;

public class ProfilePage {
    private String endpoint = "/DiplomaUser";
    private SelenideElement editProfileButton = $(byText("Edit profile"));
    private SelenideElement userNameField = $("#user_profile_name");
    private SelenideElement userBioField = $("#user_profile_bio");
    private SelenideElement saveChangesButton = $(byText("Save"));
    private SelenideElement fullNameProfile = $(".p-name.vcard-fullname");
    private SelenideElement joinedGitHubDate = $("time.mb-2");
    private SelenideElement bioInfoProfile = $(".user-profile-bio");
    private SelenideElement moreActivityButton = $(".contribution-activity-show-more");
    private SelenideElement customizePinButton = $(byText("Customize your pins"));
    private SelenideElement userPackageButton = $("[href='/DiplomaUser?tab=packages']");

    public SelenideElement getHavingRepositoriesText() {
        return havingRepositoriesText;
    }

    public ProfilePage clickCustomizePinButton() {
        customizePinButton.click();
        return this;
    }

    public ProfilePage clickUserPackageButton() {
        userPackageButton.click();
        return this;
    }

    private SelenideElement havingRepositoriesText = $(".blankslate-heading");

    public void open() {
        Selenide.open(endpoint);
    }

    public void makeUserAuthorized(Cookie userAuthCookie, Cookie hostCookie) {
        WebDriverRunner.getWebDriver().manage().addCookie(userAuthCookie);
        WebDriverRunner.getWebDriver().manage().addCookie(hostCookie);
        refresh();
    }

    public SelenideElement getFullNameProfile() {
        return fullNameProfile;
    }

    public SelenideElement getBioInfoProfile() {
        return bioInfoProfile;
    }

    public SelenideElement getJoinedGitHubDate() {
        return joinedGitHubDate;
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

    public ProfilePage clickMoreActivityButton() {
        moreActivityButton.click();
        return this;
    }
}
