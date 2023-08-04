package com.yc;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.dbcp2.BasicDataSource;
import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;


@Configuration
@Data
@PropertySource("classpath:/db.properties")
@Log4j2
public class DataSourceConfig {
    //利用DI将db.properties中的内容注入
    @Value("${user}")
    private String user;
    @Value("${password}")
    private String password;
    @Value("${url}")
    private String url;
    @Value("${driverclass}")
    private String driverclass;
    @Value("#{T(Runtime).getRuntime().availableProcessors()*2}")
    private int cpuCount;

    @Bean
    public TransactionManager dataSourceTransactionManager(@Autowired @Qualifier("dataSource") DataSource ds){
        DataSourceTransactionManager tx = new DataSourceTransactionManager();
        tx.setDataSource(ds);
        return tx;
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverclass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public DataSource dbcpDataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverclass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(initMethod = "init")
    public DruidDataSource druidDataSource(){
        DruidDataSource dds=new DruidDataSource();
        dds.setDriverClassName(driverclass);
        dds.setUrl(url);
        dds.setUsername(user);
        dds.setPassword(password);

        //当前主机的CPU数*2
        //int c=Runtime.getRuntime().availableProcessors()*2
        log.info("配置druid的连接池大小："+cpuCount);
        dds.setMaxActive(cpuCount);
        dds.setMaxActive(cpuCount*2);
        return dds;
    }
}
