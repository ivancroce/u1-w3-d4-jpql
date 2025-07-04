package ivancroce.dao;

import ivancroce.entities.Participation;
import ivancroce.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class ParticipationsDAO {
    // em is our "contact" with the DB
    private EntityManager em;

    public ParticipationsDAO(EntityManager em) {
        this.em = em;
    }

    // save method (create)
    public void save(Participation newParticipation) {

        // 1. we ask the em to create a new transaction.
        EntityTransaction transaction = em.getTransaction();

        // 2. begin transaction.
        transaction.begin();

        // 3. add newEvent to the Persistence Context with persist (at this stage newEvent is still not saved in table in the DB)
        em.persist(newParticipation);

        // 4. commit = write data in the DB
        transaction.commit();
        System.out.println("The Participation '" + newParticipation.getParticipationId() + "' has been successfully saved in the DB.");
    }

    // findId method
    public Participation findById(String participationId) {
        Participation found = em.find(Participation.class, UUID.fromString(participationId)); // Participation.class to go look in the right table
        if (found == null)
            throw new NotFoundException(participationId);
        return found;
    }

    public void findByIdAndDelete(String participationId) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            Participation found = this.findById(participationId);
            em.remove(found);
            t.commit();
            System.out.println("Participation deleted.");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

