package tests.base;

import static tests.base.ApiTestBase.baseConfig;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import configs.CredentialsConfig;
import configs.SelenoidConfig;
import configs.UiCookiesConfig;
import helpers.Attach;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.remote.DesiredCapabilities;

public class UiTestBase{

    protected static SelenoidConfig wdConfig = ConfigFactory.create(SelenoidConfig.class);
    protected static UiCookiesConfig uiCookies = ConfigFactory.create(UiCookiesConfig.class);
    protected static CredentialsConfig credentialsConfig = ConfigFactory.create(CredentialsConfig.class);
    protected static Cookie userAuthCookie = new Cookie("user_session", uiCookies.authCookies());
    protected static Cookie hostCookie = new Cookie("__Host-user_session_same_site", uiCookies.authCookies());
    protected static boolean isRemoteDriver = false;

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = baseConfig.baseUrl();
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.browserVersion = System.getProperty("browserVersion", "100");
        if (System.getProperty("host", "remote").equals("remote")) {
            isRemoteDriver = true;
            setRemoteWebdriver();
        }
    }

    static void setRemoteWebdriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
        Configuration.remote = wdConfig.serverUrl();
    }

    @AfterEach
    void afterEach() {
        Attach.addLogs();
        Attach.addPageSource();
        Attach.addScreenshot();
        if (isRemoteDriver) {
            Attach.addVideo(getVideoUrl());
        }
        Selenide.closeWebDriver();
    }

    private String getVideoUrl() {
        return String.format("%s/%s.mp4", wdConfig.videoPath(), Attach.getSessionId());
    }
}
