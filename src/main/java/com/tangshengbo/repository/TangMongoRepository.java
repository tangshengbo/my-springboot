package com.tangshengbo.repository;

import com.tangshengbo.model.Tang;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Tangshengbo on 2019/3/25
 */
public interface TangMongoRepository extends MongoRepository<Tang, Long> {
}
