package com.SpringBoot.MobileService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//User response model

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRest {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;

}
