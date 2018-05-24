package edu.wut.hotels.management.database.repository;

import edu.wut.hotels.management.database.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
