package ivancroce.entities;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "concerts")
public class Concert extends Event {

    @Enumerated(EnumType.STRING)
    private ConcertGenre genre;

    @Column(name = "in_streaming")
    private boolean inStreaming;

    public Concert(String title, LocalDate eventDate, String description, EventType eventType, int maxNumParticipants, Location location, ConcertGenre genre, boolean inStreaming) {
        super(title, eventDate, description, eventType, maxNumParticipants, location);
        this.genre = genre;
        this.inStreaming = inStreaming;
    }

    public ConcertGenre getGenre() {
        return genre;
    }

    public void setGenre(ConcertGenre genre) {
        this.genre = genre;
    }

    public boolean isInStreaming() {
        return inStreaming;
    }

    public void setInStreaming(boolean inStreaming) {
        this.inStreaming = inStreaming;
    }

    @Override
    public String toString() {
        return "Concert{" +
                "genre=" + genre +
                ", inStreaming=" + inStreaming +
                '}';
    }
}
