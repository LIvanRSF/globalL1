package tests.ui;

import base.UiTestBase;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("UI")
@Story("Work with UI on site")
@DisplayName("UI tests")
public class UiTests extends UiTestBase {

    @Test
    @DisplayName("first UI Test")
    public void firstTest() {
    }

    @Test
    @DisplayName("second UI test")
    public void secondTest() {
    }
}
