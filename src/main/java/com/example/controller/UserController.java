package com.example.controller;

import com.example.dto.UserDTO;
import com.example.exception.ResourceNotFoundException;
import com.example.model.UserModel;
import com.example.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tables")
@Api(value = "UserController - catch all url linking with user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users")
    @ApiOperation(value = "Add User")
    public int addUser(@RequestBody UserDTO userDto) {
        String username = userDto.getName();
        String email = userDto.getEmail();
        UserModel userModel = new UserModel(username, email);
        return this.userService.addUser(userModel);
    }

    @GetMapping("/users/{id}")
    @ApiOperation(value = "Get User by Id")
    public UserDTO getUserById(@PathVariable(value = "id") Integer userId)
            throws ResourceNotFoundException {
        UserModel userModel = this.userService.getUserById(userId);
        String name = userModel.getName();
        String email = userModel.getEmail();
        UserDTO userDto = new UserDTO(name, email);
        return userDto;
    }

}
