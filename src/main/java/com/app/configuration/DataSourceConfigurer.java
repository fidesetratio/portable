package com.app.configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.ExecutorType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.transaction.PlatformTransactionManager;

import com.app.common.DataSourceKey;

@Configuration
public class DataSourceConfigurer {

	/**
     * master DataSource
     *
     * @return data source
     */
    @Bean("master")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.hikari.master")
    public DataSource master() {
        return DataSourceBuilder.create().build();
    }
    
        
    
    
    /**
     * Dynamic data source.
     *
     * @return the data source
     */
    @Bean("dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>(4);
        dataSourceMap.put(DataSourceKey.master.name(), master());
            // Set master datasource as default
        dynamicRoutingDataSource.setDefaultTargetDataSource(master());
        // Set master and slave datasource as target datasource
        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);

       return dynamicRoutingDataSource;
    }

    /** mybatis configuration **/
    @Bean
    @ConfigurationProperties(prefix = "mybatis")
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // Here is very important, if don't config this, will can't switch datasource
        // put all datasource into SqlSessionFactoryBean, then will autoconfig SqlSessionFactory
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("sqlmapconfig.xml"));
        return sqlSessionFactoryBean;
    }
    
    
    
    
    
    /**
     * Transaction manager platform transaction manager.
     *
     * @return the platform transaction manager
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }

	  @Bean
	  public SqlSessionTemplate sqlSession(SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception {
	    return new SqlSessionTemplate(sqlSessionFactoryBean.getObject(),ExecutorType.BATCH);
	  }

	  
	@Bean
	public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dynamicDataSource());
	}
	  

}
