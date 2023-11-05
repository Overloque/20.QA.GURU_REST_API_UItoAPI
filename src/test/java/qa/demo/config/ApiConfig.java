package qa.demo.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config.properties"})
public interface ApiConfig extends Config {
    @Key("baseUrl")
    @DefaultValue("https://demoqa.com")
    String getBaseUri();

    @Key("basePathAccount")
    @DefaultValue("/Account/v1")
    String getBasePathAccount();

    @Key("basePathBookStore")
    @DefaultValue("/BookStore/v1")
    String getBasePathBookStore();

    @Key("username")
    String getUsername();

    @Key("password")
    String getPassword();
}
