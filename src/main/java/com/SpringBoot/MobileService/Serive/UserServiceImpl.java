package com.SpringBoot.MobileService.Serive;

import com.SpringBoot.MobileService.Entity.UserEntity;
import com.SpringBoot.MobileService.Repository.UserRepository;
import com.SpringBoot.MobileService.Share.dto.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        //copy userDTo to userEntity
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDTO, userEntity);

        //this below two filed need to be generated ..right noe giving hard coded values
        userEntity.setEncryptedPassword("Test");
        userEntity.setUserId("TestUserId");
         //saving values
        UserEntity storedUser = userRepository.save(userEntity);

        //now we have to copy back to userDTo as return type is userDTO
        UserDTO returnValue=new UserDTO();
        BeanUtils.copyProperties(storedUser,returnValue);

        return returnValue;



    }
}