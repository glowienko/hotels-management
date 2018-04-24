package com.studia.bd2.hotels.server.web.rest.client.dto;

import com.studia.bd2.hotels.server.database.entity.ClientFieldType;
import lombok.Data;

@Data
public class ClientFieldDto {
    private ClientFieldType type;
    private String value;
}
