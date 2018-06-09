package edu.wut.hotels.management.service;

import edu.wut.hotels.management.database.entity.Hotel;
import edu.wut.hotels.management.database.entity.Room;
import edu.wut.hotels.management.database.repository.HotelRepository;
import edu.wut.hotels.management.database.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    @PersistenceUnit
    protected EntityManagerFactory emf;

    @Transactional(readOnly = true)
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Room> findRoomsByBuildingId(long buildingId) {
        return roomRepository.findByBuildingId(buildingId);
    }

    @Transactional(readOnly = true)
    public List<Hotel> findHotelsByLocationAndStars(String location, int stars) {
        return hotelRepository.findAllByLocationAndStarsCount(location, stars);
    }


    @Transactional(readOnly = true)
    public List<Hotel> getHotelsByUserSelection(String location, int stars, String startDateStr, String endDateStr, BigDecimal price) {
        EntityManager em = emf.createEntityManager();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        startDateStr += " 00:00:00";
        endDateStr += " 00:00:00";

        LocalDateTime startDate = LocalDateTime.parse(startDateStr, format);
        LocalDateTime endDate = LocalDateTime.parse(endDateStr, format);

        String queryString = "Select distinct h from hotels h join h.buildings b " +
                "join b.rooms r " +
                "join r.prices p " +
                "where p.startDate >= :startDate and p.endDate <= :endDate and p.value <= :price " +
                "and h.starsCount <= :stars and h.location like :location";

        Query query = em.createQuery(queryString, Hotel.class);
        //debug
        List result = query.setParameter("location", location)
                .setParameter("stars", stars)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .setParameter("price", price)
                .getResultList();

        return result;
    }

    @Transactional
    public List<Room> getRoomsInHotelByUserSelection(Long hotelId, String startDateStr, String endDateStr, BigDecimal price){
        EntityManager em = emf.createEntityManager();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        startDateStr += " 00:00:00";
        endDateStr += " 00:00:00";

        LocalDateTime startDate = LocalDateTime.parse(startDateStr, format);
        LocalDateTime endDate = LocalDateTime.parse(endDateStr, format);

        //todo: get discounts and check reservations
        String queryString = "Select distinct r from hotels h join h.buildings b " +
                "join b.rooms r " +
                "join r.prices p " +
                "where h.id = :hotelId " +
                "and p.startDate <= :startDate and p.endDate >= :endDate and p.value <= :price";
        Query query = em.createQuery(queryString, Room.class);
        //debug
        List result = query.setParameter("hotelId", hotelId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .setParameter("price", price)
                .setFirstResult(0)
                .setMaxResults(100)
                .getResultList();

        return result;
    }
}
