package guru.springframework.sfgpetclinic.model;

import static org.junit.jupiter.api.Assertions.*;

import guru.springframework.sfgpetclinic.CustomArgsProvider;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class OwnerTest {

    @Test
    void dependentAssertions() {
        Owner owner = new Owner(1l, "first", "last");
        owner.setCity("Tartu");
        owner.setTelephone("01234554");

        assertAll("Owner Test", () -> {
            assertAll("Person properties test",
                () -> assertEquals("first", owner.getFirstName(),
                    "You can write a custom message here."),
                () -> assertEquals("last", owner.getLastName()));
        }, () -> {
            assertAll( "Owner Test",
                () -> assertEquals("Tartu", owner.getCity()),
                () -> assertEquals("01234554", owner.getTelephone()));
        });
    }

    @DisplayName("Value Source Test")
    @ParameterizedTest(name = "{displayName} - {index} {arguments}")
    @ValueSource(strings = {"Spring", "Framework", "Guru"})
    void testValueSource(String val) {
        System.out.println(val);
    }

    @DisplayName("Value Source Test")
    @ParameterizedTest(name = "{displayName} - {index} {arguments}")
    @EnumSource(OwnerType.class)
    void enumTest(OwnerType ownerType) {
        System.out.println(ownerType);
    }

    @DisplayName("CSV Source Test")
    @ParameterizedTest(name = "{displayName} - {index} {arguments}")
    @CsvSource({
        "FL, 1, 1",
        "OH, 2, 2",
        "MI, 3, 3"
    })
    void csvInputTest(String stateName, int val1, int val2) {
        System.out.println(String.format("%s, %d, %d", stateName, val1, val2));
    }

    @DisplayName("CSV File Source Test")
    @ParameterizedTest(name = "{displayName} - {index} {arguments}")
    @CsvFileSource(resources = "/input.csv", numLinesToSkip = 1)
    void csvInputFileTest(String stateName, int val1, int val2) {
        System.out.println(String.format("%s, %d, %d", stateName, val1, val2));
    }

    @DisplayName("Method Provider Test")
    @ParameterizedTest(name = "{displayName} - {index} {arguments}")
    @MethodSource("getArgs")
    void fromMethodTest(String stateName, int val1, int val2) {
        System.out.println(String.format("%s, %d, %d", stateName, val1, val2));
    }

    @DisplayName("Custom Provider Test")
    @ParameterizedTest(name = "{displayName} - {index} {arguments}")
    @ArgumentsSource(CustomArgsProvider.class)
    void fromCustomProviderTest(String stateName, int val1, int val2) {
        System.out.println(String.format("%s, %d, %d", stateName, val1, val2));
    }

    static Stream<Arguments> getArgs() {
        return Stream.of(
            Arguments.of("FL", 1, 1),
            Arguments.of("OH", 2, 2),
            Arguments.of("MI", 3, 3)
        );
    }
}