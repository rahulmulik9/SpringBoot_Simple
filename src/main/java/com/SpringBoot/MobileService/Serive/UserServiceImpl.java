package com.SpringBoot.MobileService.Serive;

import com.SpringBoot.MobileService.Entity.UserEntity;
import com.SpringBoot.MobileService.Repository.UserRepository;
import com.SpringBoot.MobileService.Share.dto.UserDTO;
import com.SpringBoot.MobileService.Share.dto.UsersUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UsersUtils usersUtils;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        //first check whether email is present or not
        UserEntity userFind = userRepository.findByEmail(userDTO.getEmail());

        if (userFind != null) {
            throw new RuntimeException("User is Present");
        }
        //generate random userId
        String randomUserID = usersUtils.generateUserId(30);

        //copy userDTo to userEntity
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDTO, userEntity);

        //adding userID to entity class
        userEntity.setUserId(randomUserID);

        //this below two filed need to be generated ..right noe giving hard coded values
        userEntity.setEncryptedPassword("Test");

        //saving values
        UserEntity storedUser = userRepository.save(userEntity);

        //now we have to copy back to userDTo as return type is userDTO
        UserDTO returnValue = new UserDTO();
        BeanUtils.copyProperties(storedUser, returnValue);

        return returnValue;


    }
}
