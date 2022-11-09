package helpers;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.logging.LogType.BROWSER;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Attachment;
import java.nio.charset.StandardCharsets;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Attach {
    @Attachment(value = "Log", type = "text/plain")
    public static String addLogs() {

        return String.join("\n", Selenide.getWebDriverLogs(BROWSER));
    }

    @Attachment(value = "Screenshot ", type = "image/png", fileExtension = ".png")
    public static byte[] addScreenshot() {

        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Page source", type = "text/plain")
    public static byte[] addPageSource() {

        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "Video", type = "text/html", fileExtension = ".html")
    public static String addVideo(String videoUrl) {

        return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
            + videoUrl
            + "' type='video/mp4'></video></body></html>";
    }

    public static String getSessionId() {

        return ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
    }
}
