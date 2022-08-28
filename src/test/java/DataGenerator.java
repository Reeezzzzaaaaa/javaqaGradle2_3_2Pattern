import com.github.javafaker.Faker;
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
            return new RegistrationInfo(faker.name().username(), faker.internet().password(), status);
        }
    }
}
