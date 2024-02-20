package org.mccallum.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Response", schema = "mysql")
@Entity
public class ResponseEntity {
    @Id
    private String responseId;

    @OneToOne
    @JoinColumn(name = "messageId", referencedColumnName = "messageId")
    private MessageEntity message;

    @Column
    private String content;
}
