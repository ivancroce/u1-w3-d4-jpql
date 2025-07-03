package ivancroce.dao;

import ivancroce.entities.Concert;
import ivancroce.entities.ConcertGenre;
import ivancroce.entities.Event;
import ivancroce.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.UUID;

public class EventsDAO {

    // em is our "contact" with the DB
    private EntityManager em;

    public EventsDAO(EntityManager em) {
        this.em = em;
    }

    // save method (create)
    public void save(Event newEvent) {

        // 1. we ask the em to create a new transaction.
        EntityTransaction transaction = em.getTransaction();

        // 2. begin transaction.
        transaction.begin();

        // 3. add newEvent to the Persistence Context with persist (at this stage newEvent is still not saved in table in the DB)
        em.persist(newEvent);

        // 4. commit = write data in the DB
        transaction.commit();
        System.out.println("The event '" + newEvent.getTitle() + "' has been successfully saved in the DB.");
    }

    // findId method
    public Event findById(String eventId) {
        Event found = em.find(Event.class, UUID.fromString(eventId)); // Event.class to go look in the right table
        if (found == null)
            throw new NotFoundException(eventId);
        return found;
    }

    // 1. JPQL Methods
    public List<Concert> getConcertsInStreaming(boolean streaming) {
        // JPQL uses entities, not on the tables
        // SELECT all obj c of Concert type
        TypedQuery<Concert> query = em.createQuery("SELECT c FROM Concert c WHERE c.inStreaming = :isStreaming", Concert.class);
        // set the value to the parameter :isStreaming
        query.setParameter("isStreaming", streaming);
        // execute the query and return a list
        return query.getResultList();
    }

    public List<Concert> getConcertsByGenre(ConcertGenre genre) {
        TypedQuery<Concert> query = em.createQuery("SELECT c FROM Concert c WHERE c.genre = :genre", Concert.class);
        query.setParameter("genre", genre);
        return query.getResultList();
    }


}
