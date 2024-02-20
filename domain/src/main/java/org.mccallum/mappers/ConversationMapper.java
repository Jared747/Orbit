package org.mccallum.mappers;

import org.mccallum.dtos.ConversationDTO;
import org.mccallum.dtos.UserDTO;
import org.mccallum.entities.ConversationEntity;
import org.mccallum.entities.UserEntity;
import org.mccallum.enums.ConversationStatus;

public class ConversationMapper {

    public static ConversationEntity convertDtoToEntity(ConversationDTO dto) {
        if (dto == null) {
            return null;
        }

        ConversationEntity entity = ConversationEntity.builder()
                .conversationId(dto.getConversationId())
                .user(convertUserDtoToEntity(dto.getUser()))
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime() == null ? null : dto.getEndTime().atStartOfDay()) // Adjusting LocalDate to LocalDateTime
                .build();

        return entity;
    }

    // Assuming this method exists alongside the previous one in the same class
    public static ConversationDTO convertEntityToDto(ConversationEntity entity) {
        if (entity == null) {
            return null;
        }

        ConversationDTO dto = ConversationDTO.builder()
                .conversationId(entity.getConversationId())
                .user(convertUserEntityToDto(entity.getUser())) // Assuming there's a method for User conversion
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime() == null ? null : entity.getEndTime().toLocalDate()) // Converting LocalDateTime to LocalDate
                //.status(ConversationStatus.valueOf(entity.getStatus().name())) // Assuming the enum names are the same
                .build();

        return dto;
    }

    // Placeholder for the user conversion method
    private static UserDTO convertUserEntityToDto(UserEntity userEntity) {
        // Implement user entity to DTO conversion logic here
        return null; // Return actual UserDTO after conversion
    }

    private static UserEntity convertUserDtoToEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        return UserEntity.builder()
                .userId(userDTO.getId())
                .phoneNumber(userDTO.getPhoneNumber())
                .build();
    }

}

