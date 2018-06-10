SELECT DISTINCT rooms.*
FROM hotels
  JOIN buildings ON hotels.id = buildings.hotel_id
  JOIN rooms ON buildings.id = rooms.building_id
  JOIN rooms_reservations ON rooms.id = rooms_reservations.room_id
  JOIN reservations ON rooms_reservations.reservation_id = reservations.id
WHERE ((reservations.check_in_date <= CURRENT_DATE AND
        reservations.check_out_date <= DATE_ADD(CURRENT_DATE, INTERVAL 10 DAY)) OR
       (reservations.check_in_date >= CURRENT_DATE AND
        reservations.check_out_date >= DATE_ADD(CURRENT_DATE, INTERVAL 10 DAY))) AND
      hotels.id = 1;