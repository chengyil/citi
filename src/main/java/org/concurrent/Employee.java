package org.concurrent;
import lombok.Getter;

public class Employee {
    @Getter private String name;

    public Employee(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(null == obj) return false;
        Employee thatEmployee = (Employee) obj;
        return thatEmployee.getName().equals(this.getName());
    }
}