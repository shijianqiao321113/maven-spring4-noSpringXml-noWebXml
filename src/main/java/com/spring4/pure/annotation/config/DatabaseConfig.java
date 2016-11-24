package com.spring4.pure.annotation.config;

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
  
    @Bean(name = "druidDataSource")  
    public DataSource druidDataSource() {  
    	DruidDataSource dataSource = new DruidDataSource();
    	dataSource.setDriverClassName(this.jdbcDriver);
    	dataSource.setUrl(this.dbUrl);
    	dataSource.setUsername(this.username);
    	dataSource.setPassword(this.password);
        return dataSource;  
    }  
  
    @Bean(name = "druidTransactionManager")
    public DataSourceTransactionManager druidTransactionManager() {
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