package edu.wut.hotels.management.database.repository;

import edu.wut.hotels.management.database.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ClientRepositoryImpl implements ClientRepositoryCustom {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    public List<Client> findBestClientsInYear(int year, int numberOfClients) {
        EntityManager em = emf.createEntityManager();
        String query = "Select distinct c from clients c " +
                "join reservations r ON r.client.id = c.id " +
                "where year(r.checkInDate) = :year " +
                "GROUP BY c.id " +
                "order by sum(r.cost) desc" ;

        TypedQuery<Client> q = em.createQuery(query, Client.class);
        q.setParameter("year", year);
        q.setMaxResults(numberOfClients);

        return q.getResultList();
    }

    @Override
    public List<Client> findBestClientsSpendingMuchTimeInYear(int year, int numberOfClients, String dateFrom, String dateTo) {
        EntityManager em = emf.createEntityManager();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateFromL = LocalDateTime.parse(dateFrom, format);
        LocalDateTime dateToL = LocalDateTime.parse(dateTo, format);

        String query = "Select distinct c from clients c " +
                "join reservations r ON r.client.id = c.id " +
                "where year(r.checkInDate) = :year " +
                    "AND  (r.checkInDate BETWEEN :dateFrom AND :dateTo) " +
                    "AND  (r.checkOutDate BETWEEN :dateFrom AND :dateTo) " +
                "GROUP BY c.id " +
                "order by sum(datediff(r.checkOutDate, r.checkInDate)) desc" ;

        TypedQuery<Client> q = em.createQuery(query, Client.class);
        q.setParameter("year", year);
        q.setParameter("dateFrom", dateFromL);
        q.setParameter("dateTo", dateToL);
        q.setMaxResults(numberOfClients);

        return q.getResultList();
    }

}
