import com.github.javafaker.Faker;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.experimental.UtilityClass;

import java.util.Locale;

@UtilityClass
public class DataGenerator {
    @UtilityClass
    public static class Registration {
        public static RegistrationInfo registrationInfo(String locale, String status) {
            Faker faker = new Faker(new Locale(locale));
            return new RegistrationInfo(faker.name().username(), faker.internet().password(), status);
        }

        public static RegistrationInfo registrationWrongInfo(String locale, String status) {
            Faker faker = new Faker(new Locale(locale));
            return new RegistrationInfo(faker.funnyName().name(), faker.starTrek().location(), status);
        }
    }
}
