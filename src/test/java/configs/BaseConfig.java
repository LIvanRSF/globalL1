package configs;

import org.aeonbits.owner.Config;

@Config.Sources({
    "classpath:config/baseconfig.properties"
})
public interface BaseConfig extends Config {

    String baseUrl();

    String baseApiUri();

    String users();

    String userRepos();
}
