package ivancroce.dao;

import ivancroce.entities.Person;
import ivancroce.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class PersonsDAO {
    // em is our "contact" with the DB
    private EntityManager em;

    public PersonsDAO(EntityManager em) {
        this.em = em;
    }

    // save method (create)
    public void save(Person newPerson) {

        // 1. we ask the em to create a new transaction.
        EntityTransaction transaction = em.getTransaction();

        // 2. begin transaction.
        transaction.begin();

        // 3. add newEvent to the Persistence Context with persist (at this stage newEvent is still not saved in table in the DB)
        em.persist(newPerson);

        // 4. commit = write data in the DB
        transaction.commit();
        System.out.println("The Person '" + newPerson.getName() + "' has been successfully saved in the DB.");
    }

    // findId method
    public Person findById(String personId) {
        Person found = em.find(Person.class, UUID.fromString(personId)); // Person.class to go look in the right table
        if (found == null)
            throw new NotFoundException(personId);
        return found;
    }
}

