SELECT AVG(aggregate.sum)
FROM (
  SELECT SUM(DATEDIFF(reservations.check_out_date, reservations.check_in_date)) as sum
  FROM rooms
    JOIN rooms_reservations on rooms.id = rooms_reservations.room_id
    JOIN reservations on rooms_reservations.reservation_id = reservations.id
  GROUP BY rooms.id
) AS aggregate;