package com.SpringBoot.MobileService.controller;

import com.SpringBoot.MobileService.Serive.UserService;
import com.SpringBoot.MobileService.Serive.UserServiceImpl;
import com.SpringBoot.MobileService.Share.dto.UserDTO;
import com.SpringBoot.MobileService.model.UserRequestDetails;
import com.SpringBoot.MobileService.model.UserRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    UserServiceImpl UserServiceImpl;

    public UserController(com.SpringBoot.MobileService.Serive.UserServiceImpl userServiceImpl) {
        UserServiceImpl = userServiceImpl;
    }

    @GetMapping
    public String getUser(){
        return "Rahul get";
    }
    @PostMapping
    public UserRest createUser(@RequestBody UserRequestDetails user){
       // return new UserResponseDetails("FE@#","Rahul","Mulik","rahul@gmail.com");

        UserRest userReturn = new UserRest();
        //create object of DTO class then copy incoming data of UserRequestDetails (user) to that class object userReturn
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user,userDTO);


        //the value which coming from service class has been store into again DTO class then copy this to UserRest which is used as User response
        UserDTO createduser = UserServiceImpl.createUser(userDTO);
        BeanUtils.copyProperties(createduser,userReturn);
        return userReturn;

    }
    @PutMapping
    public String uploadUser(){
        return "Rahul uploaded";
    }
    @DeleteMapping
    public String deletUser(){
        return "Rahul deleted";
    }



}
