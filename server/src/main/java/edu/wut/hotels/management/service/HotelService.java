package edu.wut.hotels.management.service;

import edu.wut.hotels.management.database.entity.Hotel;
import edu.wut.hotels.management.database.entity.Room;
import edu.wut.hotels.management.database.repository.HotelRepository;
import edu.wut.hotels.management.database.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

//    @Transactional(readOnly = true)
//    public List<Room> findFreeRoomsByHotelId(long hotelId, Period timeRange) {
//        return roomRepository.findByBuildingId(buildingId);
//    }

@Service
@Transactional
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    @PersistenceUnit
    protected EntityManagerFactory emf;


    @Transactional
    public Optional<Hotel> findHotelById(long id) {
        return hotelRepository.findById(id);
    }

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
    public List<Hotel> getHotelsByUserSelection(String location, int stars, LocalDateTime startDate, LocalDateTime endDate, BigDecimal price) {
        EntityManager em = emf.createEntityManager();

        String queryString = "Select distinct h from hotels h " +
                "join h.buildings b " +
                "join b.rooms r " +
                "join r.prices p " +
                "join r.reservations rv " +
//                "where rv.checkInDate >= :startDate and rv.checkOutDate<= :endDate and p.value <= :price " +
                "where h.starsCount <= :stars and h.location like :location";
//                "where p.value <= :price " +

                Query query = em.createQuery(queryString, Hotel.class);
        //debug
        List result = query.setParameter("location", location)
                .setParameter("stars", stars)
//                .setParameter("startDate", startDate)
//                .setParameter("endDate", endDate)
//                .setParameter("price", price)
                .getResultList();

        return result;
    }

    @Transactional
    public List<Room> getRoomsInHotelByUserSelection(Long hotelId, LocalDateTime startDate, LocalDateTime endDate, BigDecimal price){
        EntityManager em = emf.createEntityManager();

        //todo: check reservations
        String queryString = "Select distinct r from hotels h " +
                "join h.buildings b " +
                "join b.rooms r " +
                "join r.prices p " +
//                "join r.reservations rv " +
                "where h.id = :hotelId " +
                "and p.value <= :price";
//                "and rv.checkInDate>= :startDate and rv.checkOutDate<= :endDate and p.value <= :price";
        Query query = em.createQuery(queryString, Room.class);
        //debug
        List result = query.setParameter("hotelId", hotelId)
//                .setParameter("startDate", startDate)
//                .setParameter("endDate", endDate)
                .setParameter("price", price)
                .setFirstResult(0)
                .setMaxResults(100)
                .getResultList();

        return result;
    }

    @Transactional(readOnly = true)
    public List<Room> findFreeRoomsForHotelAndParams(Long hotelId, LocalDateTime startDate, LocalDateTime endDate, BigDecimal price) {
        return roomRepository.findFreeRoomsForHotelAndParams(hotelId, startDate, endDate, price);
    }
}
