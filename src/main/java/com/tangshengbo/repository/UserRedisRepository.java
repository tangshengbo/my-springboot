package com.tangshengbo.repository;

import com.tangshengbo.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Tangshengbo on 2019/3/26
 */
public interface UserRedisRepository extends CrudRepository<User, String> {
}
