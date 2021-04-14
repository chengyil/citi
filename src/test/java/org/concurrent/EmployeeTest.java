package org.concurrent;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class EmployeeTest {
    @Test
    void shouldBeAbleToGetName() {
        Employee employee = new Employee("Name");
        assertEquals(employee.getName(), "Name");
    }

    @Test
    void shouldBeEqual() {
        Employee john = new Employee("John");
        Employee thatJohn = new Employee("John");
        Employee mary = new Employee("Mary");
        assertEquals(john, thatJohn);
        assertNotEquals(john, mary);
    }
}