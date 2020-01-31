package com.shm.core.domain.repository.user;

import com.shm.core.domain.entity.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User getByMobile(String mobile);

    List<User> findAll();

    User findByMobileAndPassword(String mobile, String password);
}
