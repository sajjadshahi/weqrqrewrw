package com.shm.core.domain.repository.user;

import com.shm.core.domain.entity.user.Log;
import org.springframework.data.repository.CrudRepository;

public interface LogRepository extends CrudRepository<Log, Long> {
}
