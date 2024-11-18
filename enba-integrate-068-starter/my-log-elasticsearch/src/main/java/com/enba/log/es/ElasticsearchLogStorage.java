package com.enba.log.es;

import com.enba.log.core.LogStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Component;

@Component
public class ElasticsearchLogStorage implements LogStorage {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Override
    public void storeLog(String logMessage) {
        LogDocument logDocument = new LogDocument(logMessage);
        elasticsearchOperations.save(logDocument);
    }
}

