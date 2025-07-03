package ivancroce.dao;

import ivancroce.entities.Location;
import ivancroce.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class LocationsDAO {
    // em is our "contact" with the DB
    private EntityManager em;

    public LocationsDAO(EntityManager em) {
        this.em = em;
    }

    // save method (create)
    public void save(Location newLocation) {

        // 1. we ask the em to create a new transaction.
        EntityTransaction transaction = em.getTransaction();

        // 2. begin transaction.
        transaction.begin();

        // 3. add newEvent to the Persistence Context with persist (at this stage newEvent is still not saved in table in the DB)
        em.persist(newLocation);

        // 4. commit = write data in the DB
        transaction.commit();
        System.out.println("The Location '" + newLocation.getName() + "' has been successfully saved in the DB.");
    }

    // findId method
    public Location findById(String locationId) {
        Location found = em.find(Location.class, UUID.fromString(locationId)); // Location.class to go look in the right table
        if (found == null)
            throw new NotFoundException(locationId);
        return found;
    }
}
