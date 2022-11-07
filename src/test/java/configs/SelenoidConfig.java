package configs;

import org.aeonbits.owner.Config;

@Config.Sources({
    "classpath:config.selenoid.properties"
})
public interface SelenoidConfig extends Config {

    String serverUrl();
    String videoPath();
}
