package base;

import configs.BaseConfig;
import org.aeonbits.owner.ConfigFactory;

public class TestBase {
    protected static BaseConfig baseConfig = ConfigFactory.create(BaseConfig.class);
}
