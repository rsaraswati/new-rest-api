package io.egensolutions.repository;

import io.egensolutions.entities.readings.Reading;
import io.egensolutions.entities.readings.Tyre;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class ReadingsRepositoryImplementation implements ReadingsRepository{

    ReadingsRepositoryImplementation() {
        System.out.println("Reading repository");
    }
    @PersistenceContext
    private EntityManager entityManager;

    public List<Reading> findAllReadings() {
    TypedQuery<Reading> query = entityManager.createNamedQuery("Reading.findAllReadings", Reading.class);
        return query.getResultList();
    }

    public Reading findOneReading(String vin) {
        return entityManager.find(Reading.class,vin);
    }

    public Reading createReading(Reading reading) {

        try {
            //entityManager.persist(t);
            //reading.setTyre(t);
            entityManager.persist(reading);
        }catch(Exception e){
            System.out.println("Error in reading repository implementation");;
        }
        return reading;
    }

    public Reading findByTimestamp(Timestamp timestamp) {
        TypedQuery<Reading> query = entityManager.createNamedQuery("Reading.findByTimestamp", Reading.class);
        query.setParameter("timestampParameter", timestamp);
        List<Reading> resultList = query.getResultList();
        if (resultList != null && resultList.size() == 1) {
            return resultList.get(0);
        } else {
            return null;
        }
    }

    public Reading updateReading(Reading reading) {
        return entityManager.merge(reading);
    }

    public void deleteReading(String vin) {
        entityManager.remove(entityManager.find(Reading.class,vin));
    }

}
