SELECT rooms.*
FROM hotels
  JOIN buildings ON hotels.id = buildings.hotel_id
  JOIN rooms on buildings.id = rooms.building_id
WHERE
  rooms.id NOT IN (
    SELECT rooms.id
    FROM rooms
      JOIN rooms_reservations ON rooms.id = rooms_reservations.room_id
      JOIN reservations ON rooms_reservations.reservation_id = reservations.id
    WHERE
      CURRENT_DATE BETWEEN reservations.check_in_date AND reservations.check_out_date
  ) AND
  hotels.id = 1;