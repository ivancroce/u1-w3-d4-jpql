package ivancroce.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "locations")

public class Location {

    @Id
    @GeneratedValue
    @Column(name = "location_id")
    private UUID locationId;

    @Column(name = "name")
    private String name;

    @Column(name = "city")
    private String city;

    // Relation OneToMany, one location can have more events
    @OneToMany(mappedBy = "location")
    // mappedBy it means that location is already mapped from the Event class, it gets the value from the Event class
    private List<Event> events;

    public Location() {
    }

    public Location(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public UUID getLocationId() {
        return locationId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationId=" + locationId +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
