package edu.wut.hotels.management.service;

import edu.wut.hotels.management.database.entity.Reservation;
import edu.wut.hotels.management.database.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public Reservation makeReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
}
