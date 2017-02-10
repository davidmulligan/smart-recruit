package com.eureka.smartrecruit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private String street1;
    private String street2;
    private String street3;
    private String town;
    private String county;
    private String postCode;
}