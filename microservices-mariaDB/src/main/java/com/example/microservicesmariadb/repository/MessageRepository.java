package com.example.microservicesmariadb.repository;

import com.example.microservicesmariadb.model.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * MessageRepository
 *
 * @author alex90bar
 */

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {

}


