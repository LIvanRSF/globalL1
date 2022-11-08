package configs;

import org.aeonbits.owner.Config;

@Config.Sources({
    "classpath:config/credentials.properties"
})
public interface CredentialsConfig extends Config {

    String login();

    String name();

    String email();

    String apiToken();

    String accLogin();

    String login1();

    String name1();

    String email1();
}
