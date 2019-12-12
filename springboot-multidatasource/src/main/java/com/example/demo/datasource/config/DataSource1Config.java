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
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan(basePackages = "com.example.demo.test1", sqlSessionFactoryRef = "sqlSessionFactory1")
public class DataSource1Config {
	/**
	 * @methodDesc: 功能描述:(配置test1数据库)
	 * prefix = "spring.datasource.hikari.test1" 是application.perties配置的数据库前缀
	 */
	@Bean(name = "DataSource1")
	@ConfigurationProperties(prefix = "spring.datasource.hikari.test1")
	@Primary
	public DataSource testDataSource() {
		return DataSourceBuilder.create().build();
	}

	/**
	 * @methodDesc: 功能描述:(test1 sql会话工厂)
	 */
	@Bean(name = "sqlSessionFactory1")
	@Primary
	public SqlSessionFactory testSqlSessionFactory(@Qualifier("DataSource1") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		return bean.getObject();
	}
	/**
	 * @methodDesc: 功能描述:(test1 事物管理)
	 */
	@Bean(name = "test1TransactionManager")
	@Primary
	public DataSourceTransactionManager testTransactionManager(@Qualifier("DataSource1") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	@Bean(name = "test1SqlSessionTemplate")
	@Primary
	public SqlSessionTemplate testSqlSessionTemplate(
			@Qualifier("sqlSessionFactory1") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
