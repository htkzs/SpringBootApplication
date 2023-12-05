package com.itheima.app.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * @ClassName : MyDataSourceConfig
 * @Description : 配置Druid数据源
 * @Author : 20609
 * @Date: 2023/1/1  13:04
 */
//@Configuration
public class MyDataSourceConfig {
    //将配置文件的配置项的值和DruidDataSource类中的属性值绑定
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource dataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        //设置最大的活动线程数
        dataSource.setMaxActive(10);
        //加入监控功能和防火墙功能
        dataSource.setFilters("stat,wall");
        return dataSource;
    }

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
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");

        return filterRegistrationBean;
    }


}
