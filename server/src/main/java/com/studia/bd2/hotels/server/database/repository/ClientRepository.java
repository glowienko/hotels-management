package com.studia.bd2.hotels.server.database.repository;

import com.studia.bd2.hotels.server.database.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
