package edu.wut.hotels.management.web.rest.client.dto;

import edu.wut.hotels.management.database.entity.ClientFieldType;
import lombok.Data;

@Data
public class ClientFieldDto {
    private ClientFieldType type;
    private String value;
}
