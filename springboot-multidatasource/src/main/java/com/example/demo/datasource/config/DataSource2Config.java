package com.example.demo.datasource.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan(basePackages = "com.example.demo.test2", sqlSessionFactoryRef = "sqlSessionFactory2")
public class DataSource2Config {

	/**
	 * @methodDesc: 
	 * prefix = "spring.datasource.hikari.test1" 是application.perties配置的数据库前缀
	 */
	@Bean(name = "DataSource2")
	@ConfigurationProperties(prefix = "spring.datasource.hikari.test2")
	public DataSource testDataSource() {
		return DataSourceBuilder.create().build();
	}

	/**
	 * @methodDesc: 功能描述:(test２ sql会话工厂)
	 */
	@Bean(name = "sqlSessionFactory2")
	public SqlSessionFactory testSqlSessionFactory(@Qualifier("DataSource2") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		return bean.getObject();
	}

	/**
	 * @methodDesc: 功能描述:(test2 事物管理)
	 */
	@Bean(name = "test2TransactionManager")
	public DataSourceTransactionManager testTransactionManager(@Qualifier("DataSource2") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "test2SqlSessionTemplate")
	public SqlSessionTemplate testSqlSessionTemplate(
			@Qualifier("sqlSessionFactory2") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
