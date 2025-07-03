package ivancroce.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity // Tells to JPA that this class must be mapped to a specific table
@Table(name = "events")
public class Event {

    // One event to many participations
    @OneToMany(mappedBy = "event")
    List<Participation> participations;

    @Id
    @GeneratedValue
    @Column(name = "event_id")
    private UUID eventId;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "event_date", nullable = false)
    private LocalDate eventDate;
    @Column(name = "description")
    private String description;
    @Column(name = "event_type")
    @Enumerated(EnumType.STRING)
    private EventType eventType;
    @Column(name = "max_num_participants")
    private int maxNumParticipants;

    // Many events in One location
    @ManyToOne
    @JoinColumn(name = "location_id")   // Fk's column name in the events table
    private Location location;          // location is the attribute that the mappedBy will get, need to be added in the constructor and getters/setters

    // Constructor
    // JPA needs an empty constructor to create objects for us when we read from the DB
    public Event() {
    }

    public Event(String title, LocalDate eventDate, String description, EventType eventType, int maxNumParticipants, Location location) {
        this.title = title;
        this.eventDate = eventDate;
        this.description = description;
        this.eventType = eventType;
        this.maxNumParticipants = maxNumParticipants;
        this.location = location;
    }

    public UUID getId() {
        return eventId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public int getMaxNumParticipants() {
        return maxNumParticipants;
    }

    public void setMaxNumParticipants(int maxNumParticipants) {
        this.maxNumParticipants = maxNumParticipants;
    }

    public List<Participation> getParticipations() {
        return participations;
    }

    public void setParticipations(List<Participation> participations) {
        this.participations = participations;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", title='" + title + '\'' +
                ", eventDate=" + eventDate +
                ", description='" + description + '\'' +
                ", eventType=" + eventType +
                ", maxNumParticipants=" + maxNumParticipants +
                '}';
    }
}
