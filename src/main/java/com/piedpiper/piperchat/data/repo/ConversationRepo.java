package com.piedpiper.piperchat.data.repo;

import com.piedpiper.piperchat.data.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created By: Yahia
 */
@Repository
public interface ConversationRepo extends JpaRepository<Conversation, Long> {
}
