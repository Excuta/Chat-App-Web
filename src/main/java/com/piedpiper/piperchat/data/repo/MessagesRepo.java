package com.piedpiper.piperchat.data.repo;

import com.piedpiper.piperchat.data.model.message.Message;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created By: Yahia
 */
@Repository
@Primary
public interface MessagesRepo extends JpaRepository<Message, Long> {
}
