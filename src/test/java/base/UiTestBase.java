package base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import configs.SelenoidConfig;
import helpers.Attach;
import java.util.Map;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

public class UiTestBase extends TestBase {

    protected static SelenoidConfig wdConfig = ConfigFactory.create(SelenoidConfig.class);
    protected static boolean isRemoteDriver = false;

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = baseConfig.baseUrl();
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        if (System.getProperty("host", "remote").equals("remote")) {
            isRemoteDriver = true;
            Configuration.browserCapabilities = getRemoteWDCapabilities();
            Configuration.remote = wdConfig.serverUrl();
        }
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

    private static DesiredCapabilities getRemoteWDCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
            "enableVNC", true,
            "enableVideo", true));

        return capabilities;
    }

    private String getVideoUrl() {
        return String.format("%s/%s.mp4", wdConfig.videoPath(), Attach.getSessionId());
    }
}
