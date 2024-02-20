package org.mccallum.repositories;

import org.mccallum.entities.ConversationEntity;
import org.mccallum.entities.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository  extends JpaRepository<MessageEntity, Long> {
}
