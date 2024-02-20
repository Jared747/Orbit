package org.mccallum.repositories;

import org.mccallum.entities.ConversationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConversationRepository extends JpaRepository<ConversationEntity, Long> {

}
