package com.studia.bd2.hotels.server.mapper;

import com.studia.bd2.hotels.server.database.entity.Client;
import com.studia.bd2.hotels.server.database.entity.ClientField;
import com.studia.bd2.hotels.server.web.rest.client.dto.ClientDto;
import com.studia.bd2.hotels.server.web.rest.client.dto.ClientFieldDto;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface ClientMapper {

    ClientDto toClientDto(Client client);
    Client toClientEntity(ClientDto dto);


    List<ClientFieldDto> toClientFiieldDtos(List<ClientField> clientFields);
    List<ClientField> toClientFiieldEntities(List<ClientFieldDto> dtos);

    ClientFieldDto toClientFieldDto(ClientField clientField);
    ClientField toClientFieldEntity(ClientFieldDto dto);
}
