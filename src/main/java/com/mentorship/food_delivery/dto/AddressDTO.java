package com.mentorship.food_delivery.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
 
    private String line1;        
    private String line2;        
    private String city;         
    private String state;        
    private String country;      
    private String zipcode;
}