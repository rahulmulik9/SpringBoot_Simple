package com.SpringBoot.MobileService.Serive;

import com.SpringBoot.MobileService.Entity.UserEntity;
import com.SpringBoot.MobileService.Repository.UserRepository;
import com.SpringBoot.MobileService.Share.dto.UserDTO;
import com.SpringBoot.MobileService.Share.dto.UsersUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service

public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UsersUtils usersUtils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        // 1] first check whether email is present or not
        UserEntity userFind = userRepository.findByEmail(userDTO.getEmail());
        if (userFind != null) {
            throw new RuntimeException("User is Present");
        }

        //generate random userId
        String randomUserID = usersUtils.generateUserId(30);

        //2] copy userDTo to userEntity
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDTO, userEntity);

        //adding userID to entity class
        userEntity.setUserId(randomUserID);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));

        //3] saving values
        UserEntity storedUser = userRepository.save(userEntity);

        //4] now we have to copy back to userDTo as return type is userDTO
        UserDTO returnValue = new UserDTO();
        BeanUtils.copyProperties(storedUser, returnValue);

        return returnValue;

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null)
            throw new UsernameNotFoundException(email);
     //this User is from spring security
        return new User(userEntity.getEmail(),userEntity.getEncryptedPassword(),new ArrayList<>());

    }
}
