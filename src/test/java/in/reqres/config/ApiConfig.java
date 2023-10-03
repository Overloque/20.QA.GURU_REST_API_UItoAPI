package in.reqres.config;

import org.aeonbits.owner.Config;

@ApiConfig.Sources({
        "classpath:api.properties"
})
public interface ApiConfig extends Config {
    @Key("baseUrl")
    @DefaultValue("https://reqres.in/")
    String baseUrl();

    @Key("basePath")
    String basePath();
}
