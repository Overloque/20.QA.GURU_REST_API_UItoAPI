package qa.demo.tests;

import org.aeonbits.owner.ConfigFactory;
import qa.demo.api.AccountApi;
import qa.demo.api.BookApi;
import qa.demo.api.AuthorizationApi;
import qa.demo.config.ApiConfig;

public class BaseTest {
    protected static ApiConfig config = ConfigFactory.create(ApiConfig.class);
    AuthorizationApi authorizationApi = new AuthorizationApi();
    AccountApi accountApi = new AccountApi();
    BookApi bookApi = new BookApi();
}
