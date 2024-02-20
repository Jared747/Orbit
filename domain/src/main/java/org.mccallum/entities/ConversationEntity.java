package org.mccallum.entities;

import lombok.*;
import org.mccallum.enums.ConversationStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Conversation", schema = "mysql")
@Entity
public class ConversationEntity{
    @Id
    private String conversationId;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private UserEntity user;

    @Column
    private LocalDateTime startTime;

    @Column
    private LocalDateTime endTime;

}

