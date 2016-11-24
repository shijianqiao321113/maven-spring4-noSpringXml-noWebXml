package com.spring4.pure.annotation.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@MapperScan("com.spring4.pure.annotation.dao.mapper")
public class DatabaseConfig{  
    @Value("${jdbc.driver}")  
    private String jdbcDriver;  
  
    @Value("${db.url}")  
    private String dbUrl;  
  
    @Value("${db.username}")  
    private String username;  
  
    @Value("${db.password}")  
    private String password;
    
    @Value("${druid.filters}")
    private String filters;
    @Value("${druid.initialSize}")
    private Integer initialSize;
    @Value("${druid.maxActive}")
    private Integer maxActive;
    @Value("${druid.minIdle}")
    private Integer minIdle;
    @Value("${druid.maxWait}")
    private Long maxWait;
    @Value("${druid.useUnfairLock}")
    private Boolean useUnfairLock;
    @Value("${druid.testWhileIdle}")
    private Boolean testWhileIdle;
    @Value("${druid.timeBetweenEvictionRunsMillis}")
    private Long timeBetweenEvictionRunsMillis;
    @Value("${druid.validationQuery}")
    private String validationQuery;
    @Value("${druid.minEvictableIdleTimeMillis}")
    private Long minEvictableIdleTimeMillis;
    @Value("${druid.testOnBorrow}")
    private Boolean testOnBorrow;
    @Value("${druid.testOnReturn}")
    private Boolean testOnReturn;
    @Value("${druid.poolPreparedStatements}")
    private Boolean poolPreparedStatements;
    @Value("${druid.maxPoolPreparedStatementPerConnectionSize}")
    private Integer maxPoolPreparedStatementPerConnectionSize;
    @Value("${druid.removeAbandoned}")
    private Boolean removeAbandoned;
    @Value("${druid.removeAbandonedTimeout}")
    private Integer removeAbandonedTimeout;
    @Value("${druid.logAbandoned}")
    private Boolean logAbandoned;
    
  
    @Bean(name = "druidDataSource")  
    public DataSource druidDataSource() throws SQLException {  
    	DruidDataSource dataSource = new DruidDataSource();
    	dataSource.setDriverClassName(this.jdbcDriver);
    	dataSource.setUrl(this.dbUrl);
    	dataSource.setUsername(this.username);
    	dataSource.setPassword(this.password);
    	
    	dataSource.setFilters(this.filters);
    	dataSource.setInitialSize(this.initialSize);
    	dataSource.setMaxActive(this.maxActive);
    	dataSource.setMinIdle(this.minIdle);
    	dataSource.setMaxWait(this.maxWait);
    	dataSource.setUseUnfairLock(this.useUnfairLock);
    	dataSource.setTestWhileIdle(this.testWhileIdle);
    	dataSource.setTimeBetweenEvictionRunsMillis(this.timeBetweenEvictionRunsMillis);
    	dataSource.setValidationQuery(this.validationQuery);
    	dataSource.setMinEvictableIdleTimeMillis(this.minEvictableIdleTimeMillis);
    	dataSource.setTestOnBorrow(this.testOnBorrow);
    	dataSource.setTestOnReturn(this.testOnReturn);
    	dataSource.setPoolPreparedStatements(this.poolPreparedStatements);
    	dataSource.setMaxPoolPreparedStatementPerConnectionSize(this.maxPoolPreparedStatementPerConnectionSize);
    	dataSource.setRemoveAbandoned(this.removeAbandoned);
    	dataSource.setRemoveAbandonedTimeout(this.removeAbandonedTimeout);
    	dataSource.setLogAbandoned(this.logAbandoned);
        return dataSource;  
    }  
  
    @Bean(name = "druidTransactionManager")
    public DataSourceTransactionManager druidTransactionManager() throws SQLException {
        return new DataSourceTransactionManager(druidDataSource());
    }
  
    @Bean(name = "druidSqlSessionFactory")
    public SqlSessionFactory druidSqlSessionFactory() throws Exception {  
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();  
        sessionFactory.setDataSource(druidDataSource());
        sessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml"));
        return sessionFactory.getObject();  
    }
}  