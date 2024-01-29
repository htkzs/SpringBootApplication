package com.itheima.app;

import com.itheima.app.entity.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.Map;
import java.util.Set;

/**
 * @SpringBootApplication：标识该应用是一个Springboot应用
 * 注意这里不能使用application明明xml文件否则会报错
 */
@ImportResource(value="classpath:bean.xml")
@SpringBootApplication
@MapperScan(basePackages = "com.itheima.app.mapper")
public class HelloSpringBootApplication {
    public static void main(String[] args) {

        ConfigurableApplicationContext run = SpringApplication.run(HelloSpringBootApplication.class, args);
//        String[] beanDefinitionNames = run.getBeanDefinitionNames();
//        for (String beanDefinitionName : beanDefinitionNames) {
//            System.out.println(beanDefinitionName);
//        }
//        System.out.println(run.getBeanDefinitionCount());


//        User getUser =(User)run.getBean("getUser");
//        System.out.println(getUser);
//        Pet pet = (Pet)run.getBean("getPet");
//        System.out.println(pet);
//        boolean getUser = run.containsBean("getUser");
//        System.out.println(getUser);
//        User user = (User)run.getBean("getUser");
//        System.out.println(user);



//        User getUser = run.getBean("getUser", User.class);
//        System.out.println(getUser);
//        //配置类本身也是一个组件 组件的名称就是类名的首字母小写
//        ApplicationConfig app = run.getBean(ApplicationConfig.class);
//        //
//        //当 proxyBeanMethods = true时 app的类型为com.itheima.app.configuration.ApplicationConfig$$EnhancerBySpringCGLIB$$c2390cb6@569ca90f
//        System.out.println(app);
//        //通过代理对象多次调用配置类的方法检查得到的对象是否是同一个对象
//        //所以当开启代理bean方法后，无论调用多少次方法得到的都是单实例的bean，springboot在每次创建bean的时候会检查当前类型的bean是否存在，存在就直接使用，
//        //应用场景是解决了组件的依赖，缺点是，每次创建时都会检查，导致springboot的启动会很慢
//        User user = app.getUser();
//        User user1 = app.getUser();
//        boolean b = user == user1;
//        System.out.println("使用代理类多次调用方法得到的是否是同一个bean："+b);
//
//        //当设置proxyBeanMethods = false时，每次调用配置类的方法都会创建一个新的对象  测试结果为false，此时ApplicationConfig对象类型为com.itheima.app.configuration.ApplicationConfig@748e3a07
//        User user2 = app.getUser();
//        User user3 = app.getUser();
//        System.out.println(user2 == user3);
//
//
//
//        System.out.println(user == getUser);
//        //确定user的一个类型 class com.itheima.app.entity.User普通对象 也就是说
//        System.out.println(user.getClass());
        System.out.println("_________________________________________");
        //拿到IOC容器中的某个类型的所有bean
        String[] names = run.getBeanNamesForType(User.class);
        for (String name : names) {
            System.out.println(name);
        }


        ConfigurableEnvironment environment = run.getEnvironment();
        //获取环境变量的值
        Map<String, Object> systemEnvironment = environment.getSystemEnvironment();
        //获取系统属性
        Map<String, Object> systemProperties = environment.getSystemProperties();

        Set<String> keySet = systemEnvironment.keySet();
        for (String key:keySet){
            System.out.println("环境变量的属性为："+key+":对应的值为"+systemEnvironment.get(key));
        }
        Set<String> set = systemProperties.keySet();
        for (String key:set){
            System.out.println("系统属性名称为："+key+":对应的值为"+systemProperties.get(key));
        }
    }
}
