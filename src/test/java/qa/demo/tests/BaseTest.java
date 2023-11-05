package qa.demo.tests;

import org.aeonbits.owner.ConfigFactory;
import qa.demo.api.AuthorizationApi;
import qa.demo.config.ApiConfig;

public class BaseTest {
    protected static ApiConfig config = ConfigFactory.create(ApiConfig.class);
    AuthorizationApi authorizationApi = new AuthorizationApi();
}
