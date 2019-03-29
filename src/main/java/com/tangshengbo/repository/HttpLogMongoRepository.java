package com.tangshengbo.repository;

import com.tangshengbo.model.HttpLog;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by TangShengBo on 2019/3/29
 */
public interface HttpLogMongoRepository extends MongoRepository<HttpLog, Long> {


}
