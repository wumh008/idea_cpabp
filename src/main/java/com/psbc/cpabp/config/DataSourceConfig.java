package com.psbc.cpabp.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * <p>
 * 多数据源的配置类
 * </p>
 *
 * @author XiaoHH
 * @version 1.0
 * @date 2021-12-03 星期五 11:15:06
 * @file DataSourceConfig.java
 */
@Configuration
@MapperScans({
        // basePackages 是mapper的包路径，sqlSessionTemplateRef 是指定我们创建的 SqlSessionTemplate
        @MapperScan(basePackages = "com.psbc.cpabp.mapper.user", sqlSessionTemplateRef = "userSqlSessionTemplate"),
        @MapperScan(basePackages = "com.psbc.cpabp.mapper.order", sqlSessionTemplateRef = "orderSqlSessionTemplate")
})
public class DataSourceConfig {

    /**
     * 配置用户的数据源
     *
     * @return 用户的数据源对象
     */
    @Primary // 需要一个默认配置的数据源
    @Bean("user")
    @ConfigurationProperties(prefix = "spring.datasource.user") // 在yml中配置数据源的前缀
    public DataSource user() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 配置订单的数据源
     *
     * @return 订单的数据源对象
     */
    @Bean("order")
    @ConfigurationProperties(prefix = "spring.datasource.order") // 在yml中配置数据源的前缀
    public DataSource order() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 注册用户的 SqlSession 工厂
     *
     * @param user 数据源
     * @return SqlSession 工厂
     * @throws Exception 可能会抛出异常
     */
    @Primary // 配置一个这个类型默认的 bean
    @Bean("userSqlSessionFactory")
    public SqlSessionFactory userSqlSessionFactory(@Qualifier("user") DataSource user) throws Exception {
        // 创建bean
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        // 设置数据源
        factory.setDataSource(user);
        // 设置 mapper 文件的扫描
        factory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/user/*.xml"));
        return factory.getObject();
    }

    /**
     * 注册订单的 SqlSession 工厂
     *
     * @param order 数据源
     * @return SqlSession 工厂
     * @throws Exception 可能会抛出异常
     */
    @Bean("orderSqlSessionFactory")
    public SqlSessionFactory orderSqlSessionFactory(@Qualifier("order") DataSource order) throws Exception {
        // 创建bean
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        // 设置数据源
        factory.setDataSource(order);
        // 设置 mapper 文件的扫描
        factory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/order/*.xml"));
        return factory.getObject();
    }

    /**
     * 用户的 SqlSessionTemplate
     *
     * @param userSqlSessionFactory 用户的 SqlSession 工厂
     * @return 用户的 SqlSessionTemplate
     */
    @Primary // 设置这个类型默认的 bean
    @Bean("userSqlSessionTemplate")
    public SqlSessionTemplate userSqlSessionTemplate(@Qualifier("userSqlSessionFactory") SqlSessionFactory userSqlSessionFactory) {
        return new SqlSessionTemplate(userSqlSessionFactory);
    }

    /**
     * 订单的 SqlSessionTemplate
     *
     * @param orderSqlSessionFactory 订单的 SqlSession 工厂
     * @return 用户的 SqlSessionTemplate
     */
    @Bean("orderSqlSessionTemplate")
    public SqlSessionTemplate orderSqlSessionTemplate(@Qualifier("orderSqlSessionFactory") SqlSessionFactory orderSqlSessionFactory) {
        return new SqlSessionTemplate(orderSqlSessionFactory);
    }
}
