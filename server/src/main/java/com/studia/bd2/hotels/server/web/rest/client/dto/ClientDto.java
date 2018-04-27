package com.studia.bd2.hotels.server.web.rest.client.dto;

import lombok.Data;

import java.util.List;

@Data
public class ClientDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private Boolean conference;

    private List<ClientFieldDto> clientFields;
}
