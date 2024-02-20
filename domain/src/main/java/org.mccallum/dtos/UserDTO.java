package org.mccallum.dtos;

import lombok.*;
import org.mccallum.entities.UserEntity;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String id;
    private String phoneNumber;
}
