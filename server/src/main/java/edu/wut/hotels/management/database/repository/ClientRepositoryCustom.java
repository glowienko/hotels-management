package edu.wut.hotels.management.database.repository;

import edu.wut.hotels.management.database.entity.Client;

import java.util.List;

public interface ClientRepositoryCustom {
    List<Client> findBestClientsInYear(int year, int numberOfClients);
    List<Client> findBestClientsSpendingMuchTimeInYear(int year, int numberOfClients, String dateFrom, String dateTo);
}
