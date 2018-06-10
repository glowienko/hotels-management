package edu.wut.hotels.management.database.repository;

import edu.wut.hotels.management.database.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long>, ClientRepositoryCustom {

    Optional<Client> getClientByEmail(String email);
}
