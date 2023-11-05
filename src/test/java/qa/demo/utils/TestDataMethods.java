package qa.demo.utils;

import com.github.javafaker.Faker;

import java.util.Locale;

public class TestDataMethods {
    private final static Faker faker = new Faker(Locale.ENGLISH);

    public String getRandomUsername() {
        return faker.name().firstName();
    }

    public String getRandomPassword() {
        return ".!5" + faker.internet().password(8, 15, true, true, true);
    }
}
