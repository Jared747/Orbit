package org.mccallum.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Message", schema = "mysql")
@Entity
public class MessageEntity {
    @Id
    private String messageId;

    @ManyToOne
    @JoinColumn(name = "conversationId", referencedColumnName = "conversationId")
    private ConversationEntity conversation;

    @Column
    private String content;

    @Column
    private String timestamp;
}
