package com.SpringBoot.MobileService.Serive;

import com.SpringBoot.MobileService.Share.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

     UserDTO createUser(UserDTO userDTO);
}
