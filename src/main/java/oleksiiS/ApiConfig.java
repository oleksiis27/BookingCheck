package oleksiiS;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config.properties"})

public interface ApiConfig extends org.aeonbits.owner.Config {
    @Key("baseUrl")
    String baseUrl();

    @Key("username")
    String username();

    @Key("password")
    String password();
}