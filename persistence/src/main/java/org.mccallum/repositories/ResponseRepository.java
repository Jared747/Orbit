package org.mccallum.repositories;

import org.mccallum.entities.ConversationEntity;
import org.mccallum.entities.ResponseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepository  extends JpaRepository<ResponseEntity, Long> {
}
