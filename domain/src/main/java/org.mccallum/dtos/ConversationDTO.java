package org.mccallum.dtos;

import lombok.*;
import org.mccallum.enums.ConversationStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConversationDTO {
    private String conversationId;
    private UserDTO user;
    private LocalDateTime startTime;
    private LocalDate endTime;
    private ConversationStatus status;
}
