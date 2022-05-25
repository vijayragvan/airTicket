package com.air.broker.payload;

import lombok.Data;

@Data
public class SignUpDto {
    private String user_name;
    private String company_name;
    private Long mobile_number;
    private String email;
    private String password;
    private String role;
    private String country;
    private String state;
    private String city;
    private String address;
    private String pin_code;
    private String pan_number;
}
