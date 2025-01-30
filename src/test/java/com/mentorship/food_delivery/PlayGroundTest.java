package com.mentorship.food_delivery;


import com.mentorship.food_delivery.utils.ObjectConfigurator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class Person {
    String name;
    int age;
    String address;
}


public class PlayGroundTest {

    @Test
    void testObjectApplier() {

        Person person = new Person();

        var result = ObjectConfigurator.apply(person, p -> {
            p.name = "Tamer";
            p.address = "Giza";
            p.age = 22;
        });

        Assertions.assertEquals(person.name, "Tamer");
        Assertions.assertEquals(person.age, 22);
        Assertions.assertEquals(person.address, "Giza");
        Assertions.assertEquals(person, result);
    }
}
