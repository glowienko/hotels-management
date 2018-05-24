package edu.wut.hotels.management.database.repository;

import edu.wut.hotels.management.database.entity.ClientField;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientFieldsRepository extends JpaRepository<ClientField, Long> {
}
