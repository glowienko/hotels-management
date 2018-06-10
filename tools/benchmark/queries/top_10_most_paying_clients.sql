SELECT clients.id, SUM(reservations.cost)
FROM clients
  JOIN reservations ON clients.id = reservations.client_id
GROUP BY clients.id
ORDER BY SUM(reservations.cost) DESC
LIMIT 10;