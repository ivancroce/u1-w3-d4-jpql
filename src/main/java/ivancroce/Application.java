package ivancroce;

import ivancroce.dao.EventsDAO;
import ivancroce.dao.LocationsDAO;
import ivancroce.dao.ParticipationsDAO;
import ivancroce.dao.PersonsDAO;
import ivancroce.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Set;

public class Application {
    // 1. STEP: create the emf to connect the DB
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("events_management_3_pu");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager(); // em manages all the operations with the DB

        // Instances of all DAO
        EventsDAO ed = new EventsDAO(em); // instance of our DAO, passing em
        LocationsDAO locationDAO = new LocationsDAO(em);
        PersonsDAO personDAO = new PersonsDAO(em);
        ParticipationsDAO participationDAO = new ParticipationsDAO(em);

        Location threeArenaFromDb = locationDAO.findById("f7911009-3759-4e44-9b68-28522468930c");
        Person aldoFromDb = personDAO.findById("7ccd98d0-bc80-4ead-923c-efc96abb7a78");
        Person giovanniFromDb = personDAO.findById("1bb7032e-ecb8-43bf-9e34-0a10affe35bc");
        Person mariaFromDb = personDAO.findById("980df8d4-da39-46b4-b732-b43d135b2fb4");
        // --- create and save football matches ---

        FootballMatch fm1 = new FootballMatch("Derby della Madonnina", LocalDate.now(), "Partitona", EventType.PUBLIC, 850000, threeArenaFromDb, "Inter", "Milan");
        fm1.setHomeTeamGoals(5);
        fm1.setAwayTeamGoals(4);
        //ed.save(fm1);

        FootballMatch fm2 = new FootballMatch("Derby delle bestemmie", LocalDate.now(), "Dio", EventType.PUBLIC, 1000000, threeArenaFromDb, "Gesu", "Giuseppe");
        fm1.setHomeTeamGoals(8);
        fm1.setAwayTeamGoals(3);
        fm1.setWinningTeam("Gesu");
        //ed.save(fm2);

        FootballMatch fm3 = new FootballMatch("Derby delle vittorie", LocalDate.now(), "madonna", EventType.PUBLIC, 500000, threeArenaFromDb, "Madonna", "Giuseppe");
        fm1.setHomeTeamGoals(10);
        fm1.setAwayTeamGoals(1);
        fm1.setWinningTeam("Madonna");
        // ed.save(fm3);

        // football match won by home team
        ed.getMatchesWonByHomeTeam().forEach(System.out::println);

        // football match won by away team
        ed.getMatchesWonByAwayTeam().forEach(System.out::println);


        // Concert in streaming
        Concert concertoStreaming = new Concert("Concerto di Natale", LocalDate.now().plusMonths(5), "Musica classica", EventType.PUBLIC, 2000, threeArenaFromDb, ConcertGenre.CLASSIC, true);
        // ed.save(concertoStreaming);

        // Concert Rock NOT in streaming
        Concert concertRock = new Concert("Rock Fest", LocalDate.now().plusMonths(3), "Festival rock", EventType.PUBLIC, 50000, threeArenaFromDb, ConcertGenre.ROCK, false);
        // ed.save(concertRock);

        // AthleticsCompetition
        AthleticsCompetition maratona = new AthleticsCompetition("Maratona di Milano", LocalDate.now().plusDays(40), "Corsa cittadina", EventType.PUBLIC, 10000, threeArenaFromDb);
        maratona.setWinner(aldoFromDb); // Aldo is the winner
        maratona.setAthletes(Set.of(aldoFromDb, giovanniFromDb)); // Aldo and Giovanni are participants/athletes
        // ed.save(maratona);

        System.out.println("✅ New Events saved.");


        em.close();
        emf.close();
    }
}