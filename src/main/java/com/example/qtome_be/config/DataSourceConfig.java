package com.example.qtome_be.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class DataSourceConfig {
    private AtomicInteger counter = new AtomicInteger(-1);

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slave1")
    public DataSource slave1DataSource() {
        return DataSourceBuilder
                .create()
                .type(HikariDataSource.class)
                .build();
    }
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slave2")
    public DataSource slave2DataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean
    @Primary
    public DataSource dataSource() {
        RoutingDataSource routingDataSource = new RoutingDataSource();

        DataSource masterDataSource = masterDataSource();
        DataSource slaveDataSource1 = slave1DataSource();
        DataSource slaveDataSource2 = slave2DataSource();

        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DbRole.MASTER, masterDataSource);
        dataSourceMap.put(DbRole.SLAVE1, slaveDataSource1);
        dataSourceMap.put(DbRole.SLAVE2, slaveDataSource2);

        routingDataSource.setTargetDataSources(dataSourceMap);
        routingDataSource.setDefaultTargetDataSource(masterDataSource);

        return routingDataSource;
    }

    public enum DbRole {
        MASTER, SLAVE1, SLAVE2
    }

    public class RoutingDataSource extends AbstractRoutingDataSource {
        @Override
        protected Object determineCurrentLookupKey() {
            if (TransactionSynchronizationManager.isCurrentTransactionReadOnly()) {
                int index = counter.incrementAndGet() % 2;
                System.out.println("slave");

                return index == 0 ? DbRole.SLAVE1 : DbRole.SLAVE2;
            } else {
                System.out.println("master");
                return DbRole.MASTER;
            }
        }
    }
}
