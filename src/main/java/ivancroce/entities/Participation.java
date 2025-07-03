package ivancroce.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "participations")

public class Participation {
    @Id
    @GeneratedValue
    @Column(name = "pariticipation_id")
    private UUID participationId;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ParticipationStatus state;

    public Participation() {
    }

    public Participation(Event event, Person person, ParticipationStatus state) {
        this.event = event;
        this.person = person;
        this.state = state;
    }

    public UUID getParticipationId() {
        return participationId;
    }


    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public ParticipationStatus getState() {
        return state;
    }

    public void setState(ParticipationStatus state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Participation{" +
                "participationId=" + participationId +
                ", event=" + event +
                ", person=" + person +
                ", state=" + state +
                '}';
    }
}

