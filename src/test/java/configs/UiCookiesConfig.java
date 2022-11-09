package configs;

import org.aeonbits.owner.Config;

@Config.Sources({
    "classpath:config/cookies.properties"
})
public interface UiCookiesConfig extends Config {

    String authCookies();
}
