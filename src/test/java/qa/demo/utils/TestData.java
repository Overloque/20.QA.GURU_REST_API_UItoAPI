package qa.demo.utils;

import qa.demo.models.CredentialsModel;

public class TestData {
    public static String username = "TestDemoUser1",
            password = "TestDemoUser1!",
            bookId = "9781449325862";



    public static CredentialsModel creds = new CredentialsModel(username, password);
}
