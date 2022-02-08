package com.bank.hdfc.dbservice;

import lombok.extern.slf4j.Slf4j;
import oracle.nosql.driver.AuthorizationProvider;
import oracle.nosql.driver.NoSQLHandle;
import oracle.nosql.driver.NoSQLHandleConfig;
import oracle.nosql.driver.NoSQLHandleFactory;
import oracle.nosql.driver.kv.StoreAccessTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DBFactory {

    //public Logger log = LoggerFactory.getLogger(DBFactory.class);

    @Autowired
    private Environment environment;
    private NoSQLHandle handle;

    public NoSQLHandle getHandle() {
        if (handle == null) {
            String endpoint = environment.getProperty("spring.nosql.endpoint");
            String poolSize = environment.getProperty("spring.nosql.poolSize");
            log.info("Entered DBFactory.getHandle() method and connecting to the following nosql endpoint: ".concat(endpoint));
            NoSQLHandleConfig config = new NoSQLHandleConfig(endpoint);
            config.setAuthorizationProvider(getAuthProvider());
            config.setConnectionPoolSize(Integer.parseInt(poolSize));
            handle = NoSQLHandleFactory.createNoSQLHandle(config);
        }
        return handle;
    }

    AuthorizationProvider getAuthProvider() {
        return new StoreAccessTokenProvider();
    }

}
