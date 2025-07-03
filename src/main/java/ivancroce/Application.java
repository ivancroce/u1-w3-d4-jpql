package ivancroce;

import ivancroce.dao.EventsDAO;
import ivancroce.dao.LocationsDAO;
import ivancroce.dao.ParticipationsDAO;
import ivancroce.dao.PersonsDAO;
import ivancroce.entities.*;
import ivancroce.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

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

        // --- Create and save data in DB ---
        Location threeArena = new Location("3Arena", "Dublin");
        Location o2Arena = new Location("O2 Arena", "London");
        // locationDAO.save(threeArena);
        // locationDAO.save(o2Arena);

        Person aldoBaglio = new Person("Aldo", "Baglio", "aldo.baglio@gmail.com", LocalDate.of(1958, 9, 28), PersonGender.MALE);
        Person giovanniStorti = new Person("Giovanni", "Storti", "giovanni.storti@gmail.com", LocalDate.of(1957, 2, 20), PersonGender.MALE);
        Person mariaPia = new Person("Maria", "Pia", "maria.pia@gmail.com", LocalDate.of(1965, 9, 30), PersonGender.FEMALE);
        // personDAO.save(aldoBaglio);
        // personDAO.save(giovanniStorti);
        // personDAO.save(mariaPia);

        // --- Create an Event which depends on a Location ---
        // We need to retrieve the location we want to use from the DB to ensure that we are working with a JPA-MANAGED obj.
        try {
            Location locationFromDB = locationDAO.findById("f7911009-3759-4e44-9b68-28522468930c");
            Person aldoFromDb = personDAO.findById("7ccd98d0-bc80-4ead-923c-efc96abb7a78");
            Person giovanniFromDb = personDAO.findById("1bb7032e-ecb8-43bf-9e34-0a10affe35bc");
            Person mariaFromDb = personDAO.findById("980df8d4-da39-46b4-b732-b43d135b2fb4");
            // System.out.println(locationFromDB);
            // System.out.println(aldoFromDb);

            // create a new Event to a Location
            Event aldoBaglioBday = new Event("Aldo Baglio's Birthday", LocalDate.now().plusMonths(2), "Big Party", EventType.PUBLIC, 7000, locationFromDB);
            Event giovanniStortiShow = new Event("Giovanni Storti Show", LocalDate.now().plusMonths(4), "Big Show", EventType.PRIVATE, 100, locationFromDB);
            Event mariaPiaConcert = new Event("Maria Pia Concert", LocalDate.now().minusMonths(2), "Big Concert", EventType.PUBLIC, 2000, locationFromDB);

            // ed.save(aldoBaglioBday);
            // ed.save(giovanniStortiShow);
            // ed.save(mariaPiaConcert);

            Event bdayFromDb = ed.findById("2502f287-b2c5-4963-a711-db56cf348412");
            Event showFromDb = ed.findById("8a5d8c55-a455-4d90-bc8d-3c1e5a2b5d41");
            Event mariaPiaFromDb = ed.findById("cbdc22b9-2525-49a2-9dba-495e8a3c99c6");

            // --- Create and save Participations ---
            // we need to pass the data from DB because aldo, giovanni and mariapia don't have ID.


            Participation participationAldo = new Participation(bdayFromDb, aldoFromDb, ParticipationStatus.CONFIRMED);
            // participationDAO.save(participationAldo);

            Participation participationGiovanni = new Participation(showFromDb, giovanniFromDb, ParticipationStatus.TO_BE_CONFIRMED);
            // participationDAO.save(participationGiovanni);

            Participation participationMaria = new Participation(mariaPiaFromDb, mariaFromDb, ParticipationStatus.CONFIRMED);
            // participationDAO.save(participationMaria);

            // System.out.println(participationAldo);
            // System.out.println(participationGiovanni);
            // System.out.println(participationMaria);
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        em.close();
        emf.close();
    }
}