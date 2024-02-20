package org.mccallum.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Users", schema = "mysql")
@Entity
public class UserEntity {
    @Id
    private String userId;

    @Column
    private String phoneNumber;
}

