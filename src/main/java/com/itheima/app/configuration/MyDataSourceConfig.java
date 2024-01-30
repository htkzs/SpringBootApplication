package com.itheima.app.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * @ClassName : MyDataSourceConfig
 * @Description : 配置Druid数据源
 * @Author : 20609
 * @Date: 2023/1/1  13:04
 */
//@Deprecated  //标识该配置类过期采用starter的方式配置druid
@Configuration
public class MyDataSourceConfig {
    //将配置文件的配置项的值和DruidDataSource类中的属性值绑定
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource dataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        //设置最大的活动线程数
        dataSource.setMaxActive(10);
        //加入监控功能和防火墙功能,如果不加stat则不能监控sql的执行状态
        dataSource.setFilters("stat,wall");
        return dataSource;
    }
    /**
     * @description: TODO 创建事务管理器
     * @author 20609
     * @date 2023/12/27 20:23
     * @version 1.0
     */
//    @Bean
//    public PlatformTransactionManager transactionManager() throws SQLException {
//        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
//        dataSourceTransactionManager.setDataSource(dataSource());
//        return dataSourceTransactionManager;
//    }
//
//    @Bean
//    public JdbcTemplate jdbcTemplate() throws SQLException {
//        return new JdbcTemplate(dataSource());
//    }



    /**
    * @Param :
    * @Description :  配置 druid的监控页功能
    * @Author : 20609
    * @Date : 2023/1/1 13:22
    */

    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet(){
        StatViewServlet statViewServlet = new StatViewServlet();
        ServletRegistrationBean<StatViewServlet> registrationBean = new ServletRegistrationBean<>(statViewServlet, "/druid/*");
        //设置web监控页面的账号和密码
        registrationBean.addInitParameter("loginUsername","admin");
        registrationBean.addInitParameter("loginPassword","123456");
        return registrationBean;
    }

    /**
    * @Param :
    * @Description :  WebStatFilter 用于采集web-jdbc关联监控的数据。
    * @Author : 20609
    * @Date : 2023/1/1 13:23
    */

    @Bean
    public FilterRegistrationBean<WebStatFilter> webStatFilter(){
        WebStatFilter webStatFilter = new WebStatFilter();

        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<>(webStatFilter);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        //排除一些过滤的路径
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");

        return filterRegistrationBean;
    }


}
