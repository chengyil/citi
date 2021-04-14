package org.concurrent;
import lombok.Getter;

public class Location {
    @Getter private String name;
    public Location(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(null == obj) return false;
        Location thatLocation = (Location)obj;
        return thatLocation.getName().equals(this.getName());
    }
}
