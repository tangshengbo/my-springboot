package com.tangshengbo.service.impl;

import com.tangshengbo.model.HttpLog;
import com.tangshengbo.repository.HttpLogMongoRepository;
import com.tangshengbo.service.LogService;
import com.tangshengbo.util.QueryString;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 * Created by Tangshengbo on 2018/3/5.
 */
@Service
public class LogServiceImpl implements LogService {

    private static Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);

    private static final String HTTP_LOG_ID = "HTTP_LOG_ID";

    private static final long DELTA= 1;

    @Autowired
    private HttpLogMongoRepository repository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<HttpLog> listHttpLog() {
        return repository.findAll();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void saveHttpLog(HttpLog httpLog) {
        httpLog.setLogId(redisTemplate.opsForValue().increment(HTTP_LOG_ID,  DELTA));
//        String address = getAddressByIp(httpLog.getClientIp());
//        httpLog.setClientAddress(address);
        httpLog.setCreateDate(new Date());
        repository.save(httpLog);
    }

    private String getAddressByIp(String ip) {
        QueryString queryString = new QueryString();
        queryString.add("ip", ip);
        queryString.add("action", "1");
        String address = "";
        try {
            Document doc = Jsoup.parse(new URL("http://www.ip138.com/ips1388.asp?" + queryString), 5000);
            Elements elements = doc.select("ul.ul1 li");
            address = elements.get(0).text();
            address = address.substring(address.indexOf("：") + 1);
            logger.info("{}", address);
        } catch (Exception e) {
            logger.error("{}", ExceptionUtils.getStackTrace(e));
        }
        return address;
    }

    @Override
    public void complementIpAddress() {
        logger.info("定时任务开始.......");
//        List<HttpLog> httpLogList = logMapper.listByNullAddress();
//        httpLogList.forEach(httpLog -> {
//            httpLog.setClientAddress(getAddressByIp(httpLog.getClientIp()));
//            logMapper.updateByPrimaryKeySelective(httpLog);
//
//        });
        logger.info("定时任务结束.......");
    }
}
