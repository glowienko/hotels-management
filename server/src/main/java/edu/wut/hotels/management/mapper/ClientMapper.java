package edu.wut.hotels.management.mapper;

import edu.wut.hotels.management.database.entity.Client;
import edu.wut.hotels.management.database.entity.ClientField;
import edu.wut.hotels.management.web.rest.client.dto.ClientDto;
import edu.wut.hotels.management.web.rest.client.dto.ClientFieldDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface ClientMapper {

    ClientDto toClientDto(Client client);

    @Mapping(target = "conference", expression = "java( (dto.getConference() == null || dto.getConference() == false) ? false : true)")
    Client toClientEntity(ClientDto dto);


    List<ClientFieldDto> toClientFiieldDtos(List<ClientField> clientFields);
    List<ClientField> toClientFiieldEntities(List<ClientFieldDto> dtos);

    ClientFieldDto toClientFieldDto(ClientField clientField);
    ClientField toClientFieldEntity(ClientFieldDto dto);
}
