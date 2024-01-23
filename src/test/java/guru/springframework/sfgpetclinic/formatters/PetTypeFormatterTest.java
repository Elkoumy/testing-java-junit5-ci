package guru.springframework.sfgpetclinic.formatters;

import static org.junit.jupiter.api.Assertions.*;

import guru.springframework.sfgpetclinic.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PetTypeFormatterTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void grouptedTest() {
        Person person = new Person(1l, "FirstName", "LastName");
        //both the below tests will run, even if the first one fails.
        assertAll("Test Props Set",
            () -> assertEquals("FirstName1", person.getFirstName(),
                "You can write a custom message here."),
            () -> assertEquals("LastName", person.getLastName()));
    }
}