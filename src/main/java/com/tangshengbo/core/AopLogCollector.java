package com.tangshengbo.core;

import com.alibaba.fastjson.JSON;
import com.github.LogData;
import com.github.collector.LogCollector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Tangshengbo
 *
 * @author Tangshengbo
 * @date 2020/9/24
 */
@Component
public class AopLogCollector implements LogCollector {

    private static final Logger logger = LoggerFactory.getLogger(AopLogCollector.class);

    @Override
    public void collect(LogData data) {
        logger.info(JSON.toJSONString(data));
    }
}
