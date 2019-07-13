package com.piedpiper.piperchat.data.repo;

import com.piedpiper.piperchat.data.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created By: Yahia
 */
@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    boolean existsByEmail(String email);
}
