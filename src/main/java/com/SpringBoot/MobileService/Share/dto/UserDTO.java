package com.SpringBoot.MobileService.Share.dto;
//dto data transfer object

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 6337791228005433929L;
    private long id;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String encryptedPassword;
    private String emailVerificationToken;
    private Boolean emailVerificationStatus=false;
  //  private List<AddressDTO> addresses;

}
