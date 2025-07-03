package ivancroce.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "athletics_competitions")
public class AthleticsCompetition extends Event {

    @ManyToMany
    @JoinTable(name = "competition_athletes",
            joinColumns = @JoinColumn(name = "competition_id"),
            inverseJoinColumns = @JoinColumn(name = "athlete_id"))
    private Set<Person> athletes;

    @ManyToOne
    @JoinColumn(name = "winner_id")
    private Person winner;

    public AthleticsCompetition(String title, LocalDate eventDate, String description, EventType eventType, int maxNumParticipants, Location location, Set<Person> athletes, Person winner) {
        super(title, eventDate, description, eventType, maxNumParticipants, location);
        this.athletes = athletes;
        this.winner = winner;
    }

    public Set<Person> getAthletes() {
        return athletes;
    }

    public void setAthletes(Set<Person> athletes) {
        this.athletes = athletes;
    }

    public Person getWinner() {
        return winner;
    }

    public void setWinner(Person winner) {
        this.winner = winner;
    }

    @Override
    public String toString() {
        return "AthleticsCompetition{" +
                "athletes=" + athletes +
                ", winner=" + winner +
                '}';
    }
}
