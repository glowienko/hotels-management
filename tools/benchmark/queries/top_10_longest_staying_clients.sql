SELECT clients.id, SUM(DATEDIFF(reservations.check_out_date, reservations.check_in_date))
FROM clients
  JOIN reservations ON clients.id = reservations.client_id
GROUP BY clients.id
ORDER BY SUM(DATEDIFF(reservations.check_out_date, reservations.check_in_date)) DESC
LIMIT 10;