package qa.demo.utils;

import qa.demo.models.authorization.CredentialsModel;
import qa.demo.tests.BaseTest;

public class TestDataVariables extends BaseTest {
    private final static TestDataMethods testDataMethods = new TestDataMethods();
    public final static String existUsername = config.getUsername(),
            existPassword = config.getPassword(),
            randomUsernamae = testDataMethods.getRandomUsername(),
            randomPassword = testDataMethods.getRandomPassword(),
            bookId = "9781449325862";

    public final static CredentialsModel existCreds = new CredentialsModel(existUsername, existPassword);
    public final static CredentialsModel randomCreds = new CredentialsModel(randomUsernamae, randomPassword);
}
