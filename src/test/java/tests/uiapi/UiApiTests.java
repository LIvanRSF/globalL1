package tests.uiapi;

import base.UiTestBase;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Disabled
@Tag("UIAPI")
@Story("Work with API with UI on site")
@DisplayName("API and UI tests")
public class UiApiTests extends UiTestBase {

    @Test
    @DisplayName("first API and UI Test")
    public void firstTest() {
    }
}
