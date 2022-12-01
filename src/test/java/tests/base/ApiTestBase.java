package tests.base;

import static io.restassured.RestAssured.baseURI;

import configs.BaseConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;

public class ApiTestBase {
    protected static BaseConfig baseConfig = ConfigFactory.create(BaseConfig.class);

    @BeforeAll
    static void beforeAll() {
        baseURI = baseConfig.baseApiUri();
    }
}
