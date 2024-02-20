package org.mccallum.controllers;

import org.mccallum.dtos.UserDTO;
import org.mccallum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/*This controller is expected to manage user-related functionalities such as creating new users,
    authenticating users, updating user information, and deleting users.

It could handle requests related to user accounts, profiles, and possibly user preferences or settings.
 */

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO){
        UserDTO newUser = userService.createUser(userService.createUserEntity(userDTO));
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

}
