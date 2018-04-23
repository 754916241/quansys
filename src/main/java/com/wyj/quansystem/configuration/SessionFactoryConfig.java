package com.wyj.quansystem.jdbc;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * 绑定mybatis
 */
// @Configuration
// @MapperScan("com.wyj.quansystem.dao")
public class SessionFactoryConfig {

    @Value("${mybatis_config_file}")
    private String myBatilsPath;
    @Value("${mapper_path}")
    private String mapperPath;
    @Value("${bean_package}")
    private String beanPackage;
    @Autowired
    @Qualifier("dataSource")
    // @Resource(name = "dataSource")
    private DataSource dataSource;

    @Bean("sqlSessionFactory")
    public SqlSessionFactoryBean create(){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(myBatilsPath));
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        String packagePath = PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + mapperPath;
        // SqlSessionFac
        try {
            sqlSessionFactoryBean.setMapperLocations(resolver.getResources(packagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage(beanPackage);
        return sqlSessionFactoryBean;
    }
}
