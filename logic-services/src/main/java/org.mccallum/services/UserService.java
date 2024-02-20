package org.mccallum.services;


import org.mccallum.dtos.ConversationDTO;
import org.mccallum.dtos.UserDTO;
import org.mccallum.entities.ConversationEntity;
import org.mccallum.entities.UserEntity;
import org.mccallum.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
public class UserService {

    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) throws FileNotFoundException
    {
        this.userRepository = userRepository;
    }

    public UserDTO createUser(UserEntity userEntity)
    {
        return UserDTO.builder()
                .id(userEntity.getUserId())
                .phoneNumber(userEntity.getPhoneNumber())
                .build();
    }


    public UserEntity createUserEntity(UserDTO userDTO)
    {
        UserEntity newUser = UserEntity.builder()
                .userId(userDTO.getId())
                .phoneNumber(userDTO.getPhoneNumber())
                .build();
        var savedUser = userRepository.save(newUser);

        return savedUser;
    }
}

